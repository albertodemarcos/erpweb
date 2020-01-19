package com.erpweb.servicios.compras.interfaces;

import com.erpweb.entidades.compras.Compra;
import com.erpweb.utiles.dao.CompraDao;

public interface CompraServiceInterfaz {

	public Compra obtieneCompraDeCompraDao( CompraDao compraDao);
	
	public CompraDao obtieneCompraDaoDeCompra(Compra compra);
	
	public void persisteCompra(Compra Compra);
	
	public void destruyeCompra(Compra Compra);	
}
