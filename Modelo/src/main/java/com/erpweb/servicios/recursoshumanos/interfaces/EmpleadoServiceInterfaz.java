package com.erpweb.servicios.recursoshumanos.interfaces;

import com.erpweb.dto.EmpleadoDto;
import com.erpweb.entidades.recursoshumanos.Empleado;

public interface EmpleadoServiceInterfaz {

	public Boolean creaEmpleadoDesdeEmpleadoDto(EmpleadoDto empleadoDto); //Crea el empleado mediante dto
	
	public EmpleadoDto obtieneEmpleadoDto(Long id, Long empresaId); //Visualizar el empleado
	
	public Boolean actualizaEmpleado(EmpleadoDto empleadoDto); //Actualizamos el empleado 

	public Boolean eliminaEmpleado(Empleado empleado); //Borramos el empleado
	
	public Empleado obtieneEmpleado(Long id, Long empresaId); //Obtenemos el empleado de BBDD

}
