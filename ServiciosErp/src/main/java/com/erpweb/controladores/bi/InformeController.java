package com.erpweb.controladores.bi;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = {"http://localhost:4200"}) //Conexion con angular 
@RestController
@RequestMapping("/informes")
public class InformeController {

	@GetMapping("/informe")
	public String getInforme() {
		return "";
	}
	
	@GetMapping("/informes")
	public String getInformes() {
		return "";
	}
	
	@GetMapping("/crearInforme")
	public String getCrearInforme() {
		return "";
	}
	
	@PostMapping("/crearInforme")
	public String postCrearInforme() {
		return "";
	}
	
	@GetMapping("/editarInforme")
	public String getEditarInforme() {
		return "";
	}
	
	@PostMapping("/editarInforme")
	public String postEditarInforme() {
		return "";
	}
	
	@PostMapping("/eliminarInforme")
	public String postEliminarInforme() {
		return "";
	}
	
	
}
