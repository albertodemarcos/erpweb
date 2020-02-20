package com.erpweb.controladores.recursoshumanos;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://localhost:4200"}) //Conexion con angular 
@RestController
@RequestMapping("/empleados")
public class EmpleadoController {

	@GetMapping("/empleado")
	public String getEmpleado() {
		return "";
	}
	
	@GetMapping("/empleados")
	public String getEmpleados() {
		return "";
	}
	
	@GetMapping("/crearEmpleado")
	public String getCrearEmpleado() {
		return "";
	}
	
	@PostMapping("/crearEmpleado")
	public String postCrearEmpleado() {
		return "";
	}
	
	@GetMapping("/editarEmpleado")
	public String getEditarEmpleado() {
		return "";
	}
	
	@PostMapping("/editarEmpleado")
	public String postEditarEmpleado() {
		return "";
	}
	
	@PostMapping("/eliminarEmpleado")
	public String postEliminarEmpleado() {
		return "";
	}
	
	
}
