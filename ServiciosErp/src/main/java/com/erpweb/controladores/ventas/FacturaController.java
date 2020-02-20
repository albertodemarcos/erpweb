package com.erpweb.controladores.ventas;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://localhost:4200"}) //Conexion con angular 
@RestController
@RequestMapping("/facturas")
public class FacturaController {

	@GetMapping("/factura")
	public String getFactura() {
		return "";
	}
	
	@GetMapping("/facturas")
	public String getFacturas() {
		return "";
	}
	
	@GetMapping("/crearFactura")
	public String getCrearFactura() {
		return "";
	}
	
	@PostMapping("/crearFactura")
	public String postCrearFactura() {
		return "";
	}
	
	@GetMapping("/editarFactura")
	public String getEditarFactura() {
		return "";
	}
	
	@PostMapping("/editarFactura")
	public String postEditarFactura() {
		return "";
	}
	
	@PostMapping("/eliminarFactura")
	public String postEliminarFactura() {
		return "";
	}
	
	
}
