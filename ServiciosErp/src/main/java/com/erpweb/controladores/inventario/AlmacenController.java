package com.erpweb.controladores.inventario;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://localhost:4200"}) //Conexion con angular 
@RestController
@RequestMapping("/almacenes")
public class AlmacenController {

	@GetMapping("/almacen")
	public String getAlmacen() {
		return "";
	}
	
	@GetMapping("/almacenes")
	public String getAlmacenes() {
		return "";
	}
	
	@GetMapping("/crearAlmacen")
	public String getCrearAlmacen() {
		return "";
	}
	
	@PostMapping("/crearAlmacen")
	public String postCrearAlmacen() {
		return "";
	}
	
	@GetMapping("/editarAlmacen")
	public String getEditarAlmacen() {
		return "";
	}
	
	@PostMapping("/editarAlmacen")
	public String postEditarAlmacen() {
		return "";
	}
	
	@PostMapping("/eliminarAlmacen")
	public String postEliminarAlmacen() {
		return "";
	}
	
	
}
