package com.erpweb.patrones.builder.constructores;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erpweb.dto.PedidoDto;
import com.erpweb.entidades.compras.Pedido;
import com.erpweb.entidades.ventas.Factura;
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
			
			// Creamos el pedido y su factura en los siguientes pasos
			Pedido pedido = null;
			Factura factura = null;
			
			//Paso Previo: Creamos una factura vacia para asociar al pedido previamente
			factura = factoriaPedido.preCrearFacturaEntidad();
			
			//Paso 1: Pedido
			pedido = factoriaPedido.crearEntidad(pedidoDto);
			
			if( pedido == null ) {
				
				logger.error("Error al crear la entidad de pedido");
				
				return null;
			}
			
			//Paso 2: LineaPedido
			pedido = factoriaPedido.crearLineasEntidad(pedido, pedidoDto);
			
			//Paso 3: Crear factura
			factura = factoriaPedido.crearFacturaEntidad(pedido);
			
			if( factura == null ) {
				
				logger.error("Error al crear la factura de la entidad de pedido");
				
				return null;
			}

			
			//Paso 4: Crear lineaFactura
			factura = factoriaPedido.crearLineasFacturaEntidad(pedido, factura);
					
			return pedido;		
			
		} catch(Exception e) {
			
			logger.error("Error al crear la entidad y sus lineas de pedido");
			
			e.printStackTrace();
			
			return null;
		}
	}

}
