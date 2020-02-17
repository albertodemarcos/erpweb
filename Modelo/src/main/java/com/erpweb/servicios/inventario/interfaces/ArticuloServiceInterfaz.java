package com.erpweb.servicios.inventario.interfaces;

import com.erpweb.dto.ArticuloDto;
import com.erpweb.entidades.inventario.Articulo;

public interface ArticuloServiceInterfaz {

	public Boolean creaArticuloDesdeArticuloDto(ArticuloDto articuloDto); //Crea  mediante

	public ArticuloDto obtieneArticuloDto(Long id, Long empresaId); //Visualizar el articulo
	
	public Boolean actualizaArticulo(ArticuloDto articuloDto); //Actualizamos el articulo
	
	public Boolean eliminaArticulo(Articulo articulo); //Borramos el articulo 	

	public Articulo obtieneArticulo(Long id, Long empresaId); //Obtenemos el articulo de BBDD
	
}
