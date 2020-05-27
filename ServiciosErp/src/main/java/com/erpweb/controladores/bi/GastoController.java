package com.erpweb.controladores.bi;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erpweb.dto.GastoDto;
import com.erpweb.entidades.bi.Gasto;
import com.erpweb.servicios.bi.GastoService;
import com.erpweb.validadores.bi.GastoValidator;



@CrossOrigin(origins = {"http://localhost:4200"}) //Conexion con angular 
@RestController
@RequestMapping("/gastos")
public class GastoController {
	
	@Autowired 
	private GastoValidator gastoValidator;
	
	@Autowired
	private GastoService gastoService;
	
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
	public String postCrearGasto(GastoDto gasto, BindingResult result) {
		
		this.gastoValidator.validate(gasto, result);
		
		if( result.hasErrors() ) {
			
			return "";
		}
		
		
		return "";
	}
	
	@GetMapping("/editarGasto")
	public String getEditarGasto() {
		return "";
	}
	
	@PostMapping("/editarGasto")
	public String postEditarGasto(GastoDto gasto, BindingResult result) {
		
		this.gastoValidator.validate(gasto, result);
		
		if( result.hasErrors() ) {
			
			return "";
		}
		
		
		return "";
	}
	
	@PostMapping("/eliminarGasto")
	public String postEliminarGasto(GastoDto gasto) {
		
		if(gasto == null) {
			
			return "";
		}
		
		
		
		return "";
	}

}
