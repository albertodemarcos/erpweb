package com.erpweb.controladores.recursoshumanos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erpweb.entidades.recursoshumanos.Nomina;
import com.erpweb.validadores.recursoshumanos.NominaValidator;

@CrossOrigin(origins = {"http://localhost:4200"}) //Conexion con angular 
@RestController
@RequestMapping("/nominas")
public class NominaController {
	
	@Autowired private NominaValidator nominaValidator;

	@GetMapping("/nomina")
	public String getNomina(  ) {
		return "";
	}
	
	@GetMapping("/nominas")
	public String getNominas(  ) {
		return "";
	}
	
	@GetMapping("/crearNomina")
	public String getCrearNomina(  ) {
		return "";
	}
	
	@PostMapping("/crearNomina")
	public String postCrearNomina( Nomina nomina, BindingResult result ) {
		
		this.nominaValidator.validate(nomina, result);
		
		if( result.hasErrors() ) {
			
			return "";
		}
		
		return "";
	}
	
	@GetMapping("/editarNomina")
	public String getEditarNomina(  ) {
		return "";
	}
	
	@PostMapping("/editarNomina")
	public String postEditarNomina( Nomina nomina, BindingResult result ) {
		
		this.nominaValidator.validate(nomina, result);
		
		if( result.hasErrors() ) {
			
			return "";
		}
		
		return "";
	}
	
	@PostMapping("/eliminarNomina")
	public String postEliminarNomina(  ) {
		return "";
	}
	
	
}
