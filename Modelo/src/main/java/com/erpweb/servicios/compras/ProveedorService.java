package com.erpweb.servicios.compras;

import org.springframework.stereotype.Service;

import com.erpweb.entidades.compras.Proveedor;
import com.erpweb.servicios.compras.interfaces.ProveedorServiceInterfaz;
import com.erpweb.utiles.dao.ProveedorDao;



@Service
public class ProveedorService implements ProveedorServiceInterfaz {

	@Override
	public Proveedor obtieneProveedorDeProveedorDao(ProveedorDao proveedorDao) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProveedorDao obtieneProveedorDaoDeProveedor(Proveedor proveedor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void persisteProveedor(Proveedor proveedor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destruyeProveedor(Proveedor proveedor) {
		// TODO Auto-generated method stub
		
	}
	
}
