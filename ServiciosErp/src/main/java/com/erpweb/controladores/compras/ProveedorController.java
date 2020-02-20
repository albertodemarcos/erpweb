package com.erpweb.controladores.compras;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://localhost:4200"}) //Conexion con angular 
@RestController
@RequestMapping("/proveedores")
public class ProveedorController {

	@GetMapping("/proveedor")
	public String getProveedor() {
		return "";
	}
	
	@GetMapping("/proveedores")
	public String getProveedores() {
		return "";
	}
	
	@GetMapping("/crearProveedor")
	public String getCrearProveedor() {
		return "";
	}
	
	@PostMapping("/crearProveedor")
	public String postCrearProveedor() {
		return "";
	}
	
	@GetMapping("/editarProveedor")
	public String getEditarProveedor() {
		return "";
	}
	
	@PostMapping("/editarProveedor")
	public String postEditarProveedor() {
		return "";
	}
	
	@PostMapping("/eliminarProveedor")
	public String postEliminarProveedor() {
		return "";
	}
	
}
