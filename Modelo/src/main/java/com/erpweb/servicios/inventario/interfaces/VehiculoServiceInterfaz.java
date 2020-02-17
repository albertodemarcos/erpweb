package com.erpweb.servicios.inventario.interfaces;

import com.erpweb.dto.VehiculoDto;
import com.erpweb.entidades.inventario.Vehiculo;

public interface VehiculoServiceInterfaz {

	public Boolean creaVehiculoDesdeVehiculoDto(VehiculoDto vehiculoDto); //Crea el vehiculo mediante dto
	
	public VehiculoDto obtieneVehiculoDto(Long id, Long empresaId); //Visualizar el vehiculo
	
	public Boolean actualizaVehiculo(VehiculoDto vehiculoDto); //Actualizamos el vehiculo

	public Boolean eliminaVehiculo(Vehiculo vehiculo); //Borramos el vehiculo
	
	public Vehiculo obtieneVehiculo(Long id, Long empresaId); //Obtenemos el vehiculo de BBDD
}
