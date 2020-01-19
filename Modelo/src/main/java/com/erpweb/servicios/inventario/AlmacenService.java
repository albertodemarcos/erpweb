package com.erpweb.servicios.inventario;

import org.springframework.stereotype.Service;

import com.erpweb.entidades.inventario.Almacen;
import com.erpweb.servicios.inventario.interfaces.AlmacenServiceInterfaz;
import com.erpweb.utiles.dao.AlmacenDao;



@Service
public class AlmacenService implements AlmacenServiceInterfaz {

	@Override
	public Almacen obtieneAlmacenDeAlmacenDao(AlmacenDao almacenDao) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AlmacenDao obtieneAlmacenDaoDeAlmacen(Almacen almacen) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void persisteAlmacen(Almacen almacen) {
		// TODO Auto-generated method stub
	}

	@Override
	public void destruyeAlmacen(Almacen almacen) {
		// TODO Auto-generated method stub
	}

}
