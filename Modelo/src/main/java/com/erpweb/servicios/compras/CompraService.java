package com.erpweb.servicios.compras;

import org.springframework.stereotype.Service;

import com.erpweb.entidades.compras.Compra;
import com.erpweb.servicios.compras.interfaces.CompraServiceInterfaz;
import com.erpweb.utiles.dao.CompraDao;



@Service
public class CompraService implements CompraServiceInterfaz{

	@Override
	public Compra obtieneCompraDeCompraDao(CompraDao compraDao) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CompraDao obtieneCompraDaoDeCompra(Compra compra) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void persisteCompra(Compra Compra) {
		// TODO Auto-generated method stub
	}

	@Override
	public void destruyeCompra(Compra Compra) {
		// TODO Auto-generated method stub
	}

}
