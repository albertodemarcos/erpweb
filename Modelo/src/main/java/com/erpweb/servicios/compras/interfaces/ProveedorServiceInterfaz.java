package com.erpweb.servicios.compras.interfaces;

import com.erpweb.entidades.compras.Proveedor;
import com.erpweb.utiles.dao.ProveedorDao;

public interface ProveedorServiceInterfaz {

	public Proveedor obtieneProveedorDeProveedorDao(ProveedorDao proveedorDao);
	
	public ProveedorDao obtieneProveedorDaoDeProveedor(Proveedor proveedor);
	
	public void persisteProveedor( Proveedor proveedor);
	
	public void destruyeProveedor(Proveedor proveedor);
}
