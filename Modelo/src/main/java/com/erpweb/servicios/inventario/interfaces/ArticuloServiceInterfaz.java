package com.erpweb.servicios.inventario.interfaces;

import com.erpweb.entidades.inventario.Articulo;

public interface ArticuloServiceInterfaz {

	public void obtieneArticulo(Long id, Long empresaId); //Obtenemos el articulo de BBDD
	
	public void obtieneArticuloDto(Long id, Long empresaId); //Obtenemos el articulo y lo llevamos a capa vista mediante dto
	
	public void actualizaArticulo(Articulo articulo); //Actualizamos el articulo

	public void eliminaArticulo(Articulo articulo); //Borramos el articulo 	
	
	
}
