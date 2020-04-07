package com.erpweb.controladores.ventas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erpweb.entidades.ventas.Factura;
import com.erpweb.servicios.ventas.FacturaService;
import com.erpweb.validadores.ventas.FacturaValidator;

@CrossOrigin(origins = {"http://localhost:4200"}) //Conexion con angular 
@RestController
@RequestMapping("/facturas")
public class FacturaController {
	
	@Autowired 
	private FacturaValidator facturaValidator;

	@Autowired
	private FacturaService facturaService;
	
	@GetMapping("/factura")
	public String getFactura(  ) {
		return "";
	}
	
	@GetMapping("/facturas")
	public String getFacturas(  ) {
		return "";
	}
	
	@GetMapping("/crearFactura")
	public String getCrearFactura(  ) {
		return "";
	}
	
	@PostMapping("/crearFactura")
	public String postCrearFactura( Factura factura, BindingResult result ) {
		
		this.facturaValidator.validate(factura, result);
		
		if( result.hasErrors() ) {
			
			return "";
		}
		
		return "";
	}
	
	@GetMapping("/editarFactura")
	public String getEditarFactura(  ) {
		return "";
	}
	
	@PostMapping("/editarFactura")
	public String postEditarFactura( Factura factura, BindingResult result ) {
		
		this.facturaValidator.validate(factura, result);
		
		if( result.hasErrors() ) {
			
			return "";
		}
		
		return "";
	}
	
	@PostMapping("/eliminarFactura")
	public String postEliminarFactura(  ) {
		return "";
	}
	
	
}
