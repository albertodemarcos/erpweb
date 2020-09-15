package com.erpweb.controladores.ventas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.erpweb.dto.ContratoDto;
import com.erpweb.servicios.errores.ErroresService;
import com.erpweb.servicios.ventas.ContratoService;
import com.erpweb.utiles.AccionRespuesta;
import com.erpweb.validadores.ventas.ContratoValidator;

@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true") 
@RestController
@RequestMapping("/contratos")
public class ContratoController {
	
	@Autowired 
	private ContratoValidator contratoValidator;
	
	@Autowired
	private ContratoService contratoService;
	
	@Autowired
	private ErroresService erroresService;

	@GetMapping("/contrato/{contratoId}")
	public @ResponseBody AccionRespuesta getContrato( @PathVariable Long contratoId) throws Exception {

		return this.contratoService.getContrato(contratoId);
	}
	
	@GetMapping("/listado.json")
	public @ResponseBody List<ContratoDto> getContratos( ) {
		
		return this.contratoService.getListadoContratos();
	}
	
	@GetMapping( "/crearContrato" )
	public @ResponseBody AccionRespuesta getCrearContrato( Model model) throws Exception {
		
		return new AccionRespuesta();
	}
	
	@GetMapping( "/editarContrato/{contratoId}" )
	public @ResponseBody AccionRespuesta getEditarContrato( @PathVariable Long contratoId) throws Exception {
		
		return this.contratoService.getContrato(contratoId);
	}
	
	@PostMapping( "/crearContrato" )
	public @ResponseBody AccionRespuesta postCrearContrato( @RequestBody ContratoDto contratoDto, BindingResult result ) {
		
		this.contratoValidator.validate(contratoDto, result);
		
		if(	result.hasErrors() ) {
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE, this.erroresService.erroresValidacionEnContratoDto(contratoDto, result) );
		}
		
		return this.contratoService.getCrearEditarContrato(contratoDto);
	}
	
	@PostMapping( "/editarContrato" )
	public @ResponseBody AccionRespuesta postEditarContrato( @RequestBody ContratoDto contratoDto, BindingResult result ) {
		
		this.contratoValidator.validate(contratoDto, result);
		
		if( result.hasErrors() ) {
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE, this.erroresService.erroresValidacionEnContratoDto(contratoDto, result) );
		}
		
		return this.contratoService.getCrearEditarContrato(contratoDto);
	}
	
	@GetMapping("/eliminarContrato/{contratoId}")
	public @ResponseBody AccionRespuesta getEliminarContrato( @PathVariable Long contratoId) throws Exception {
		
		if(contratoId == null || contratoId.longValue() < 1L ) {
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
		
		return this.contratoService.eliminarContratoPorId(contratoId);
	}
	
	
}
