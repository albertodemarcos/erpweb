package com.erpweb.controladores.inventario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.erpweb.entidades.inventario.Almacen;
import com.erpweb.entidades.usuarios.Usuario;
import com.erpweb.servicios.inventario.AlmacenService;
import com.erpweb.utiles.AccionRespuesta;
import com.erpweb.validadores.inventario.AlmacenValidator;

@CrossOrigin(origins = {"http://localhost:4200"}) //Conexion con angular 
@RestController
@RequestMapping("/almacenes")
public class AlmacenController {
	
	@Autowired 
	private AlmacenValidator almacenValidator;
	
	@Autowired
	private AlmacenService almacenService;

	@GetMapping("/almacen/{almacenId}")
	public @ResponseBody AccionRespuesta getAlmacen( @PathVariable Long almacenId, Usuario user) throws Exception {
		
		return this.almacenService.getAlmacen(almacenId, user);
	}
	
	@GetMapping("/listado")
	public String getAlmacenes(  ) {
		
		
		return "";
	}
	
	@GetMapping("/crearAlmacen")
	public @ResponseBody AccionRespuesta getCrearAlmacen(  ) {
		
		return new AccionRespuesta();
	}
	
	@PostMapping("/crearAlmacen")
	public @ResponseBody AccionRespuesta postCrearAlmacen( Almacen almacen, BindingResult result ) {
		
		this.almacenValidator.validate(almacen, result);
		
		if( result.hasErrors() ) {
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	@GetMapping("/editarAlmacen")
	public @ResponseBody AccionRespuesta getEditarAlmacen(  ) {
		return new AccionRespuesta();
	}
	
	@PostMapping("/editarAlmacen")
	public @ResponseBody AccionRespuesta postEditarAlmacen( Almacen almacen, BindingResult result ) {
		
		this.almacenValidator.validate(almacen, result);
		
		if( result.hasErrors() ) {
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	@PostMapping("/eliminarAlmacen")
	public @ResponseBody AccionRespuesta postEliminarAlmacen(  ) {
		
		return new AccionRespuesta();
	}
	
	
}
