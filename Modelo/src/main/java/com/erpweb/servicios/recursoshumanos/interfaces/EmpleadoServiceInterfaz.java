package com.erpweb.servicios.recursoshumanos.interfaces;

import com.erpweb.entidades.recursoshumanos.Empleado;

public interface EmpleadoServiceInterfaz {

	public void obtieneEmpleado(Long id, Long empresaId); //Obtenemos el empleado de BBDD
	
	public void obtieneEmpleadoDto(Long id, Long empresaId); //Obtenemos el empleado y lo llevamos a capa vista mediante dto
	
	public void actualizaEmpleado(Empleado empleado); //Actualizamos el empleado 

	public void eliminaEmpleado(Empleado empleado); //Borramos el empleado
	
	
}
