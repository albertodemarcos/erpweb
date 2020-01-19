package com.erpweb.servicios.inventario;

import org.springframework.stereotype.Service;

import com.erpweb.entidades.compras.Servicio;
import com.erpweb.servicios.inventario.interfaces.ServicioServiceInterfaz;
import com.erpweb.utiles.dao.ServicioDao;



@Service
public class ServicioService implements ServicioServiceInterfaz {

	@Override
	public Servicio obtieneServicioDeServicioDao(ServicioDao servicioDao) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServicioDao obtieneServicioDaoDeServicio(Servicio servicio) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void persisteServicio(Servicio servicio) {
		// TODO Auto-generated method stub
	}

	@Override
	public void destruyeServicio(Servicio servicio) {
		// TODO Auto-generated method stub
	}

}
