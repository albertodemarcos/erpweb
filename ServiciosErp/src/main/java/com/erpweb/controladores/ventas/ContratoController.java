package com.erpweb.controladores.ventas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.erpweb.entidades.usuarios.Usuario;
import com.erpweb.entidades.ventas.Contrato;
import com.erpweb.servicios.ventas.ContratoService;
import com.erpweb.utiles.AccionRespuesta;
import com.erpweb.validadores.ventas.ContratoValidator;

@CrossOrigin(origins = {"http://localhost:4200"}) //Conexion con angular 
@RestController
@RequestMapping("/contratos")
public class ContratoController {
	
	@Autowired 
	private ContratoValidator contratoValidator;
	
	@Autowired
	private ContratoService contratoService;

	@GetMapping("/contrato/{contratoId}")
	public @ResponseBody AccionRespuesta getContrato( @PathVariable Long contratoId, Usuario user) throws Exception {

		return this.contratoService.getContrato(contratoId, user);
	}
	
	@GetMapping("/listado")
	public String getContratos(  ) {
		
		
		return "";
	}
	
	@GetMapping( "/crearContrato" )
	public @ResponseBody AccionRespuesta getCrearContrato( Model model, Usuario user) throws Exception {
		
		return new AccionRespuesta();
	}
	
	@GetMapping( "/editarContrato/{contratoId}" )
	public @ResponseBody AccionRespuesta getEditarContrato( @PathVariable Long contratoId, Usuario user) throws Exception {
		
		return this.contratoService.getContrato(contratoId, user);
	}
	
	@PostMapping( { "/crearContrato", "/editarContrato" } )
	public @ResponseBody AccionRespuesta postCrearContrato( Contrato contrato, BindingResult result ) {
		
		this.contratoValidator.validate(contrato, result);
		
		if( result.hasErrors() ) {
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	@PostMapping("/eliminarContrato")
	public @ResponseBody AccionRespuesta postEliminarContrato(  ) {
		
		
		return new AccionRespuesta();
	}
	
	
}
