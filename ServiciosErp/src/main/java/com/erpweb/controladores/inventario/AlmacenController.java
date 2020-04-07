package com.erpweb.controladores.inventario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erpweb.entidades.inventario.Almacen;
import com.erpweb.servicios.inventario.AlmacenService;
import com.erpweb.validadores.inventario.AlmacenValidator;

@CrossOrigin(origins = {"http://localhost:4200"}) //Conexion con angular 
@RestController
@RequestMapping("/almacenes")
public class AlmacenController {
	
	@Autowired 
	private AlmacenValidator almacenValidator;
	
	@Autowired
	private AlmacenService almacenService;

	@GetMapping("/almacen")
	public String getAlmacen(  ) {
		return "";
	}
	
	@GetMapping("/almacenes")
	public String getAlmacenes(  ) {
		return "";
	}
	
	@GetMapping("/crearAlmacen")
	public String getCrearAlmacen(  ) {
		return "";
	}
	
	@PostMapping("/crearAlmacen")
	public String postCrearAlmacen( Almacen almacen, BindingResult result ) {
		
		this.almacenValidator.validate(almacen, result);
		
		if( result.hasErrors() ) {
			
			return "";
		}
		
		return "";
	}
	
	@GetMapping("/editarAlmacen")
	public String getEditarAlmacen(  ) {
		return "";
	}
	
	@PostMapping("/editarAlmacen")
	public String postEditarAlmacen( Almacen almacen, BindingResult result ) {
		
		this.almacenValidator.validate(almacen, result);
		
		if( result.hasErrors() ) {
			
			return "";
		}
		
		return "";
	}
	
	@PostMapping("/eliminarAlmacen")
	public String postEliminarAlmacen(  ) {
		return "";
	}
	
	
}
