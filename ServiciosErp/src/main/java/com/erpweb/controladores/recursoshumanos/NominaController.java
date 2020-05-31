package com.erpweb.controladores.recursoshumanos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.erpweb.dto.NominaDto;
import com.erpweb.entidades.usuarios.Usuario;
import com.erpweb.servicios.recursoshumanos.NominaService;
import com.erpweb.utiles.AccionRespuesta;
import com.erpweb.validadores.recursoshumanos.NominaValidator;

@CrossOrigin(origins = {"http://localhost:4200"}) //Conexion con angular 
@RestController
@RequestMapping("/nominas")
public class NominaController {
	
	@Autowired 
	private NominaValidator nominaValidator;
	
	@Autowired
	private NominaService nominaService;

	@GetMapping("/nomina/{nominaId}")
	public @ResponseBody AccionRespuesta getNomina( @PathVariable Long nominaId, Usuario user) throws Exception {
		
		return this.nominaService.getNomina(nominaId, user);
	}
	
	@GetMapping("/listado")
	public String getNominas(  ) {
		
		return "";
	}
	
	@GetMapping( "/crearNomina" )
	public @ResponseBody AccionRespuesta getCrearNomina( @PathVariable Long nominaId, Usuario user) throws Exception {
		
		return new AccionRespuesta();
	}
	
	@GetMapping( "/editarNomina/{nominaId}"  )
	public @ResponseBody AccionRespuesta getEditarNomina( @PathVariable Long nominaId, Usuario user) throws Exception {
		
		return this.nominaService.getNomina(nominaId, user);
	}
	
	@PostMapping( { "/crearNomina/nomina/{nominaDto}.json", "/editarNomina/nomina/{nominaDto}.json" } )
	public @ResponseBody AccionRespuesta postCrearNomina( NominaDto nominaDto, Usuario user, BindingResult result ) {
		
		this.nominaValidator.validate(nominaDto, result);
		
		if( result.hasErrors() ) {
			
			return this.nominaService.getNomina(nominaDto.getId(), user);
		}
		
		return this.nominaService.getCrearEditarNomina(nominaDto, user);
	}
	
	@PostMapping("/eliminarNomina/{nominaId}")
	public @ResponseBody AccionRespuesta postEliminarNomina( @PathVariable Long nominaId, Usuario user) throws Exception {
		
		if(nominaId == null || nominaId.longValue() < 1L ) {
			
			return new AccionRespuesta();
		}
		
		return this.nominaService.eliminarNominaPorId(nominaId);
	}
	
	
}
