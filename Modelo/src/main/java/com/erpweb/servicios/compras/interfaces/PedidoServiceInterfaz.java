package com.erpweb.servicios.compras.interfaces;

import com.erpweb.entidades.inventario.Pedido;
import com.erpweb.utiles.dao.PedidoDao;

public interface PedidoServiceInterfaz {

	public Pedido obtienePedidoDePedidoDao(PedidoDao pedidoDao);
	
	public PedidoDao obtienePedidoDaoDePedido(Pedido pedido);
	
	public void persistePedido(Pedido pedido);
	
	public void destruyePedido(Pedido pedido);
}
