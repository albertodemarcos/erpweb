package com.erpweb.servicios.transportes;

import org.springframework.stereotype.Service;

import com.erpweb.entidades.transportes.Vehiculo;
import com.erpweb.servicios.transportes.interfaces.VehiculoServiceInterfaz;
import com.erpweb.utiles.dao.VehiculoDao;



@Service
public class VehiculoService implements VehiculoServiceInterfaz {

	@Override
	public Vehiculo obtieneVehiculoDeVehiculoDao(VehiculoDao vehiculoDao) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VehiculoDao obtieneVehiculoDaoDeVehiculo(Vehiculo vehiculo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void persisteVehiculo(Vehiculo vehiculo) {
		// TODO Auto-generated method stub
	}

	@Override
	public void destruyeVehiculo(Vehiculo vehiculo) {
		// TODO Auto-generated method stub
	}

}
