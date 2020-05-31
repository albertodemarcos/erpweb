package com.erpweb.controladores.recursoshumanos;

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

import com.erpweb.entidades.recursoshumanos.Empleado;
import com.erpweb.entidades.usuarios.Usuario;
import com.erpweb.servicios.recursoshumanos.EmpleadoService;
import com.erpweb.utiles.AccionRespuesta;
import com.erpweb.validadores.recursoshumanos.EmpleadoValidator;

@CrossOrigin(origins = {"http://localhost:4200"}) //Conexion con angular 
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
	
	@GetMapping("/listado")
	public String getEmpleados(  ) {
		
		return "";
	}
	
	@GetMapping( "/crearEmpleado" )
	public @ResponseBody AccionRespuesta getCrearEmpleado( Model model, Usuario user) throws Exception {
		
		return new AccionRespuesta();
	}
	
	@GetMapping( "/editarEmpleado/{empleadoId}" )
	public @ResponseBody AccionRespuesta getEditarEmpleado( @PathVariable Long empleadoId, Usuario user) throws Exception {
		
		return this.empleadoService.getEmpleado(empleadoId, user);
	}
	
	@PostMapping( { "/crearEmpleado", "/editarEmpleado" } )
	public @ResponseBody AccionRespuesta postCrearEmpleado( Empleado empleado, BindingResult result ) {
		
		this.empleadoValidator.validate(empleado, result);
		
		if( result.hasErrors() ) {
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	@PostMapping("/eliminarEmpleado")
	public @ResponseBody AccionRespuesta postEliminarEmpleado(  ) {
		
		
		return new AccionRespuesta();
	}
	
	
}
