package com.erpweb.controladores.inventario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erpweb.entidades.inventario.Articulo;
import com.erpweb.servicios.inventario.ArticuloService;
import com.erpweb.validadores.inventario.ArticuloValidator;

@CrossOrigin(origins = {"http://localhost:4200"}) //Conexion con angular 
@RestController
@RequestMapping("/articulos")
public class ArticuloController {
	
	@Autowired 
	private ArticuloValidator articuloValidator;

	@Autowired
	private ArticuloService articuloService;
	
	@GetMapping("/articulo")
	public String getArticulo(  ) {
		return "";
	}
	
	@GetMapping("/articulos")
	public String getArticulos(  ) {
		return "";
	}
	
	@GetMapping("/crearArticulo")
	public String getCrearArticulo(  ) {
		return "";
	}
	
	@PostMapping("/crearArticulo")
	public String postCrearArticulo( Articulo articulo, BindingResult result ) {
		
		this.articuloValidator.validate(articulo, result);
		
		if( result.hasErrors() ) {
			
			return "";
		}
		
		return "";
	}
	
	@GetMapping("/editarArticulo")
	public String getEditarArticulo(  ) {
		return "";
	}
	
	@PostMapping("/editarArticulo")
	public String postEditarArticulo( Articulo articulo, BindingResult result ) {
		
		this.articuloValidator.validate(articulo, result);
		
		if( result.hasErrors() ) {
			
			return "";
		}
		
		return "";
	}
	
	@PostMapping("/eliminarArticulo")
	public String postEliminarArticulo(  ) {
		return "";
	}
	
	
}
