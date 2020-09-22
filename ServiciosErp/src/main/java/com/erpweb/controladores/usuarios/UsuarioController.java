package com.erpweb.controladores.usuarios;

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

import com.erpweb.dto.UsuarioDto;
import com.erpweb.servicios.errores.ErroresService;
import com.erpweb.servicios.usuarios.UsuarioService;
import com.erpweb.utiles.AccionRespuesta;
import com.erpweb.validadores.usuarios.UsuarioValidator;

@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true") //Conexion con angular 
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired 
	private UsuarioValidator usuarioValidator;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ErroresService erroresService;

	@GetMapping("/usuario/{usuarioId}")
	public @ResponseBody AccionRespuesta getUsuario( @PathVariable Long usuarioId) throws Exception {
		
		return this.usuarioService.getUsuario(usuarioId);
	}
	
	
	@GetMapping("/listado.json")
	public @ResponseBody List<UsuarioDto> getUsuarios( ) throws Exception {
		
		return this.usuarioService.getListadoUsuarios();
	}
	
	@GetMapping( "/editarUsuario/{usuarioId}" )
	public @ResponseBody AccionRespuesta getEditarUsuario( @PathVariable Long usuarioId) throws Exception {
		
		return this.usuarioService.getUsuario(usuarioId);
	}
	
	
	@PostMapping( "/crearUsuario" )
	public @ResponseBody AccionRespuesta postCrearUsuario( @RequestBody UsuarioDto usuarioDto, BindingResult result ) {
		
		this.usuarioValidator.validate(usuarioDto, result);
		
		if(	result.hasErrors() ) {
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE, this.erroresService.erroresValidacionEnDto(result) );
		}
		
		return this.usuarioService.getCrearEditarUsuario(usuarioDto);
	}
	
	@PostMapping( "/editarUsuario"  )
	public @ResponseBody AccionRespuesta postEditarUsuario( @RequestBody UsuarioDto usuarioDto, BindingResult result ) {
		
		this.usuarioValidator.validate(usuarioDto, result);
		
		if(	result.hasErrors() ) {
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE, this.erroresService.erroresValidacionEnDto(result) );
		}
		
		return this.usuarioService.getCrearEditarUsuario(usuarioDto);
	}
	
	
	@GetMapping("/eliminarUsuario/{usuarioId}")
	public @ResponseBody AccionRespuesta getEliminarUsuario( @PathVariable Long usuarioId) throws Exception {
		
		if(usuarioId == null || usuarioId.longValue() < 1L ) {
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
		
		return this.usuarioService.eliminarUsuarioPorId(usuarioId);
	}
	
	
}
