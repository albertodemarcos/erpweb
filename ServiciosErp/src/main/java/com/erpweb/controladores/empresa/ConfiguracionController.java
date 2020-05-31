package com.erpweb.controladores.empresa;

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

import com.erpweb.dto.ConfiguracionDto;
import com.erpweb.entidades.usuarios.Usuario;
import com.erpweb.servicios.empresa.ConfiguracionService;
import com.erpweb.utiles.AccionRespuesta;
import com.erpweb.validadores.empresa.ConfiguracionValidator;

@CrossOrigin(origins = {"http://localhost:4200"}) //Conexion con angular 
@RestController
@RequestMapping("/configuraciones")
public class ConfiguracionController {
	
	@Autowired 
	private ConfiguracionValidator configuracionValidator;

	@Autowired
	private ConfiguracionService configuracionService;
	
	@GetMapping("/configuracion/{configuracionId}")
	public @ResponseBody AccionRespuesta getConfiguracion( @PathVariable Long configuracionId, Usuario user) throws Exception {
		
		return this.configuracionService.getConfiguracion(configuracionId, user);
	}
	
	@GetMapping("/listado")
	public String getConfiguraciones(  ) {
		
		
		return "";
	}
	
	@GetMapping( "/crearConfiguracion" )
	public @ResponseBody AccionRespuesta getCrearConfiguracion( Model model, Usuario user) throws Exception {
		
		return new AccionRespuesta();
	}
	
	@GetMapping( "/editarConfiguracion/{configuracionId}" )
	public @ResponseBody AccionRespuesta getEditarConfiguracion( @PathVariable Long configuracionId, Usuario user) throws Exception {
		
		return this.configuracionService.getConfiguracion(configuracionId, user);
	}
	
	@PostMapping( { "/crearConfiguracion/configuracion/{configuracionDto}.json", "/editarConfiguracion/configuracion/{configuracionDto}.json" } )
	public @ResponseBody AccionRespuesta postCrearConfiguracion( ConfiguracionDto configuracionDto, Usuario user, BindingResult result ) {
		
		this.configuracionValidator.validate(configuracionDto, result);
		
		if(	result.hasErrors()	) {
			
			return this.configuracionService.getConfiguracion(configuracionDto.getId(), user);
		}
		
		return this.configuracionService.getCrearEditarConfiguracion(configuracionDto, user);
	}
	
	@PostMapping("/eliminarConfiguracion/{configuracionId}")
	public @ResponseBody AccionRespuesta postEliminarConfiguracion( @PathVariable Long configuracionId, Usuario user) throws Exception {
		
		if(configuracionId == null || configuracionId.longValue() < 1L ) {
			
			return new AccionRespuesta();
		}
		
		return this.configuracionService.eliminarConfiguracionPorId(configuracionId);		
	}
	
	
}
