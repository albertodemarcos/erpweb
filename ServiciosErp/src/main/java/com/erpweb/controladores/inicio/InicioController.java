package com.erpweb.controladores.inicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.erpweb.entidades.usuarios.Usuario;
import com.erpweb.servicios.usuarios.UsuarioService;


@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true") //Conexion con angular 
@RestController
@RequestMapping("/inicio")
public class InicioController {
	
	//private Logger inicio ;
	
	@Autowired
    private UsuarioService  usuarioService;
	
	@GetMapping("/index")
	public String inicioWeb() {
		
		return "Hola Mundo!";
	}
	
	@PostMapping({"/login"})
	public String loginWeb(@RequestBody Usuario usuario) {
		
		String usuarioname = usuario.getUsername();
		
		String password = usuario.getPassword();
				
				
		UserDetails usuarioEncontrado = usuarioService.loadUserByUsername(usuarioname);
		
		if(usuarioEncontrado.getUsername().equals(usuarioname) 
				&& usuarioEncontrado.getPassword().equals(password)) {
			return "true";
		}
		
		return "Hola Mundo!";
	}
	
	/*COMENTARIOS UTILES
	 
	  Al ser un REST CONTROLLER, NO DEVUELVE VISTAS!!!
	  CORS nos permite conectar spring con angular (servidores claro).
	  
	  @GetMapping
	  @PostMapping
	  
	 */
}
