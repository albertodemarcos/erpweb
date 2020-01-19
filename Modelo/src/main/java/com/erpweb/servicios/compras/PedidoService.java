package com.erpweb.servicios.compras;

import org.springframework.stereotype.Service;

import com.erpweb.entidades.inventario.Pedido;
import com.erpweb.servicios.compras.interfaces.PedidoServiceInterfaz;
import com.erpweb.utiles.dao.PedidoDao;



@Service
public class PedidoService implements PedidoServiceInterfaz {

	@Override
	public Pedido obtienePedidoDePedidoDao(PedidoDao pedidoDao) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PedidoDao obtienePedidoDaoDePedido(Pedido pedido) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void persistePedido(Pedido pedido) {
		// TODO Auto-generated method stub
	}

	@Override
	public void destruyePedido(Pedido pedido) {
		// TODO Auto-generated method stub
	}

}
