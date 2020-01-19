package com.erpweb.servicios.inventario.interfaces;

import com.erpweb.entidades.inventario.Almacen;
import com.erpweb.utiles.dao.AlmacenDao;

public interface AlmacenServiceInterfaz {

	public Almacen obtieneAlmacenDeAlmacenDao(AlmacenDao almacenDao);
	
	public AlmacenDao obtieneAlmacenDaoDeAlmacen(Almacen almacen);
	
	public void persisteAlmacen(Almacen almacen);
	
	public void destruyeAlmacen(Almacen almacen);
}
