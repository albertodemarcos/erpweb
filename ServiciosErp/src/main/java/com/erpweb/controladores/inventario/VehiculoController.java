package com.erpweb.controladores.inventario;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://localhost:4200"}) //Conexion con angular 
@RestController
@RequestMapping("/vehiculos")
public class VehiculoController {

	@GetMapping("/vehiculo")
	public String getVehiculo() {
		return "";
	}
	
	@GetMapping("/vehiculos")
	public String getVehiculos() {
		return "";
	}
	
	@GetMapping("/crearVehiculo")
	public String getCrearVehiculo() {
		return "";
	}
	
	@PostMapping("/crearVehiculo")
	public String postCrearVehiculo() {
		return "";
	}
	
	@GetMapping("/editarVehiculo")
	public String getEditarVehiculo() {
		return "";
	}
	
	@PostMapping("/editarVehiculo")
	public String postEditarVehiculo() {
		return "";
	}
	
	@PostMapping("/eliminarVehiculo")
	public String postEliminarVehiculo() {
		return "";
	}
	
	
}
