package com.erpweb.controladores.bi;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@CrossOrigin(origins = {"http://localhost:4200"}) //Conexion con angular 
@RestController
@RequestMapping("/gastos")
public class GastoController {
	
	
	@GetMapping("/gasto")
	public String getGasto() {
		return "";
	}
	
	@GetMapping("/gastos")
	public String getGastos() {
		return "";
	}
	
	@GetMapping("/crearGasto")
	public String getCrearGasto() {
		return "";
	}
	
	@PostMapping("/crearGasto")
	public String postCrearGasto() {
		return "";
	}
	
	@GetMapping("/editarGasto")
	public String getEditarGasto() {
		return "";
	}
	
	@PostMapping("/editarGasto")
	public String postEditarGasto() {
		return "";
	}
	
	@PostMapping("/eliminarGasto")
	public String postEliminarGasto() {
		return "";
	}

}
