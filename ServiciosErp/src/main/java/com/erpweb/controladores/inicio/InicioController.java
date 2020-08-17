package com.erpweb.controladores.inicio;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.erpweb.dto.UsuarioDto;
import com.erpweb.seguridad.AutenticacionRequest;
import com.erpweb.seguridad.SecurityService;
import com.erpweb.servicios.usuarios.LoginService;
import com.erpweb.utiles.AccionRespuesta;

@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true") //Conexion con angular 
@RestController
@RequestMapping("/inicio")
public class InicioController {
	
		
	@Autowired
    private LoginService loginService;
	
	@Autowired
    private SecurityService securityService;
	
	
	
	@GetMapping("/index")
	public String inicioWeb() {
		
		return "Hola Mundo!";
	}
	

	@PostMapping("/oldlogin")
	public @ResponseBody AccionRespuesta accesoUsuarios (@RequestBody UsuarioDto usuarioDto) throws Exception {

		return this.loginService.obtieneUsuarioDeUsername(usuarioDto);
	}
	
	@PostMapping("/login")
	public @ResponseBody AccionRespuesta incioERPWeb(@RequestBody AutenticacionRequest autenticacion) throws Exception {
		
		return this.securityService.obtieneRespuestaAccesoUsuario(autenticacion);
	}
	
	@GetMapping("/logout")
	public @ResponseBody AccionRespuesta terminarUsuarios () throws Exception {

		return null;
	}

	
	/*COMENTARIOS UTILES
	 
	  Al ser un REST CONTROLLER, NO DEVUELVE VISTAS!!!
	  CORS nos permite conectar spring con angular (servidores claro).
	  
	  @GetMapping
	  @PostMapping
	  
	 */
}
