package com.erpweb.servicios.inventario;

import org.springframework.stereotype.Service;

import com.erpweb.dto.ArticuloDto;
import com.erpweb.entidades.inventario.Articulo;
import com.erpweb.servicios.inventario.interfaces.ArticuloServiceInterfaz;



@Service
public class ArticuloService implements ArticuloServiceInterfaz {

	@Override
	public Boolean creaArticuloDesdeArticuloDto(ArticuloDto articuloDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArticuloDto obtieneArticuloDto(Long id, Long empresaId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean actualizaArticulo(ArticuloDto articuloDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean eliminaArticulo(Articulo articulo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Articulo obtieneArticulo(Long id, Long empresaId) {
		// TODO Auto-generated method stub
		return null;
	}


}
