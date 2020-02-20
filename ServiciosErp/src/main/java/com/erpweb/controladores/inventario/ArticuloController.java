package com.erpweb.controladores.inventario;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://localhost:4200"}) //Conexion con angular 
@RestController
@RequestMapping("/articulos")
public class ArticuloController {

	@GetMapping("/articulo")
	public String getArticulo() {
		return "";
	}
	
	@GetMapping("/articulos")
	public String getArticulos() {
		return "";
	}
	
	@GetMapping("/crearArticulo")
	public String getCrearArticulo() {
		return "";
	}
	
	@PostMapping("/crearArticulo")
	public String postCrearArticulo() {
		return "";
	}
	
	@GetMapping("/editarArticulo")
	public String getEditarArticulo() {
		return "";
	}
	
	@PostMapping("/editarArticulo")
	public String postEditarArticulo() {
		return "";
	}
	
	@PostMapping("/eliminarArticulo")
	public String postEliminarArticulo() {
		return "";
	}
	
	
}
