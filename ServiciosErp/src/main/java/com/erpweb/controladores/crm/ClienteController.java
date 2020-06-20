package com.erpweb.controladores.crm;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.erpweb.dto.ClienteDto;
import com.erpweb.entidades.usuarios.Usuario;
import com.erpweb.servicios.crm.ClienteService;
import com.erpweb.utiles.AccionRespuesta;
import com.erpweb.validadores.crm.ClienteValidator;

/*,"http://localhost:4200", "http://127.0.0.1:4200", "http://192.168.1.39:4200"*/
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true" ) //Conexion con angular  
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
	
	@GetMapping("/listado.json")
	public @ResponseBody List<ClienteDto> getClientes() {
		
		return this.clienteService.getListadoClientes();
	}
	
	@GetMapping( "/editarCliente/{clienteId}" )
	public @ResponseBody AccionRespuesta getEditarCliente(  @PathVariable Long clienteId, Usuario user ) throws Exception {
		
		return this.clienteService.getCliente(clienteId, user);
	}
	
	@PostMapping( { "/crearCliente" } )
	public @ResponseBody AccionRespuesta postCrearCliente(@RequestBody ClienteDto clienteDto, BindingResult result ) {
		
		Usuario user = new Usuario();
		
		this.clienteValidator.validate(clienteDto, result);
		
		if(	result.hasErrors() ) {
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE, new HashMap<String, Object> (result.getModel()));
		}
		
		return this.clienteService.getCrearEditarCliente(clienteDto, user);
	}
	
	@GetMapping( "/editarCliente" )
	public @ResponseBody AccionRespuesta getCrearCliente( @RequestBody ClienteDto clienteDto, BindingResult result ) throws Exception {
		
		Usuario user = new Usuario();
		
		this.clienteValidator.validate(clienteDto, result);
		
		if(	result.hasErrors() ) {
			
			return this.clienteService.getCliente(clienteDto.getId(), user);
		}
		
		return this.clienteService.getCrearEditarCliente(clienteDto, user);
	}
	
	@PostMapping("/eliminarCliente")
	public @ResponseBody AccionRespuesta postEliminarCliente( @PathVariable Long clienteId, Usuario user ) throws Exception {
		
		if(clienteId == null || clienteId.longValue() < 1L ) {
			
			return new AccionRespuesta();
		}
		
		return this.clienteService.eliminarClientePorId(clienteId);
	}
	
	
}
