package com.erpweb.controladores.crm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erpweb.entidades.crm.Cliente;
import com.erpweb.validadores.crm.ClienteValidator;

@CrossOrigin(origins = {"http://localhost:4200"}) //Conexion con angular 
@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired private ClienteValidator clienteValidator;

	@GetMapping("/cliente")
	public String getCliente(  ) {
		return "";
	}
	
	@GetMapping("/clientes")
	public String getClientes(  ) {
		return "";
	}
	
	@GetMapping("/crearCliente")
	public String getCrearCliente(  ) {
		return "";
	}
	
	@PostMapping("/crearCliente")
	public String postCrearCliente( Cliente cliente, BindingResult result ) {
		
		this.clienteValidator.validate(cliente, result);
		
		if(	result.hasErrors() ) {
			
			return "";
		}
		
		return "";
	}
	
	@GetMapping("/editarCliente")
	public String getEditarCliente(  ) {
		return "";
	}
	
	@PostMapping("/editarCliente")
	public String postEditarCliente( Cliente cliente, BindingResult result ) {
		
		this.clienteValidator.validate(cliente, result);
		
		if(	result.hasErrors() ) {
			
			return "";
		}
		
		return "";
	}
	
	@PostMapping("/eliminarCliente")
	public String postEliminarCliente(  ) {
		return "";
	}
	
	
}
