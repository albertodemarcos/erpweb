package com.erpweb.servicios.inventario.interfaces;

import com.erpweb.entidades.compras.Articulo;
import com.erpweb.utiles.dao.ArticuloDao;

public interface ArticuloServiceInterfaz {

	public Articulo obtieneArticuloDeArticuloDao(ArticuloDao articuloDao);
	
	public ArticuloDao obtieneArticuloDaoDeArticulo(Articulo articulo);
	
	public void persisteArticulo(Articulo articulo);
	
	public void destruyeArticulo(Articulo articulo);
}
