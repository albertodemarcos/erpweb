package com.erpweb.controladores.bi;

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

import com.erpweb.dto.IngresoDto;
import com.erpweb.entidades.usuarios.Usuario;
import com.erpweb.servicios.bi.IngresoService;
import com.erpweb.utiles.AccionRespuesta;
import com.erpweb.validadores.bi.IngresoValidator;

@CrossOrigin(origins = {"http://localhost:4200"}) //Conexion con angular 
@RestController
@RequestMapping("/ingresos")
public class IngresoController {
	
	@Autowired
	private IngresoValidator ingresoValidator;
	
	@Autowired
	private IngresoService ingresoService;

	@GetMapping("/ingreso/{ingresoId}")
	public @ResponseBody AccionRespuesta getIngreso(@PathVariable Long ingresoId, Usuario user) {
		
		return this.ingresoService.getIngreso(ingresoId, user);
	}
	
	@GetMapping("/listado")
	public String getIngresos() {
		
		
		return "";
	}
	
	@GetMapping( "/crearIngreso" )
	public @ResponseBody AccionRespuesta getCrearIngreso( Model model, Usuario user ) throws Exception {
		
		return new AccionRespuesta();
	}
	
	@GetMapping( "/editarIngreso/{ingresoId}" )
	public @ResponseBody AccionRespuesta getEditarIngreso( @PathVariable Long ingresoId, Usuario user ) throws Exception {
		
		return this.ingresoService.getIngreso(ingresoId, user);
	}
	
	@PostMapping( { "/crearIngreso/ingreso/{ingresoDto}.json", "/editarIngreso/ingreso/{ingresoDto}.json" } )
	public @ResponseBody AccionRespuesta postCrearIngreso( IngresoDto ingresoDto, Usuario user, BindingResult result ) {
		
		this.ingresoValidator.validate(ingresoDto, result);
		
		if( result.hasErrors() ) {
			
			return this.ingresoService.getIngreso(ingresoDto.getId(), user);
		}
		
		return this.ingresoService.getCrearEditarIngreso(ingresoDto, user);
	}
	
	@PostMapping("/eliminarIngreso/{ingresoId}")
	public @ResponseBody AccionRespuesta postEliminarIngreso( @PathVariable Long ingresoId, Usuario user ) throws Exception {
		
		if(ingresoId == null || ingresoId.longValue() < 1L ) {
			
			return new AccionRespuesta();
		}
		
		return this.ingresoService.eliminarIngresoPorId(ingresoId);
	}
}
