package com.erpweb.servicios.inventario.interfaces;

import com.erpweb.entidades.inventario.Almacen;

public interface AlmacenServiceInterfaz {

	public void obtieneAlmacen(Long id, Long empresaId); //Obtenemos la almacen de BBDD
	
	public void obtieneAlmacenDto(Long id, Long empresaId); //Obtenemos la almacen y lo llevamos a capa vista mediante dto
	
	public void actualizaAlmacen(Almacen almacen); //Actualizamos la almacen

	public void eliminaAlmacen(Almacen almacen); //Borramos la almacen
	
}
