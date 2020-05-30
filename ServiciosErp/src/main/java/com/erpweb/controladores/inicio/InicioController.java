package com.erpweb.controladores.inicio;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = {"http://localhost:4200"}) //Conexion con angular 
@RestController
@RequestMapping("/inicio")
public class InicioController {
	
	//private Logger inicio ;
	
	@GetMapping("/index")
	public String inicioWeb() {
		
		return "Hola Mundo!";
	}
	
	/*COMENTARIOS UTILES
	 
	  Al ser un REST CONTROLLER, NO DEVUELVE VISTAS!!!
	  CORS nos permite conectar spring con angular (servidores claro).
	  
	  @GetMapping
	  @PostMapping
	  
	 */
}
