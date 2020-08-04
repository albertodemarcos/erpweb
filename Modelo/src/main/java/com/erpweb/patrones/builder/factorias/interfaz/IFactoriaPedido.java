package com.erpweb.patrones.builder.factorias.interfaz;

import com.erpweb.dto.PedidoDto;
import com.erpweb.entidades.compras.Pedido;

public interface IFactoriaPedido {

	/**
	 * Generamos la entidad principal
	 */
	public Pedido crearEntidad(PedidoDto pedidoDto);
	
	/**
	 * Generamos las lineas de la entidad principal
	 */
	public Pedido crearLineasEntidad(Pedido pedido, PedidoDto pedidoDto);
	
}
