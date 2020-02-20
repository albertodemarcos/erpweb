package com.erpweb.controladores.empresa;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://localhost:4200"}) //Conexion con angular 
@RestController
@RequestMapping("/configuraciones")
public class ConfiguracionController {

	@GetMapping("/configuracion")
	public String getConfiguracion() {
		return "";
	}
	
	@GetMapping("/configuraciones")
	public String getConfiguraciones() {
		return "";
	}
	
	@GetMapping("/crearConfiguracion")
	public String getCrearConfiguracion() {
		return "";
	}
	
	@PostMapping("/crearConfiguracion")
	public String postCrearConfiguracion() {
		return "";
	}
	
	@GetMapping("/editarConfiguracion")
	public String getEditarConfiguracion() {
		return "";
	}
	
	@PostMapping("/editarConfiguracion")
	public String postEditarConfiguracion() {
		return "";
	}
	
	@PostMapping("/eliminarConfiguracion")
	public String postEliminarConfiguracion() {
		return "";
	}
	
	
}
