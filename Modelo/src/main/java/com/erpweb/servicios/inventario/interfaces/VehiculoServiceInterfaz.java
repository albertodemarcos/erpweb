package com.erpweb.servicios.inventario.interfaces;

import com.erpweb.entidades.inventario.Vehiculo;

public interface VehiculoServiceInterfaz {

	public void obtieneVehiculo(Long id, Long empresaId); //Obtenemos el vehiculo de BBDD
	
	public void obtieneVehiculoDto(Long id, Long empresaId); //Obtenemos el vehiculo y lo llevamos a capa vista mediante dto
	
	public void actualizaVehiculo(Vehiculo vehiculo); //Actualizamos el vehiculo

	public void eliminaVehiculo(Vehiculo vehiculo); //Borramos el vehiculo
	
	
}
