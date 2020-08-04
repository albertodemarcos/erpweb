package com.erpweb.patrones.builder.constructores;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erpweb.dto.PedidoDto;
import com.erpweb.entidades.compras.Pedido;
import com.erpweb.patrones.builder.constructores.claseBase.ConstructorEntidad;
import com.erpweb.patrones.builder.constructores.interfaz.IConstructorPedido;
import com.erpweb.patrones.builder.factorias.FactoriaPedido;

@Component
public class ConstructorPedido extends ConstructorEntidad implements IConstructorPedido {

	@Autowired
	private FactoriaPedido factoriaPedido;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public Pedido crearEntidadLineasEntidad(PedidoDto pedidoDto) {
		
		logger.trace("Entramos en crearEntidadLineasEntidad() para el pedido"); 
		
		try {
			
			// Creamos el pedido en dos pasos
			Pedido pedido = null;
			
			//Paso 1: Pedido
			pedido = factoriaPedido.crearEntidad(pedidoDto);
			
			if( pedido == null ) {
				
				logger.error("Error al crear la entidad de pedido");
				
				return null;
			}
			
			//Paso 2: LineaPedido
			pedido = factoriaPedido.crearLineasEntidad(pedido, pedidoDto);
					
			return pedido;		
			
		} catch(Exception e) {
			
			logger.error("Error al crear la entidad y sus lineas de pedido");
			
			e.printStackTrace();
			
			return null;
		}
	}

}
