package com.erpweb.controladores.compras;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://localhost:4200"}) //Conexion con angular 
@RestController
@RequestMapping("/compras")
public class CompraController {

	@GetMapping("/compra")
	public String getCompra() {
		return "";
	}
	
	@GetMapping("/compras")
	public String getCompras() {
		return "";
	}
	
	@GetMapping("/crearCompra")
	public String getCrearCompra() {
		return "";
	}
	
	@PostMapping("/crearCompra")
	public String postCrearCompra() {
		return "";
	}
	
	@GetMapping("/editarCompra")
	public String getEditarCompra() {
		return "";
	}
	
	@PostMapping("/editarCompra")
	public String postEditarCompra() {
		return "";
	}
	
	@PostMapping("/eliminarCompra")
	public String postEliminarCompra() {
		return "";
	}
}
