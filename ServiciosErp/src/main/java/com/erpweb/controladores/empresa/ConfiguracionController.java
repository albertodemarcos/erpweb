package com.erpweb.controladores.empresa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erpweb.entidades.empresa.Configuracion;
import com.erpweb.servicios.empresa.ConfiguracionService;
import com.erpweb.validadores.empresa.ConfiguracionValidator;

@CrossOrigin(origins = {"http://localhost:4200"}) //Conexion con angular 
@RestController
@RequestMapping("/configuraciones")
public class ConfiguracionController {
	
	@Autowired 
	private ConfiguracionValidator configuracionValidator;

	@Autowired
	private ConfiguracionService configuracionService;
	
	@GetMapping("/configuracion")
	public String getConfiguracion(  ) {
		return "";
	}
	
	@GetMapping("/configuraciones")
	public String getConfiguraciones(  ) {
		return "";
	}
	
	@GetMapping("/crearConfiguracion")
	public String getCrearConfiguracion(  ) {
		return "";
	}
	
	@PostMapping("/crearConfiguracion")
	public String postCrearConfiguracion( Configuracion configuracion, BindingResult result ) {
		
		this.configuracionValidator.validate(configuracion, result);
		
		if(	result.hasErrors()	) {
			
			return "";
		}
		
		return "";
	}
	
	@GetMapping("/editarConfiguracion")
	public String getEditarConfiguracion(  ) {
		return "";
	}
	
	@PostMapping("/editarConfiguracion")
	public String postEditarConfiguracion( Configuracion configuracion, BindingResult result ) {
		
		this.configuracionValidator.validate(configuracion, result);
		
		if(	result.hasErrors() ) {
			
			return "";
		}
		
		return "";
	}
	
	@PostMapping("/eliminarConfiguracion")
	public String postEliminarConfiguracion(  ) {
		return "";
	}
	
	
}
