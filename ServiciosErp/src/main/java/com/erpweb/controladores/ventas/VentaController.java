package com.erpweb.controladores.ventas;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://localhost:4200"}) //Conexion con angular 
@RestController
@RequestMapping("/venta")
public class VentaController {

	@GetMapping("//venta")
	public String getVenta() {
		return "";
	}
	
	@GetMapping("//ventas")
	public String getVentas() {
		return "";
	}
	
	@GetMapping("/crearVenta")
	public String getCrearVenta() {
		return "";
	}
	
	@PostMapping("/crearVenta")
	public String postCrearVenta() {
		return "";
	}
	
	@GetMapping("/editarVenta")
	public String getEditarVenta() {
		return "";
	}
	
	@PostMapping("/editarVenta")
	public String postEditarVenta() {
		return "";
	}
	
	@PostMapping("/eliminarVenta")
	public String postEliminarVenta() {
		return "";
	}
	
	
}
