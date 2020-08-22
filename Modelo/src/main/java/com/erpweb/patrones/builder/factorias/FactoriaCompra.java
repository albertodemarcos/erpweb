package com.erpweb.patrones.builder.factorias;


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erpweb.dto.CompraDto;
import com.erpweb.entidades.compras.Compra;
import com.erpweb.entidades.compras.LineaCompra;
import com.erpweb.entidades.inventario.Articulo;
import com.erpweb.entidades.ventas.Factura;
import com.erpweb.entidades.ventas.LineaFactura;
import com.erpweb.patrones.builder.factorias.claseBase.FactoriaEntidad;
import com.erpweb.patrones.builder.factorias.interfaz.IFactoriaCompra;
import com.erpweb.repositorios.compras.CompraRepository;
import com.erpweb.repositorios.compras.LineaCompraRepository;
import com.erpweb.repositorios.inventario.ArticuloRepository;
import com.erpweb.repositorios.ventas.FacturaRepository;
import com.erpweb.repositorios.ventas.LineaFacturaRepository;

@Component
public class FactoriaCompra extends FactoriaEntidad implements IFactoriaCompra{

	@Autowired
	private CompraRepository compraRepository;
	@Autowired
	private LineaCompraRepository lineaCompraRepository;
	@Autowired
	private ArticuloRepository articuloRepository;
	@Autowired
	private FacturaRepository facturaRepository;
	@Autowired
	private LineaFacturaRepository lineaFacturaRepository;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	

	@Override
	public Compra crearEntidad(CompraDto compraDto, Factura factura) {

		logger.trace("Entramos en el metodo crearEntidad()");
		
		Compra compra = new Compra();
		
		try {
			
			compra.setCodigo(compraDto.getCodigo());
			compra.setFechaCompra(compraDto.getFechaCompra());
			compra.setBaseImponibleTotal(new BigDecimal(0));
			compra.setImporteTotal(new BigDecimal(0));
			compra.setFactura(factura);
			
			Compra compraSave = compraRepository.save(compra);
			
			return compraSave;
			
		} catch(Exception e) {
			
			logger.error("Error al crear la compra en el metodo crearEntidad()");
			
			e.printStackTrace();
			
			return null;
		}
	}

	@Override
	public Compra crearLineasEntidad(Compra compra, CompraDto compraDto) {
		
		logger.trace("Entramos en el metodo crearLineasEntidad()");
		
		if( compraDto.getArticulosCantidad().isEmpty() ) {
			
			return null;
		}
		
		try {
			
			Set<Long> articuloIds = compraDto.getArticulosCantidad().entrySet().stream().map(art -> art.getKey() ).collect(Collectors.toSet());
			
			List<Articulo> articulos = articuloRepository.findByIdIn( articuloIds );

			Set<LineaCompra> lineasCompra = new HashSet<LineaCompra>();
			
			//Por cada articulo, se crea una linea de compra
			for(Articulo articulo : articulos) {
				
				LineaCompra lineaCompra = new LineaCompra();
				
				lineaCompra.setCompra(compra);
				lineaCompra.setArticulo(articulo);
				
				//Calculamos los importes
				BigDecimal cantidad = compraDto.getArticulosCantidad().get(articulo.getId());
				BigDecimal baseImponibleTotal = this.calcularImporte(articulo.getBaseImponible(), cantidad);
				BigDecimal importeTotal = this.calcularImporte(articulo.getImporteTotal(), cantidad);
				Double importeImpuesto = articulo.getImporteTotal().doubleValue() - articulo.getBaseImponible().doubleValue();
				
				lineaCompra.setBaseImponible(baseImponibleTotal);
				lineaCompra.setImporteTotal(importeTotal);
				lineaCompra.setCantidad(cantidad.intValue());
				lineaCompra.setImporteImpuesto( new BigDecimal(importeImpuesto) ); 
				lineaCompra.setDescripcionLinea("");
				
				compra.setBaseImponibleTotal(compra.getBaseImponibleTotal().add(baseImponibleTotal));
				compra.setImporteTotal(compra.getBaseImponibleTotal().add(importeTotal));
				
				lineasCompra.add(lineaCompra);
			}
			
			lineaCompraRepository.saveAll(lineasCompra);
			
			compra.getLineasCompra().clear();
			
			compra.getLineasCompra().addAll(lineasCompra);
			
			compraRepository.saveAndFlush(compra);
			
			return compra;
			
		} catch(Exception e) {
			
			logger.error("Error al crear la compra en el metodo crearLineasEntidad()");
			
			e.printStackTrace();
			
			return null;
		}
	}
	
	@Override
	public Factura crearFacturaEntidad(Compra compra) {
		
		logger.trace("Entramos en el metodo crearFacturaEntidad()");
		
		try {
			
			Factura factura = new Factura();
			
			factura.setId(null);
			factura.setCodigo(null);
			factura.setFechaCreacion(new Date());
			factura.setFechaInicio(compra.getFechaCompra());
			factura.setFechaFin(compra.getFechaCompra());
			factura.setBaseImponible(compra.getBaseImponibleTotal());
			factura.setImporteTotal(compra.getImporteTotal());
			factura.setDescripcion(null);
			factura.setImpuesto(null);
			
			Factura facturaSave = facturaRepository.save(factura);
			
			return facturaSave;
			
		}catch(Exception e) {
			
			logger.error("Error al crear la factura para la compra en el metodo crearFacturaEntidad()");
			
			e.printStackTrace();
			
			return null;
		}
	}
	
	@Override
	public Factura crearLineasFacturaEntidad(Compra compra, Factura factura) {
		
		logger.trace("Entramos en el metodo crearLineasFacturaEntidad()");
		
		if( factura == null || compra == null || compra.getLineasCompra().isEmpty() ) {
			
			logger.error("Error al crear las lineas de la factura para la compra en el metodo crearLineasFacturaEntidad()");
			
			return null;
		}
		
		
		try {
			
			//Asociamos la factura a la compra
			compra.setFactura(factura);
			
			//Persistimos la factura en la compra
			compraRepository.saveAndFlush(compra);
			
			Set<LineaFactura> lineasFactura = new HashSet<LineaFactura>();
			
			for(LineaCompra lineaCompra : compra.getLineasCompra()) {
				
				LineaFactura lineaFactura = new LineaFactura();
				
				lineaFactura.setFactura(factura);
				
				lineaFactura.setArticulo(lineaCompra.getArticulo());
				lineaFactura.setCantidad(lineaCompra.getCantidad());
				lineaFactura.setBaseImponible(lineaCompra.getBaseImponible());
				lineaFactura.setImporteImpuesto(lineaCompra.getImporteImpuesto());
				lineaFactura.setImporteTotal(lineaCompra.getImporteTotal());
				lineaFactura.setDescripcionLinea(lineaCompra.getDescripcionLinea());
				
				lineasFactura.add(lineaFactura);
			}
			
			lineaFacturaRepository.saveAll(lineasFactura);
			
			factura.getLineasFactura().clear();
			
			factura.getLineasFactura().addAll(lineasFactura);
			
			facturaRepository.saveAndFlush(factura);
			
			
		}catch(Exception e) {
			
			logger.error("Error al crear las lineas de la factura para la compra en el metodo crearLineasFacturaEntidad()");
			
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


}
