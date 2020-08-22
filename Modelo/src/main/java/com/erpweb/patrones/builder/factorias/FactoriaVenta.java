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

import com.erpweb.dto.VentaDto;
import com.erpweb.entidades.inventario.Articulo;
import com.erpweb.entidades.ventas.Factura;
import com.erpweb.entidades.ventas.LineaFactura;
import com.erpweb.entidades.ventas.LineaVenta;
import com.erpweb.entidades.ventas.Venta;
import com.erpweb.patrones.builder.factorias.claseBase.FactoriaEntidad;
import com.erpweb.patrones.builder.factorias.interfaz.IFactoriaVenta;
import com.erpweb.repositorios.inventario.ArticuloRepository;
import com.erpweb.repositorios.ventas.FacturaRepository;
import com.erpweb.repositorios.ventas.LineaFacturaRepository;
import com.erpweb.repositorios.ventas.LineaVentaRepository;
import com.erpweb.repositorios.ventas.VentaRepository;

@Component
public class FactoriaVenta extends FactoriaEntidad implements IFactoriaVenta {

	@Autowired
	private VentaRepository ventaRepository;
	@Autowired
	private LineaVentaRepository lineaVentaRepository;
	@Autowired
	private ArticuloRepository articuloRepository;
	@Autowired
	private FacturaRepository facturaRepository;
	@Autowired
	private LineaFacturaRepository lineaFacturaRepository;
	
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public Venta crearEntidad(VentaDto ventaDto) {

		Venta venta = new Venta();
		
		try {
			
			venta.setCodigo(ventaDto.getCodigo());
			
			venta.setFechaCreacion(ventaDto.getFechaCreacion());
			venta.setFechaInicio(ventaDto.getFechaInicio());
			venta.setFechaFin(ventaDto.getFechaFin());
			
			venta.setBaseImponibleTotal(ventaDto.getBaseImponibleTotal());
			venta.setImpuesto(ventaDto.getImpuesto());
			venta.setImporteTotal(ventaDto.getImporteTotal());
			
			Venta ventaSave = ventaRepository.save(venta);
			
			return ventaSave;
			
		} catch(Exception e) {
			
			logger.error("Error al crear la Venta en el metodo crearEntidad()");
			
			e.printStackTrace();
			
			return null;
		}
	}

	@Override
	public Venta crearLineasEntidad(Venta venta, VentaDto ventaDto) {

		if( ventaDto.getArticulosCantidad().isEmpty() ) {
			
			return null;
		}
		
		try {
			
			Set<Long> articuloIds = ventaDto.getArticulosCantidad().entrySet().stream().map(art -> art.getKey() ).collect(Collectors.toSet());
			
			List<Articulo> articulos = articuloRepository.findByIdIn( articuloIds );

			Set<LineaVenta> lineasVenta = new HashSet<LineaVenta>();
			
			//Por cada articulo, se crea una linea de contrato
			for(Articulo articulo : articulos) {
				
				LineaVenta lineaVenta = new LineaVenta();
				
				lineaVenta.setVenta(venta);
				lineaVenta.setArticulo(articulo);
				lineaVenta.setBaseImponible(articulo.getBaseImponible());
				lineaVenta.setImporteTotal(articulo.getImporteTotal());
				BigDecimal importeImpuesto = new BigDecimal("" + (articulo.getImporteTotal().doubleValue() - articulo.getBaseImponible().doubleValue()) );
				lineaVenta.setImporteImpuesto( importeImpuesto ); 
				lineaVenta.setDescripcionLinea("");
				
				lineasVenta.add(lineaVenta);
			}
			
			lineaVentaRepository.saveAll(lineasVenta);
			
			venta.getLineasVenta().clear();
			
			venta.getLineasVenta().addAll(lineasVenta);
			
			ventaRepository.saveAndFlush(venta);
			
			return venta;
			
		} catch(Exception e) {
			
			logger.error("Error al crear la venta en el metodo crearLineasEntidad()");
			
			e.printStackTrace();
			
			return null;
		}
	}

	@Override
	public Factura crearFacturaEntidad(Venta venta) {

		logger.trace("Entramos en el metodo crearFacturaEntidad()");
		
		try {
			
			Factura factura = new Factura();
			
			factura.setId(null);
			factura.setCodigo(venta.getCodigo());
			factura.setFechaCreacion(venta.getFechaCreacion());
			factura.setFechaInicio(venta.getFechaInicio());
			factura.setFechaFin(venta.getFechaFin());
			factura.setBaseImponible(venta.getBaseImponibleTotal());
			factura.setImporteTotal(venta.getImporteTotal());
			factura.setDescripcion(venta.getDescripcion());
			factura.setImpuesto(venta.getImpuesto());
			
			Factura facturaSave = facturaRepository.save(factura);
			
			return facturaSave;
			
		}catch(Exception e) {
			
			logger.error("Error al crear la factura para la venta en el metodo crearFacturaEntidad()");
			
			e.printStackTrace();
			
			return null;
		}
	}

	@Override
	public Factura crearLineasFacturaEntidad(Venta venta, Factura factura) {

		logger.trace("Entramos en el metodo crearLineasFacturaEntidad()");
		
		if( factura == null || venta == null || venta.getLineasVenta().isEmpty() ) {
			
			logger.error("Error al crear las lineas de la factura para la venta en el metodo crearLineasFacturaEntidad()");
			
			return null;
		}
		
		
		try {

			//Asociamos la factura a la venta
			venta.setFactura(factura);
			
			//Persistimos la factura en la venta
			ventaRepository.saveAndFlush(venta);
			
			Set<LineaFactura> lineasFactura = new HashSet<LineaFactura>();
			
			for(LineaVenta lineaVenta : venta.getLineasVenta()) {
				
				LineaFactura lineaFactura = new LineaFactura();
				
				lineaFactura.setFactura(factura);
				
				lineaFactura.setArticulo(lineaVenta.getArticulo());
				lineaFactura.setCantidad(lineaVenta.getCantidad());
				lineaFactura.setBaseImponible(lineaVenta.getBaseImponible());
				lineaFactura.setImporteImpuesto(lineaVenta.getImporteImpuesto());
				lineaFactura.setImporteTotal(lineaVenta.getImporteTotal());
				lineaFactura.setDescripcionLinea(lineaVenta.getDescripcionLinea());
				
				lineasFactura.add(lineaFactura);
			}
			
			lineaFacturaRepository.saveAll(lineasFactura);
			
			factura.getLineasFactura().clear();
			
			factura.getLineasFactura().addAll(lineasFactura);
			
			facturaRepository.saveAndFlush(factura);
			
		}catch(Exception e) {
			
			logger.error("Error al crear las lineas de la factura para la venta en el metodo crearLineasFacturaEntidad()");
			
			e.printStackTrace();
		}
		
		return null;
	}

}