package com.erpweb.patrones.builder.factorias;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.erpweb.dto.FacturaDto;
import com.erpweb.entidades.inventario.Articulo;
import com.erpweb.entidades.ventas.Factura;
import com.erpweb.entidades.ventas.LineaFactura;
import com.erpweb.patrones.builder.factorias.claseBase.FactoriaEntidad;
import com.erpweb.patrones.builder.factorias.interfaz.IFactoriaFactura;
import com.erpweb.repositorios.inventario.ArticuloRepository;
import com.erpweb.repositorios.ventas.FacturaRepository;
import com.erpweb.repositorios.ventas.LineaFacturaRepository;

public class FactoriaFactura extends FactoriaEntidad implements IFactoriaFactura {

	@Autowired
	private FacturaRepository facturaRepository;
	@Autowired
	private LineaFacturaRepository lineaFacturaRepository;
	@Autowired
	private ArticuloRepository articuloRepository;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@Override
	public Factura crearEntidad(FacturaDto facturaDto) {

		Factura factura = new Factura();
		
		try {
			
			factura.setCodigo(facturaDto.getCodigo());
			
			factura.setFechaCreacion(facturaDto.getFechaCreacion());
			factura.setFechaInicio(facturaDto.getFechaInicio());
			factura.setFechaFin(facturaDto.getFechaFin());
			
			factura.setBaseImponible(facturaDto.getBaseImponible());
			factura.setImpuesto(facturaDto.getImpuesto());
			factura.setImporteTotal(facturaDto.getImporteTotal());
			
			Factura facturaSave = facturaRepository.save(factura);
			
			return facturaSave;
			
		} catch(Exception e) {
			
			logger.error("Error al crear la Venta en el metodo crearEntidad()");
			
			e.printStackTrace();
			
			return null;
		}
	}

	@Override
	public Factura crearLineasEntidad(Factura factura, FacturaDto facturaDto) {

		if( facturaDto.getArticulosCantidad().isEmpty() ) {
			
			return null;
		}
		
		try {
			
			Set<Long> articuloIds = facturaDto.getArticulosCantidad().entrySet().stream().map(art -> art.getKey() ).collect(Collectors.toSet());
			
			List<Articulo> articulos = articuloRepository.findByIdIn( articuloIds );

			Set<LineaFactura> lineasVenta = new HashSet<LineaFactura>();
			
			//Por cada articulo, se crea una linea de contrato
			for(Articulo articulo : articulos) {
				
				LineaFactura lineaFactura = new LineaFactura();
				
				lineaFactura.setFactura(factura);
				lineaFactura.setArticulo(articulo);
				lineaFactura.setBaseImponible(articulo.getBaseImponible());
				lineaFactura.setImporteTotal(articulo.getImporteTotal());
				BigDecimal importeImpuesto = new BigDecimal("" + (articulo.getImporteTotal().doubleValue() - articulo.getBaseImponible().doubleValue()) );
				lineaFactura.setImporteImpuesto( importeImpuesto ); 
				lineaFactura.setDescripcionLinea("");
				
				lineasVenta.add(lineaFactura);
			}
			
			lineaFacturaRepository.saveAll(lineasVenta);
			
			factura.getLineasFactura().clear();
			
			factura.getLineasFactura().addAll(lineasVenta);
			
			facturaRepository.saveAndFlush(factura);
			
			return factura;
			
		} catch(Exception e) {
			
			logger.error("Error al crear la venta en el metodo crearLineasEntidad()");
			
			e.printStackTrace();
			
			return null;
		}
	}

}
