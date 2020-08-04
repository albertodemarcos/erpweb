package com.erpweb.patrones.builder.factorias;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.erpweb.dto.ContratoDto;
import com.erpweb.entidades.inventario.Articulo;
import com.erpweb.entidades.ventas.Contrato;
import com.erpweb.entidades.ventas.LineaContrato;
import com.erpweb.patrones.builder.factorias.claseBase.FactoriaEntidad;
import com.erpweb.patrones.builder.factorias.interfaz.IFactoriaContrato;
import com.erpweb.repositorios.inventario.ArticuloRepository;
import com.erpweb.repositorios.ventas.ContratoRepository;
import com.erpweb.repositorios.ventas.LineaContratoRepository;

public class FactoriaContrato extends FactoriaEntidad implements IFactoriaContrato {

	@Autowired
	private ContratoRepository contratoRepository;
	@Autowired
	private LineaContratoRepository lineaContratoRepository;
	@Autowired
	private ArticuloRepository articuloRepository;
	
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

}
