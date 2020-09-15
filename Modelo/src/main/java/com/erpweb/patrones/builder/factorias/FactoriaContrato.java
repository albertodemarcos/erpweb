package com.erpweb.patrones.builder.factorias;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erpweb.dto.ContratoDto;
import com.erpweb.entidades.inventario.Articulo;
import com.erpweb.entidades.inventario.StockArticulo;
import com.erpweb.entidades.ventas.Contrato;
import com.erpweb.entidades.ventas.Factura;
import com.erpweb.entidades.ventas.LineaContrato;
import com.erpweb.entidades.ventas.LineaFactura;
import com.erpweb.patrones.builder.factorias.claseBase.FactoriaEntidad;
import com.erpweb.patrones.builder.factorias.interfaz.IFactoriaContrato;
import com.erpweb.repositorios.inventario.ArticuloRepository;
import com.erpweb.repositorios.inventario.StockArticuloRepository;
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
	@Autowired
	private StockArticuloRepository stockArticuloRepository;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	
	@Override
	public Contrato crearEntidad(ContratoDto contratoDto, Factura factura) {

		Contrato contrato = new Contrato();
		
		try {
			
			contrato.setCodigo(contratoDto.getCodigo());
			contrato.setFechaCreacion(new Date());
			contrato.setFechaInicio(contratoDto.getFechaInicio());
			contrato.setFechaFin(contratoDto.getFechaFin());
			contrato.setBaseImponibleTotal(new BigDecimal(0));
			contrato.setImporteTotal(new BigDecimal(0));
			contrato.setFactura(factura);			
			
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
			
			Set<Long> almacenesId = contratoDto.getArticulosAlmacen().entrySet().stream().map(al -> al.getValue() ).collect(Collectors.toSet());
			
			List<Articulo> articulos = articuloRepository.findByIdIn( articuloIds );

			Set<LineaContrato> lineasContrato = new HashSet<LineaContrato>();
			
			//Restar unidades
			Set<StockArticulo> stockAlmacen = this.recuperarStockAlmacenes(articuloIds, almacenesId);
			
			//Por cada articulo, se crea una linea de contrato
			for(Articulo articulo : articulos) {
				
				LineaContrato lineaContrato = new LineaContrato();
				
				lineaContrato.setContrato(contrato);
				lineaContrato.setArticulo(articulo);
				
				//Calculamos los importes
				BigDecimal cantidad = contratoDto.getArticulosCantidad().get(articulo.getId());
				BigDecimal baseImponibleTotal = this.calcularImporte(articulo.getBaseImponible(), cantidad);
				BigDecimal importeTotal = this.calcularImporte(articulo.getImporteTotal(), cantidad);
				Double importeImpuesto = articulo.getImporteTotal().doubleValue() - articulo.getBaseImponible().doubleValue();
				
				lineaContrato.setBaseImponible(baseImponibleTotal);
				lineaContrato.setImporteTotal(importeTotal);
				lineaContrato.setCantidad(cantidad.intValue());
				lineaContrato.setImporteImpuesto( new BigDecimal(importeImpuesto) ); 
				lineaContrato.setDescripcionLinea("");
				
				contrato.setBaseImponibleTotal(contrato.getBaseImponibleTotal().add(baseImponibleTotal));
				contrato.setImporteTotal(contrato.getBaseImponibleTotal().add(importeTotal));
				
				lineasContrato.add(lineaContrato);
				
				Long almacenId = contratoDto.getArticulosAlmacen().get(articulo.getId());
				
				//Restar unidades
				this.restarCantidadStockAlmacenes(stockAlmacen, almacenId, articulo.getId(), cantidad);
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
	public Factura crearFacturaEntidad(Contrato contrato, Factura facturaPre) {

		logger.trace("Entramos en el metodo crearFacturaEntidad()");
		
		try {
			
			facturaPre.setCodigo(contrato.getCodigo());
			facturaPre.setFechaCreacion(new Date());
			facturaPre.setFechaFactura(contrato.getFechaInicio());			
			facturaPre.setBaseImponible(contrato.getBaseImponibleTotal());
			facturaPre.setImporteTotal(contrato.getImporteTotal());
			facturaPre.setDescripcion(contrato.getDescripcion());
			facturaPre.setImpuesto(contrato.getImpuesto());
			
			Factura facturaSave = facturaRepository.save(facturaPre);
			
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

	@Override
	public Factura preCrearFacturaEntidad() {

		logger.trace("Entramos en el metodo preCrearFacturaEntidad()");
		
		try {
			
			Factura factura = new Factura();
			
			Factura facturaSave = facturaRepository.save(factura);
			
			return facturaSave;
			
		}catch(Exception e) {
			
			logger.error("Error al crear la factura para la compra en el metodo crearFacturaEntidad()");
			
			e.printStackTrace();
			
			return null;
		}
	}

	@Override
	public BigDecimal calcularImporte(BigDecimal importe, BigDecimal cantidad) {
		return new BigDecimal(importe.doubleValue() * cantidad.doubleValue());
	}

	public Set<StockArticulo> recuperarStockAlmacenes(Set<Long> articulosId, Set<Long> almacenesId) {
		
		try {
			
			Set<StockArticulo> stock = stockArticuloRepository.obtieneStockArticuloAlmacen(almacenesId, articulosId);
			
			return stock;
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
			return null;
		}
	}
	
	@Transactional
	public void restarCantidadStockAlmacenes(Set<StockArticulo> stockAlmacen, Long almacenId, Long articuloId, BigDecimal cantidad) {
		
		StockArticulo stock = stockAlmacen.stream().filter(s -> {
			return s.getArticulo().getId().equals(articuloId) && s.getAlmacen().getId().equals(almacenId);
		}).findFirst().orElse(null);
		
		Long cantidadFinal = stock.getCantidad().longValue() - cantidad.longValue();
		
		stock.setCantidad(cantidadFinal);
		
		try {
			
			this.stockArticuloRepository.save(stock);
			
		} catch(Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
}
