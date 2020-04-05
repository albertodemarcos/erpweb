package com.erpweb.controladores.ventas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erpweb.entidades.ventas.Contrato;
import com.erpweb.validadores.ventas.ContratoValidator;

@CrossOrigin(origins = {"http://localhost:4200"}) //Conexion con angular 
@RestController
@RequestMapping("/contratos")
public class ContratoController {
	
	@Autowired private ContratoValidator contratoValidator;

	@GetMapping("/contrato")
	public String getContrato(  ) {
		return "";
	}
	
	@GetMapping("/contratos")
	public String getContratos(  ) {
		return "";
	}
	
	@GetMapping("/crearContrato")
	public String getCrearContrato(  ) {
		return "";
	}
	
	@PostMapping("/crearContrato")
	public String postCrearContrato( Contrato contrato, BindingResult result ) {
		
		this.contratoValidator.validate(contrato, result);
		
		if( result.hasErrors() ) {
			
			return "";
		}
		
		return "";
	}
	
	@GetMapping("/editarContrato")
	public String getEditarContrato(  ) {
		return "";
	}
	
	@PostMapping("/editarContrato")
	public String postEditarContrato( Contrato contrato, BindingResult result ) {
		
		this.contratoValidator.validate(contrato, result);
		
		if( result.hasErrors() ) {
			
			return "";
		}
		
		return "";
	}
	
	@PostMapping("/eliminarContrato")
	public String postEliminarContrato(  ) {
		return "";
	}
	
	
}
