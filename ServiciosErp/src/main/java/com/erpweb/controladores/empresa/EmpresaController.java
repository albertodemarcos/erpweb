package com.erpweb.controladores.empresa;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://localhost:4200"}) //Conexion con angular 
@RestController
@RequestMapping("/empresas")
public class EmpresaController {

	@GetMapping("/empresa")
	public String getEmpresa() {
		return "";
	}
	
	@GetMapping("/empresas")
	public String getEmpresas() {
		return "";
	}
	
	@GetMapping("/crearEmpresa")
	public String getCrearEmpresa() {
		return "";
	}
	
	@PostMapping("/crearEmpresa")
	public String postCrearEmpresa() {
		return "";
	}
	
	@GetMapping("/editarEmpresa")
	public String getEditarEmpresa() {
		return "";
	}
	
	@PostMapping("/editarEmpresa")
	public String postEditarEmpresa() {
		return "";
	}
	
	@PostMapping("/eliminarEmpresa")
	public String postEliminarEmpresa() {
		return "";
	}
	
	
}
