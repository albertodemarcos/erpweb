package com.erpweb.controladores.crm;

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
import com.erpweb.servicios.crm.ClienteService;
import com.erpweb.servicios.errores.ErroresService;
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
	
	@Autowired
	private ErroresService erroresService;

	@GetMapping("/cliente/{clienteId}")
	public @ResponseBody AccionRespuesta getCliente( @PathVariable Long clienteId ) throws Exception {
		
		return this.clienteService.getCliente(clienteId);
	}
	
	@GetMapping("/listado.json")
	public @ResponseBody List<ClienteDto> getClientes() {
		
		return this.clienteService.getListadoClientes();
	}
	
	@GetMapping( "/editarCliente/{clienteId}" )
	public @ResponseBody AccionRespuesta getEditarCliente(  @PathVariable Long clienteId ) throws Exception {
		
		return this.clienteService.getCliente(clienteId);
	}
	
	@PostMapping( { "/crearCliente" } )
	public @ResponseBody AccionRespuesta postCrearCliente(@RequestBody ClienteDto clienteDto, BindingResult result ) {
		
		this.clienteValidator.validate(clienteDto, result);
		
		if(	result.hasErrors() ) {
						
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE,  erroresService.erroresValidacionEnDto(result) );
		}
		
		return this.clienteService.getCrearEditarCliente(clienteDto);
	}
	
	@PostMapping( "/editarCliente" )
	public @ResponseBody AccionRespuesta postEditarCliente( @RequestBody ClienteDto clienteDto, BindingResult result ) throws Exception {
		
		this.clienteValidator.validate(clienteDto, result);
		
		if(	result.hasErrors() ) {
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE, this.erroresService.erroresValidacionEnDto(result) );
		}
		
		return this.clienteService.getCrearEditarCliente(clienteDto);
	}
	
	@GetMapping("/eliminarCliente/{clienteId}")
	public @ResponseBody AccionRespuesta getEliminarCliente( @PathVariable Long clienteId ) throws Exception {
		
		if(clienteId == null || clienteId.longValue() < 1L ) {
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
		
		return this.clienteService.eliminarClientePorId(clienteId);
	}
	
	
	
}
