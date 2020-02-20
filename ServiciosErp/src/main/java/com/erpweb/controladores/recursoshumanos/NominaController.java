package com.erpweb.controladores.recursoshumanos;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://localhost:4200"}) //Conexion con angular 
@RestController
@RequestMapping("/nominas")
public class NominaController {

	@GetMapping("/nomina")
	public String getNomina() {
		return "";
	}
	
	@GetMapping("/nominas")
	public String getNominas() {
		return "";
	}
	
	@GetMapping("/crearNomina")
	public String getCrearNomina() {
		return "";
	}
	
	@PostMapping("/crearNomina")
	public String postCrearNomina() {
		return "";
	}
	
	@GetMapping("/editarNomina")
	public String getEditarNomina() {
		return "";
	}
	
	@PostMapping("/editarNomina")
	public String postEditarNomina() {
		return "";
	}
	
	@PostMapping("/eliminarNomina")
	public String postEliminarNomina() {
		return "";
	}
	
	
}
