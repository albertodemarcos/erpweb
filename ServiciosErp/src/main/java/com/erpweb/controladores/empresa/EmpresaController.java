package com.erpweb.controladores.empresa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erpweb.entidades.empresa.Empresa;
import com.erpweb.validadores.empresa.EmpresaValidator;

@CrossOrigin(origins = {"http://localhost:4200"}) //Conexion con angular 
@RestController
@RequestMapping("/empresas")
public class EmpresaController {
	
	@Autowired private EmpresaValidator empresaValidator;

	@GetMapping("/empresa")
	public String getEmpresa(  ) {
		return "";
	}
	
	@GetMapping("/empresas")
	public String getEmpresas(  ) {
		return "";
	}
	
	@GetMapping("/crearEmpresa")
	public String getCrearEmpresa(  ) {
		return "";
	}
	
	@PostMapping("/crearEmpresa")
	public String postCrearEmpresa( Empresa empresa, BindingResult result ) {
		
		this.empresaValidator.validate(empresa, result);
		
		if(	result.hasErrors() ) {
			
			return "";
		}
		
		return "";
	}
	
	@GetMapping("/editarEmpresa")
	public String getEditarEmpresa(  ) {
		return "";
	}
	
	@PostMapping("/editarEmpresa")
	public String postEditarEmpresa( Empresa empresa, BindingResult result ) {
		
		this.empresaValidator.validate(empresa, result);
		
		if(	result.hasErrors() ) {
			
			return "";
		}
		
		return "";
	}
	
	@PostMapping("/eliminarEmpresa")
	public String postEliminarEmpresa(  ) {
		return "";
	}
	
	
}
