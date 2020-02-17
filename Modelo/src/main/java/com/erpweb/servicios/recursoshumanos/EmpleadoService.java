package com.erpweb.servicios.recursoshumanos;

import org.springframework.stereotype.Service;

import com.erpweb.dto.EmpleadoDto;
import com.erpweb.entidades.recursoshumanos.Empleado;
import com.erpweb.servicios.recursoshumanos.interfaces.EmpleadoServiceInterfaz;



@Service
public class EmpleadoService implements EmpleadoServiceInterfaz {

	@Override
	public Boolean creaEmpleadoDesdeEmpleadoDto(EmpleadoDto empleadoDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmpleadoDto obtieneEmpleadoDto(Long id, Long empresaId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean actualizaEmpleado(EmpleadoDto empleadoDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean eliminaEmpleado(Empleado empleado) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Empleado obtieneEmpleado(Long id, Long empresaId) {
		// TODO Auto-generated method stub
		return null;
	}

}
