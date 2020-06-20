package com.erpweb.controladores.usuarios;

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

import com.erpweb.dto.UsuarioDto;
import com.erpweb.entidades.usuarios.Usuario;
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

	@GetMapping("/usuario/{usuarioId}")
	public @ResponseBody AccionRespuesta getUsuario( @PathVariable Long usuarioId, Usuario user) throws Exception {
		
		return this.usuarioService.getUsuario(usuarioId, user);
	}
	
	@GetMapping("/listado")
	public String getUsuarios(  ) {
		
		
		return "";
	}
	
	@GetMapping( "/crearUsuario" )
	public @ResponseBody AccionRespuesta getCrearUsuario( Model model, Usuario user) throws Exception {
		
		return new AccionRespuesta();
	}
	
	@GetMapping( "/editarUsuario/{usuarioId}" )
	public @ResponseBody AccionRespuesta getEditarUsuario( @PathVariable Long usuarioId, Usuario user) throws Exception {
		
		return this.usuarioService.getUsuario(usuarioId, user);
	}
	
	@PostMapping( { "/crearUsuario/usuario/{usuarioDto}.json" , "/editarUsuario/usuario/{usuarioDto}.json" } )
	public @ResponseBody AccionRespuesta postCrearUsuario( UsuarioDto usuarioDto, Usuario user, BindingResult result ) {
		
		this.usuarioValidator.validate(usuarioDto, result);
		
		if(	result.hasErrors() ) {
			
			return this.usuarioService.getUsuario(usuarioDto.getId(), user);
		}
		
		return this.usuarioService.getCrearEditarUsuario(usuarioDto, user);
	}
	
	@PostMapping("/eliminarUsuario/{usuarioId}")
	public @ResponseBody AccionRespuesta postEliminarUsuario( @PathVariable Long usuarioId, Usuario user) throws Exception {
		
		if(usuarioId == null || usuarioId.longValue() < 1L ) {
			
			return new AccionRespuesta();
		}
		
		return this.usuarioService.eliminarUsuarioPorId(usuarioId);
	}
	
	
}
