package com.erpweb.controladores.bi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erpweb.entidades.bi.Ingreso;
import com.erpweb.validadores.bi.IngresoValidator;

@CrossOrigin(origins = {"http://localhost:4200"}) //Conexion con angular 
@RestController
@RequestMapping("/ingresos")
public class IngresoController {
	
	@Autowired private IngresoValidator ingresoValidator;

	@GetMapping("/ingreso")
	public String getIngreso() {
		return "";
	}
	
	@GetMapping("/ingresos")
	public String getIngresos() {
		return "";
	}
	
	@GetMapping("/crearIngreso")
	public String getCrearIngreso() {
		return "";
	}
	
	@PostMapping("/crearIngreso")
	public String postCrearIngreso( Ingreso ingreso, BindingResult result ) {
		
		this.ingresoValidator.validate(ingreso, result);
		
		if( result.hasErrors() ) {
			return "";
		}
		
		return "";
	}
	
	@GetMapping("/editarIngreso")
	public String getEditarIngreso() {
		return "";
	}
	
	@PostMapping("/editarIngreso")
	public String postEditarIngreso( Ingreso ingreso, BindingResult result) {
		
		this.ingresoValidator.validate(ingreso, result);
		
		if( result.hasErrors() ) {
			return "";
		}
		
		return "";
	}
	
	@PostMapping("/eliminarIngreso")
	public String postEliminarIngreso() {
		return "";
	}
}
