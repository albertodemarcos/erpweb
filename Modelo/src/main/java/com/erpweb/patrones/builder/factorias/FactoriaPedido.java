package com.erpweb.patrones.builder.factorias;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.erpweb.dto.PedidoDto;
import com.erpweb.entidades.compras.LineaPedido;
import com.erpweb.entidades.compras.Pedido;
import com.erpweb.entidades.inventario.Articulo;
import com.erpweb.patrones.builder.factorias.claseBase.FactoriaEntidad;
import com.erpweb.patrones.builder.factorias.interfaz.IFactoriaPedido;
import com.erpweb.repositorios.compras.LineaPedidoRepository;
import com.erpweb.repositorios.compras.PedidoRepository;
import com.erpweb.repositorios.inventario.ArticuloRepository;

public class FactoriaPedido extends FactoriaEntidad implements IFactoriaPedido {

	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private LineaPedidoRepository lineaPedidoRepository;
	@Autowired
	private ArticuloRepository articuloRepository;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public Pedido crearEntidad(PedidoDto pedidoDto) {

		Pedido pedido = new Pedido();
		
		try {
			
			pedido.setCodigo(pedidoDto.getCodigo());
			pedido.setFechaPedido(pedidoDto.getFechaPedido());
			//compra.setArticulo(compraDto.getArticulo());
			//compra.setCantidad(compraDto.getCantidad());
			pedido.setBaseImponibleTotal(pedidoDto.getBaseImponibleTotal());
			pedido.setImpuesto(pedidoDto.getImpuesto());
			pedido.setImporteTotal(pedidoDto.getImporteTotal());
			
			Pedido pedidoSave = pedidoRepository.save(pedido);
			
			return pedidoSave;
			
		} catch(Exception e) {
			
			logger.error("Error al crear la compra en el metodo crearEntidad()");
			
			e.printStackTrace();
			
			return null;
		}
	}

	@Override
	public Pedido crearLineasEntidad(Pedido pedido, PedidoDto pedidoDto) {

		if( pedidoDto.getArticulosCantidad().isEmpty() ) {
			
			return null;
		}
		
		try {
			
			Set<Long> articuloIds = pedidoDto.getArticulosCantidad().entrySet().stream().map(art -> art.getKey() ).collect(Collectors.toSet());
			
			List<Articulo> articulos = articuloRepository.findByIdIn( articuloIds );

			Set<LineaPedido> lineasPedido = new HashSet<LineaPedido>();
			
			//Por cada articulo, se crea una linea de compra
			for(Articulo articulo : articulos) {
				
				LineaPedido lineaPedido = new LineaPedido();
				
				lineaPedido.setPedido(pedido);
				lineaPedido.setArticulo(articulo);
				lineaPedido.setBaseImponible(articulo.getBaseImponible());
				lineaPedido.setImporteTotal(articulo.getImporteTotal());
				BigDecimal importeImpuesto = new BigDecimal("" + (articulo.getImporteTotal().doubleValue() - articulo.getBaseImponible().doubleValue()) );
				lineaPedido.setImporteImpuesto( importeImpuesto ); 
				lineaPedido.setDescripcionLinea("");
				
				lineasPedido.add(lineaPedido);
			}
			
			lineaPedidoRepository.saveAll(lineasPedido);
			
			pedido.getLineasPedido().clear();
			
			pedido.getLineasPedido().addAll(lineasPedido);
			
			pedidoRepository.saveAndFlush(pedido);
			
			return pedido;
			
		} catch(Exception e) {
			
			logger.error("Error al crear el pedido en el metodo crearLineasEntidad()");
			
			e.printStackTrace();
			
			return null;
		}
	}

}
