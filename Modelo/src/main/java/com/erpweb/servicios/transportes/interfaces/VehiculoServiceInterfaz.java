package com.erpweb.servicios.transportes.interfaces;

import com.erpweb.entidades.transportes.Vehiculo;
import com.erpweb.utiles.dao.VehiculoDao;

public interface VehiculoServiceInterfaz {

	public Vehiculo obtieneVehiculoDeVehiculoDao(VehiculoDao vehiculoDao);
	
	public VehiculoDao obtieneVehiculoDaoDeVehiculo(Vehiculo vehiculo);
	
	public void persisteVehiculo(Vehiculo vehiculo);
	
	public void destruyeVehiculo(Vehiculo vehiculo);
}
