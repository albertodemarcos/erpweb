package com.erpweb.controladores.crm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.erpweb.entidades.crm.Cliente;
import com.erpweb.entidades.usuarios.Usuario;
import com.erpweb.servicios.crm.ClienteService;
import com.erpweb.utiles.AccionRespuesta;
import com.erpweb.validadores.crm.ClienteValidator;

@CrossOrigin(origins = {"http://localhost:4200"}) //Conexion con angular 
@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired 
	private ClienteValidator clienteValidator;
	
	@Autowired
	private ClienteService clienteService;

	@GetMapping("/cliente/{clienteId}")
	public @ResponseBody AccionRespuesta getCliente( @PathVariable Long clienteId, Usuario user ) throws Exception {
		
		return this.clienteService.getCliente(clienteId, user);
	}
	
	@GetMapping("/listado")
	public String getClientes(  ) {
		
		return "";
	}
	
	@GetMapping( { "/crearCliente", "/editarCliente" } )
	public @ResponseBody AccionRespuesta getCrearCliente(  @PathVariable Long clienteId, Usuario user ) throws Exception {
		
		return new AccionRespuesta();
	}
	
	@PostMapping( { "/crearCliente", "/editarCliente" } )
	public @ResponseBody AccionRespuesta postCrearCliente( Cliente cliente, BindingResult result ) {
		
		this.clienteValidator.validate(cliente, result);
		
		if(	result.hasErrors() ) {
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	@PostMapping("/eliminarCliente")
	public @ResponseBody AccionRespuesta postEliminarCliente(  ) {
		
		return new AccionRespuesta();
	}
	
	
}
