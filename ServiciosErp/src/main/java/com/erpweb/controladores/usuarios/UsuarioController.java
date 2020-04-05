package com.erpweb.controladores.usuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erpweb.entidades.usuarios.Usuario;
import com.erpweb.validadores.usuarios.UsuarioValidator;

@CrossOrigin(origins = {"http://localhost:4200"}) //Conexion con angular 
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired private UsuarioValidator usuarioValidator;

	@GetMapping("/usuario")
	public String getUsuario(  ) {
		return "";
	}
	
	@GetMapping("/usuarios")
	public String getUsuarios(  ) {
		return "";
	}
	
	@GetMapping("/crearUsuario")
	public String getCrearUsuario(  ) {
		return "";
	}
	
	@PostMapping("/crearUsuario")
	public String postCrearUsuario( Usuario usuario, BindingResult result ) {
		
		this.usuarioValidator.validate(usuario, result);
		
		if(	result.hasErrors() ) {
			
			return "";
		}
		
		return "";
	}
	
	@GetMapping("/editarUsuario")
	public String getEditarUsuario(  ) {
		return "";
	}
	
	@PostMapping("/editarUsuario")
	public String postEditarUsuario( Usuario usuario, BindingResult result ) {

		this.usuarioValidator.validate(usuario, result);
		
		if(	result.hasErrors() ) {
			
			return "";
		}
		
		return "";
	}
	
	@PostMapping("/eliminarUsuario")
	public String postEliminarUsuario(  ) {
		return "";
	}
	
	
}
