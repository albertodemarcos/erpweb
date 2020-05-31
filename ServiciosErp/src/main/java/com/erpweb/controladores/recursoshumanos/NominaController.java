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

import com.erpweb.entidades.recursoshumanos.Nomina;
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
	
	@PostMapping( { "/crearNomina", "/editarNomina" } )
	public @ResponseBody AccionRespuesta postCrearNomina( Nomina nomina, BindingResult result ) {
		
		this.nominaValidator.validate(nomina, result);
		
		if( result.hasErrors() ) {
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	@PostMapping("/eliminarNomina/{nominaId}")
	public @ResponseBody AccionRespuesta postEliminarNomina( @PathVariable Long nominaId, Usuario user) throws Exception {
		
		if(nominaId == null || nominaId.longValue() < 1L ) {
			
			return new AccionRespuesta();
		}
		
		return this.nominaService.eliminarNominaPorId(nominaId);
	}
	
	
}
