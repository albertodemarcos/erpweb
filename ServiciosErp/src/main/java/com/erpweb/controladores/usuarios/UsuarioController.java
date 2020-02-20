package com.erpweb.controladores.usuarios;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://localhost:4200"}) //Conexion con angular 
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@GetMapping("/usuario")
	public String getUsuario() {
		return "";
	}
	
	@GetMapping("/usuarios")
	public String getUsuarios() {
		return "";
	}
	
	@GetMapping("/crearUsuario")
	public String getCrearUsuario() {
		return "";
	}
	
	@PostMapping("/crearUsuario")
	public String postCrearUsuario() {
		return "";
	}
	
	@GetMapping("/editarUsuario")
	public String getEditarUsuario() {
		return "";
	}
	
	@PostMapping("/editarUsuario")
	public String postEditarUsuario() {
		return "";
	}
	
	@PostMapping("/eliminarUsuario")
	public String postEliminarUsuario() {
		return "";
	}
	
	
}
