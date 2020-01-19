package com.erpweb.servicios.crm.interfaces;

import com.erpweb.entidades.crm.Empleado;
import com.erpweb.utiles.dao.EmpleadoDao;

public interface EmpleadoServiceInterfaz {

	public Empleado obtieneEmpleadoDeEmpleadoDao(EmpleadoDao empleadoDao);
	
	public EmpleadoDao obtieneEmpleadoDaoDeEmpleado(Empleado empleado);
	
	public void persisteEmpleado(Empleado empleado);
	
	public void destruyeEmpleado(Empleado empleado);
}
