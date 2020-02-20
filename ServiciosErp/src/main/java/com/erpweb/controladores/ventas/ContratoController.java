package com.erpweb.controladores.ventas;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://localhost:4200"}) //Conexion con angular 
@RestController
@RequestMapping("/contratos")
public class ContratoController {

	@GetMapping("/contrato")
	public String getContrato() {
		return "";
	}
	
	@GetMapping("/contratos")
	public String getContratos() {
		return "";
	}
	
	@GetMapping("/crearContrato")
	public String getCrearContrato() {
		return "";
	}
	
	@PostMapping("/crearContrato")
	public String postCrearContrato() {
		return "";
	}
	
	@GetMapping("/editarContrato")
	public String getEditarContrato() {
		return "";
	}
	
	@PostMapping("/editarContrato")
	public String postEditarContrato() {
		return "";
	}
	
	@PostMapping("/eliminarContrato")
	public String postEliminarContrato() {
		return "";
	}
	
	
}
