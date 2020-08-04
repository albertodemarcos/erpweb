package com.erpweb.patrones.builder.constructores.interfaz;

import com.erpweb.dto.PedidoDto;
import com.erpweb.entidades.compras.Pedido;

public interface IConstructorPedido {

	public Pedido crearEntidadLineasEntidad(PedidoDto pedidoDto);
	
}
