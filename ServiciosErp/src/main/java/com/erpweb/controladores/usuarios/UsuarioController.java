package com.erpweb.controladores.usuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.erpweb.entidades.usuarios.Usuario;
import com.erpweb.servicios.usuarios.UsuarioService;
import com.erpweb.utiles.AccionRespuesta;
import com.erpweb.validadores.usuarios.UsuarioValidator;

@CrossOrigin(origins = {"http://localhost:4200"}) //Conexion con angular 
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
	
	@GetMapping( { "/crearUsuario" , "/editarUsuario" } )
	public @ResponseBody AccionRespuesta getCrearUsuario( @PathVariable Long usuarioId, Usuario user) throws Exception {
		
		
		return new AccionRespuesta();
	}
	
	@PostMapping( { "/crearUsuario" , "/editarUsuario" } )
	public @ResponseBody AccionRespuesta postCrearUsuario( Usuario usuario, BindingResult result ) {
		
		this.usuarioValidator.validate(usuario, result);
		
		if(	result.hasErrors() ) {
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	@PostMapping("/eliminarUsuario")
	public @ResponseBody AccionRespuesta postEliminarUsuario(  ) {
		
		
		return new AccionRespuesta();
	}
	
	
}
