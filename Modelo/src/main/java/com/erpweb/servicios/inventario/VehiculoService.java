package com.erpweb.servicios.inventario;

import org.springframework.stereotype.Service;

import com.erpweb.dto.VehiculoDto;
import com.erpweb.entidades.inventario.Vehiculo;
import com.erpweb.servicios.inventario.interfaces.VehiculoServiceInterfaz;



@Service
public class VehiculoService implements VehiculoServiceInterfaz {

	@Override
	public Boolean creaVehiculoDesdeVehiculoDto(VehiculoDto vehiculoDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VehiculoDto obtieneVehiculoDto(Long id, Long empresaId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean actualizaVehiculo(VehiculoDto vehiculoDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean eliminaVehiculo(Vehiculo vehiculo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vehiculo obtieneVehiculo(Long id, Long empresaId) {
		// TODO Auto-generated method stub
		return null;
	}


}
