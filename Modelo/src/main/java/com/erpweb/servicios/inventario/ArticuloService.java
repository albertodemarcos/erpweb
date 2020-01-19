package com.erpweb.servicios.inventario;

import org.springframework.stereotype.Service;

import com.erpweb.entidades.compras.Articulo;
import com.erpweb.servicios.inventario.interfaces.ArticuloServiceInterfaz;
import com.erpweb.utiles.dao.ArticuloDao;



@Service
public class ArticuloService implements ArticuloServiceInterfaz {

	@Override
	public Articulo obtieneArticuloDeArticuloDao(ArticuloDao articuloDao) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArticuloDao obtieneArticuloDaoDeArticulo(Articulo articulo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void persisteArticulo(Articulo articulo) {
		// TODO Auto-generated method stub
	}

	@Override
	public void destruyeArticulo(Articulo articulo) {
		// TODO Auto-generated method stub
	}

}
