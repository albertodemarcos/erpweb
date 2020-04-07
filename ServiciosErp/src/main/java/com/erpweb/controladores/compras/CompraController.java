package com.erpweb.controladores.compras;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erpweb.entidades.compras.Compra;
import com.erpweb.servicios.compras.CompraService;
import com.erpweb.validadores.compras.CompraValidator;

@CrossOrigin(origins = {"http://localhost:4200"}) //Conexion con angular 
@RestController
@RequestMapping("/compras")
public class CompraController {
	
	@Autowired 
	private CompraValidator compraValidator;
	
	@Autowired
	private CompraService compraService;

	@GetMapping("/compra")
	public String getCompra(  ) {
		return "";
	}
	
	@GetMapping("/compras")
	public String getCompras(  ) {
		return "";
	}
	
	@GetMapping("/crearCompra")
	public String getCrearCompra(  ) {
		return "";
	}
	
	@PostMapping("/crearCompra")
	public String postCrearCompra( Compra compra, BindingResult result ) {
		
		this.compraValidator.validate(compra, result);
		
		if(	result.hasErrors() ) {
			return "";
		}
		
		return "";
	}
	
	@GetMapping("/editarCompra")
	public String getEditarCompra(  ) {
		return "";
	}
	
	@PostMapping("/editarCompra")
	public String postEditarCompra( Compra compra, BindingResult result ) {
		
		this.compraValidator.validate(compra, result);
		
		if(	result.hasErrors() ) {
			return "";
		}
		return "";
	}
	
	@PostMapping("/eliminarCompra")
	public String postEliminarCompra(  ) {
		return "";
	}
}
