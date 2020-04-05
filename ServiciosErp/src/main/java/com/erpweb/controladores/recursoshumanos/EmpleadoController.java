package com.erpweb.controladores.recursoshumanos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erpweb.entidades.recursoshumanos.Empleado;
import com.erpweb.validadores.recursoshumanos.EmpleadoValidator;

@CrossOrigin(origins = {"http://localhost:4200"}) //Conexion con angular 
@RestController
@RequestMapping("/empleados")
public class EmpleadoController {
	
	@Autowired private EmpleadoValidator empleadoValidator;

	@GetMapping("/empleado")
	public String getEmpleado(  ) {
		return "";
	}
	
	@GetMapping("/empleados")
	public String getEmpleados(  ) {
		return "";
	}
	
	@GetMapping("/crearEmpleado")
	public String getCrearEmpleado(  ) {
		return "";
	}
	
	@PostMapping("/crearEmpleado")
	public String postCrearEmpleado( Empleado empleado, BindingResult result ) {
		
		this.empleadoValidator.validate(empleado, result);
		
		if( result.hasErrors() ) {
			
			return "";
		}
		
		return "";
	}
	
	@GetMapping("/editarEmpleado")
	public String getEditarEmpleado(  ) {
		return "";
	}
	
	@PostMapping("/editarEmpleado")
	public String postEditarEmpleado( Empleado empleado, BindingResult result ) {

		this.empleadoValidator.validate(empleado, result);
		
		if( result.hasErrors() ) {
			
			return "";
		}
		
		return "";
	}
	
	@PostMapping("/eliminarEmpleado")
	public String postEliminarEmpleado(  ) {
		return "";
	}
	
	
}
