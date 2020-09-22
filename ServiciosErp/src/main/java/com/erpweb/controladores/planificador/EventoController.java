package com.erpweb.controladores.planificador;

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

import com.erpweb.dto.EventoDto;
import com.erpweb.servicios.errores.ErroresService;
import com.erpweb.servicios.planificador.EventoService;
import com.erpweb.utiles.AccionRespuesta;
import com.erpweb.validadores.planificador.EventoValidator;

@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true") //Conexion con angular 
@RestController
@RequestMapping("/eventos")
public class EventoController {
	
	@Autowired 
	private EventoValidator eventoValidator;
	
	@Autowired
	private EventoService eventoService;
	
	@Autowired
	private ErroresService erroresService;

	@GetMapping("/evento/{eventoId}")
	public @ResponseBody AccionRespuesta getEvento( @PathVariable Long eventoId) throws Exception {
		
		return this.eventoService.getEvento(eventoId);
	}
	
	@GetMapping("/listado.json")
	public @ResponseBody List<EventoDto> getEventos() {
		
		return this.eventoService.getListadoEventos();
	}
			
	@GetMapping( "/editarEvento/{eventoId}"  )
	public @ResponseBody AccionRespuesta getEditarEvento( @PathVariable Long eventoId) throws Exception {
		
		return this.eventoService.getEvento(eventoId);
	}
	
	@PostMapping( "/crearEvento" )
	public @ResponseBody AccionRespuesta postCrearEvento(@RequestBody EventoDto eventoDto, BindingResult result ) {
		
		this.eventoValidator.validate(eventoDto, result);
		
		if(	result.hasErrors() ) {
									
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE, this.erroresService.erroresValidacionEnDto(result) );
		}
		
		return this.eventoService.getCrearEditarEvento(eventoDto);
	}
	
	@PostMapping( "/editarEvento" )
	public @ResponseBody AccionRespuesta postEditarEvento(@RequestBody EventoDto eventoDto, BindingResult result ) {
		
		this.eventoValidator.validate(eventoDto, result);
		
		if(	result.hasErrors() ) {
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE, this.erroresService.erroresValidacionEnDto(result) );
		}
		
		return this.eventoService.getCrearEditarEvento(eventoDto);
	}
	
	@GetMapping("/eliminarEvento/{eventoId}")
	public @ResponseBody AccionRespuesta getEliminarEvento( @PathVariable Long eventoId) throws Exception {
		
		if(eventoId == null || eventoId.longValue() < 1L ) {
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
		
		return this.eventoService.eliminarEventoPorId(eventoId);
	}

}
