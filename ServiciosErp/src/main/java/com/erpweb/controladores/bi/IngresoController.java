package com.erpweb.controladores.bi;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://localhost:4200"}) //Conexion con angular 
@RestController
@RequestMapping("/ingresos")
public class IngresoController {

	@GetMapping("/ingreso")
	public String getIngreso() {
		return "";
	}
	
	@GetMapping("/ingresos")
	public String getIngresos() {
		return "";
	}
	
	@GetMapping("/crearIngreso")
	public String getCrearIngreso() {
		return "";
	}
	
	@PostMapping("/crearIngreso")
	public String postCrearIngreso() {
		return "";
	}
	
	@GetMapping("/editarIngreso")
	public String getEditarIngreso() {
		return "";
	}
	
	@PostMapping("/editarIngreso")
	public String postEditarIngreso() {
		return "";
	}
	
	@PostMapping("/eliminarIngreso")
	public String postEliminarIngreso() {
		return "";
	}
}
