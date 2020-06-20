package com.erpweb.controladores.empresa;

import java.util.List;

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

import com.erpweb.dto.EmpleadoDto;
import com.erpweb.entidades.usuarios.Usuario;
import com.erpweb.servicios.empresa.EmpleadoService;
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

	@GetMapping("/empleado/{empleadoId}")
	public @ResponseBody AccionRespuesta getEmpleado( @PathVariable Long empleadoId, Usuario user) throws Exception {
		
		return this.empleadoService.getEmpleado(empleadoId, user);
	}
	
	@GetMapping("/listado.json")
	public @ResponseBody List<EmpleadoDto> getEmpleados() {
		
		return this.empleadoService.getListadoEmpleados();
	}
	
	@GetMapping( "/crearEmpleado" )
	public @ResponseBody AccionRespuesta getCrearEmpleado( Model model, Usuario user) throws Exception {
		
		return new AccionRespuesta();
	}
	
	@GetMapping( "/editarEmpleado/{empleadoId}" )
	public @ResponseBody AccionRespuesta getEditarEmpleado( @PathVariable Long empleadoId, Usuario user) throws Exception {
		
		return this.empleadoService.getEmpleado(empleadoId, user);
	}
	
	@PostMapping( { "/crearEmpleado/empleado/{empleadoDto}.json", "/editarEmpleado/empleado/{empleadoDto}.json" } )
	public @ResponseBody AccionRespuesta postCrearEmpleado( EmpleadoDto empleadoDto, Usuario user, BindingResult result ) {
		
		this.empleadoValidator.validate(empleadoDto, result);
		
		if( result.hasErrors() ) {
			
			return this.empleadoService.getEmpleado(empleadoDto.getId(), user);
		}
		
		return this.empleadoService.getCrearEditarEmpleado(empleadoDto, user);
	}
	
	@PostMapping("/eliminarEmpleado/{empleadoId}")
	public @ResponseBody AccionRespuesta postEliminarEmpleado( @PathVariable Long empleadoId, Usuario user) throws Exception {
		
		if(empleadoId == null || empleadoId.longValue() < 1L ) {
			
			return new AccionRespuesta();
		}
		
		return this.empleadoService.eliminarEmpleadoPorId(empleadoId);
	}
	
	
}
