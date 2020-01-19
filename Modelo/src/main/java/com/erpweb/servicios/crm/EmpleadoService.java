package com.erpweb.servicios.crm;

import org.springframework.stereotype.Service;

import com.erpweb.entidades.crm.Empleado;
import com.erpweb.servicios.crm.interfaces.EmpleadoServiceInterfaz;
import com.erpweb.utiles.dao.EmpleadoDao;



@Service
public class EmpleadoService implements EmpleadoServiceInterfaz {

	@Override
	public Empleado obtieneEmpleadoDeEmpleadoDao(EmpleadoDao empleadoDao) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmpleadoDao obtieneEmpleadoDaoDeEmpleado(Empleado empleado) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void persisteEmpleado(Empleado empleado) {
		// TODO Auto-generated method stub
	}

	@Override
	public void destruyeEmpleado(Empleado empleado) {
		// TODO Auto-generated method stub
	}

	

}
