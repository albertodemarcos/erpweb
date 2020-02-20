package com.erpweb.controladores.crm;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://localhost:4200"}) //Conexion con angular 
@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@GetMapping("/cliente")
	public String getCliente() {
		return "";
	}
	
	@GetMapping("/clientes")
	public String getClientes() {
		return "";
	}
	
	@GetMapping("/crearCliente")
	public String getCrearCliente() {
		return "";
	}
	
	@PostMapping("/crearCliente")
	public String postCrearCliente() {
		return "";
	}
	
	@GetMapping("/editarCliente")
	public String getEditarCliente() {
		return "";
	}
	
	@PostMapping("/editarCliente")
	public String postEditarCliente() {
		return "";
	}
	
	@PostMapping("/eliminarCliente")
	public String postEliminarCliente() {
		return "";
	}
	
	
}
