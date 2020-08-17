package com.erpweb.patrones.builder.factorias;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erpweb.dto.ContratoDto;
import com.erpweb.entidades.inventario.Articulo;
import com.erpweb.entidades.ventas.Contrato;
import com.erpweb.entidades.ventas.Factura;
import com.erpweb.entidades.ventas.LineaContrato;
import com.erpweb.entidades.ventas.LineaFactura;
import com.erpweb.patrones.builder.factorias.claseBase.FactoriaEntidad;
import com.erpweb.patrones.builder.factorias.interfaz.IFactoriaContrato;
import com.erpweb.repositorios.inventario.ArticuloRepository;
import com.erpweb.repositorios.ventas.ContratoRepository;
import com.erpweb.repositorios.ventas.FacturaRepository;
import com.erpweb.repositorios.ventas.LineaContratoRepository;
import com.erpweb.repositorios.ventas.LineaFacturaRepository;

@Component
public class FactoriaContrato extends FactoriaEntidad implements IFactoriaContrato {

	@Autowired
	private ContratoRepository contratoRepository;
	@Autowired
	private LineaContratoRepository lineaContratoRepository;
	@Autowired
	private ArticuloRepository articuloRepository;
	@Autowired
	private FacturaRepository facturaRepository;
	@Autowired
	private LineaFacturaRepository lineaFacturaRepository;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	
	@Override
	public Contrato crearEntidad(ContratoDto contratoDto) {

		Contrato contrato = new Contrato();
		
		try {
			
			contrato.setCodigo(contratoDto.getCodigo());
			
			contrato.setFechaCreacion(contratoDto.getFechaCreacion());
			contrato.setFechaInicio(contratoDto.getFechaInicio());
			contrato.setFechaFin(contratoDto.getFechaFin());
			
			contrato.setBaseImponibleTotal(contratoDto.getBaseImponibleTotal());
			contrato.setImpuesto(contratoDto.getImpuesto());
			contrato.setImporteTotal(contratoDto.getImporteTotal());
			
			Contrato contratoSave = contratoRepository.save(contrato);
			
			return contratoSave;
			
		} catch(Exception e) {
			
			logger.error("Error al crear la compra en el metodo crearEntidad()");
			
			e.printStackTrace();
			
			return null;
		}
	}

	@Override
	public Contrato crearLineasEntidad(Contrato contrato, ContratoDto contratoDto) {

		if( contratoDto.getArticulosCantidad().isEmpty() ) {
			
			return null;
		}
		
		try {
			
			Set<Long> articuloIds = contratoDto.getArticulosCantidad().entrySet().stream().map(art -> art.getKey() ).collect(Collectors.toSet());
			
			List<Articulo> articulos = articuloRepository.findByIdIn( articuloIds );

			Set<LineaContrato> lineasContrato = new HashSet<LineaContrato>();
			
			//Por cada articulo, se crea una linea de contrato
			for(Articulo articulo : articulos) {
				
				LineaContrato lineaContrato = new LineaContrato();
				
				lineaContrato.setContrato(contrato);
				lineaContrato.setArticulo(articulo);
				lineaContrato.setBaseImponible(articulo.getBaseImponible());
				lineaContrato.setImporteTotal(articulo.getImporteTotal());
				BigDecimal importeImpuesto = new BigDecimal("" + (articulo.getImporteTotal().doubleValue() - articulo.getBaseImponible().doubleValue()) );
				lineaContrato.setImporteImpuesto( importeImpuesto ); 
				lineaContrato.setDescripcionLinea("");
				
				lineasContrato.add(lineaContrato);
			}
			
			lineaContratoRepository.saveAll(lineasContrato);
			
			contrato.getLineasContrato().clear();
			
			contrato.getLineasContrato().addAll(lineasContrato);
			
			contratoRepository.saveAndFlush(contrato);
			
			return contrato;
			
		} catch(Exception e) {
			
			logger.error("Error al crear el contrato en el metodo crearLineasEntidad()");
			
			e.printStackTrace();
			
			return null;
		}
	}

	@Override
	public Factura crearFacturaEntidad(Contrato contrato) {

		logger.trace("Entramos en el metodo crearFacturaEntidad()");
		
		try {
			
			Factura factura = new Factura();
			
			factura.setId(null);
			factura.setCodigo(contrato.getCodigo());
			factura.setFechaCreacion(contrato.getFechaCreacion());
			factura.setFechaInicio(contrato.getFechaInicio());
			factura.setFechaFin(contrato.getFechaFin());
			factura.setBaseImponible(contrato.getBaseImponibleTotal());
			factura.setImporteTotal(contrato.getImporteTotal());
			factura.setDescripcion(contrato.getDescripcion());
			factura.setImpuesto(contrato.getImpuesto());
			
			Factura facturaSave = facturaRepository.save(factura);
			
			return facturaSave;
			
		}catch(Exception e) {
			
			logger.error("Error al crear la factura para el contrato en el metodo crearFacturaEntidad()");
			
			e.printStackTrace();
			
			return null;
		}
	}

	@Override
	public Factura crearLineasFacturaEntidad(Contrato contrato, Factura factura) {

		logger.trace("Entramos en el metodo crearLineasFacturaEntidad()");
		
		if( factura == null || contrato == null || contrato.getLineasContrato().isEmpty() ) {
			
			logger.error("Error al crear las lineas de la factura para el contrato en el metodo crearLineasFacturaEntidad()");
			
			return null;
		}
		
		
		try {

			//Asociamos la factura al contrato
			contrato.setFactura(factura);
			
			//Persistimos la factura en el contrato
			contratoRepository.saveAndFlush(contrato);
			
			Set<LineaFactura> lineasFactura = new HashSet<LineaFactura>();
			
			for(LineaContrato lineaContrato : contrato.getLineasContrato()) {
				
				LineaFactura lineaFactura = new LineaFactura();
				
				lineaFactura.setFactura(factura);
				
				lineaFactura.setArticulo(lineaContrato.getArticulo());
				lineaFactura.setCantidad(lineaContrato.getCantidad());
				lineaFactura.setBaseImponible(lineaContrato.getBaseImponible());
				lineaFactura.setImporteImpuesto(lineaContrato.getImporteImpuesto());
				lineaFactura.setImporteTotal(lineaContrato.getImporteTotal());
				lineaFactura.setDescripcionLinea(lineaContrato.getDescripcionLinea());
				
				lineasFactura.add(lineaFactura);
			}
			
			lineaFacturaRepository.saveAll(lineasFactura);
			
			factura.getLineasFactura().clear();
			
			factura.getLineasFactura().addAll(lineasFactura);
			
			facturaRepository.saveAndFlush(factura);
			
		}catch(Exception e) {
			
			logger.error("Error al crear las lineas de la factura para el contrato en el metodo crearLineasFacturaEntidad()");
			
			e.printStackTrace();
		}
		
		return null;
	}

}
