package com.erpweb.controladores.empresa;

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

import com.erpweb.dto.EmpleadoDto;
import com.erpweb.entidades.usuarios.Usuario;
import com.erpweb.servicios.empresa.EmpleadoService;
import com.erpweb.servicios.errores.ErroresService;
import com.erpweb.utiles.AccionRespuesta;
import com.erpweb.validadores.empresa.EmpleadoValidator;

@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true") //Conexion con angular 
@RestController
@RequestMapping("/empleados")
public class EmpleadoController {
	
	@Autowired 
	private EmpleadoValidator empleadoValidator;
	
	@Autowired
	private EmpleadoService empleadoService;
	
	@Autowired
	private ErroresService erroresService;

	@GetMapping("/empleado/{empleadoId}")
	public @ResponseBody AccionRespuesta getEmpleado( @PathVariable Long empleadoId, Usuario user) throws Exception {
		
		return this.empleadoService.getEmpleado(empleadoId, user);
	}
	
	@GetMapping("/listado.json")
	public @ResponseBody List<EmpleadoDto> getEmpleados() {
		
		return this.empleadoService.getListadoEmpleados();
	}
	
	@GetMapping( "/editarEmpleado/{empleadoId}" )
	public @ResponseBody AccionRespuesta getEditarEmpleado( @PathVariable Long empleadoId, Usuario user) throws Exception {
		
		return this.empleadoService.getEmpleado(empleadoId, user);
	}
	
	@PostMapping( "/crearEmpleado" )
	public @ResponseBody AccionRespuesta postCrearEmpleado( @RequestBody EmpleadoDto empleadoDto, BindingResult result ) {
		
		Usuario user = new Usuario();
		
		this.empleadoValidator.validate(empleadoDto, result);
		
		if( result.hasErrors() ) {
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE,  this.erroresService.erroresValidacionEnDto(result));
		}
		
		return this.empleadoService.getCrearEditarEmpleado(empleadoDto, user);
	}
	
	@PostMapping( "/editarEmpleado" )
	public @ResponseBody AccionRespuesta postEditarEmpleado( @RequestBody EmpleadoDto empleadoDto, Usuario user, BindingResult result ) {
		
		this.empleadoValidator.validate(empleadoDto, result);
		
		if( result.hasErrors() ) {
			
			return this.empleadoService.getEmpleado(empleadoDto.getId(), user);
		}
		
		return this.empleadoService.getCrearEditarEmpleado(empleadoDto, user);
	}
	
	@GetMapping("/eliminarEmpleado/{empleadoId}")
	public @ResponseBody AccionRespuesta getEliminarEmpleado( @PathVariable Long empleadoId, Usuario user) throws Exception {
		
		if(empleadoId == null || empleadoId.longValue() < 1L ) {
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
		
		return this.empleadoService.eliminarEmpleadoPorId(empleadoId);
	}
	
	
}
