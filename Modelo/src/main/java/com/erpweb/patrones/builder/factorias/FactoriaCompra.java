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

import com.erpweb.dto.CompraDto;
import com.erpweb.entidades.compras.Compra;
import com.erpweb.entidades.compras.LineaCompra;
import com.erpweb.entidades.inventario.Articulo;
import com.erpweb.patrones.builder.factorias.claseBase.FactoriaEntidad;
import com.erpweb.patrones.builder.factorias.interfaz.IFactoriaCompra;
import com.erpweb.repositorios.compras.CompraRepository;
import com.erpweb.repositorios.compras.LineaCompraRepository;
import com.erpweb.repositorios.inventario.ArticuloRepository;

@Component
public class FactoriaCompra extends FactoriaEntidad implements IFactoriaCompra{

	@Autowired
	private CompraRepository compraRepository;
	@Autowired
	private LineaCompraRepository lineaCompraRepository;
	@Autowired
	private ArticuloRepository articuloRepository;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	

	@Override
	public Compra crearEntidad(CompraDto compraDto) {

		Compra compra = new Compra();
		
		try {
			
			compra.setCodigo(compraDto.getCodigo());
			compra.setFechaCompra(compraDto.getFechaCompra());
			//compra.setArticulo(compraDto.getArticulo());
			//compra.setCantidad(compraDto.getCantidad());
			compra.setBaseImponibleTotal(compraDto.getBaseImponibleTotal());
			compra.setImpuesto(compraDto.getImpuesto());
			compra.setImporteTotal(compraDto.getImporteTotal());
			
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
				lineaCompra.setBaseImponible(articulo.getBaseImponible());
				lineaCompra.setImporteTotal(articulo.getImporteTotal());
				BigDecimal importeImpuesto = new BigDecimal("" + (articulo.getImporteTotal().doubleValue() - articulo.getBaseImponible().doubleValue()) );
				lineaCompra.setImporteImpuesto( importeImpuesto ); 
				lineaCompra.setDescripcionLinea("");
				
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


}
