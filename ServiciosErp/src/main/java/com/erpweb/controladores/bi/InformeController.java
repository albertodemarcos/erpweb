package com.erpweb.controladores.bi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erpweb.entidades.bi.Informe;
import com.erpweb.servicios.bi.InformeService;
import com.erpweb.validadores.bi.InformeValidator;


@CrossOrigin(origins = {"http://localhost:4200"}) //Conexion con angular 
@RestController
@RequestMapping("/informes")
public class InformeController {
	
	@Autowired 
	private InformeValidator informeValidator;
	
	@Autowired
	private InformeService informeService;

	@GetMapping("/informe")
	public String getInforme() {
		return "";
	}
	
	@GetMapping("/informes")
	public String getInformes() {
		return "";
	}
	
	@GetMapping("/crearInforme")
	public String getCrearInforme() {
		return "";
	}
	
	@PostMapping("/crearInforme")
	public String postCrearInforme( Informe informe, BindingResult result) {
		
		this.informeValidator.validate(informe, result);
		
		if( result.hasErrors() ) {
			return "";
		}
		
		return "";
	}
	
	@GetMapping("/editarInforme")
	public String getEditarInforme() {
		return "";
	}
	
	@PostMapping("/editarInforme")
	public String postEditarInforme( Informe informe, BindingResult result) {
		
		this.informeValidator.validate(informe, result);
		
		if( result.hasErrors() ) {
			return "";
		}
		
		return "";
	}
	
	@PostMapping("/eliminarInforme")
	public String postEliminarInforme() {
		return "";
	}
	
	
}
