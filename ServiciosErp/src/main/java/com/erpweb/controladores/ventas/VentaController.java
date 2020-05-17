package com.erpweb.controladores.ventas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erpweb.entidades.ventas.Venta;
import com.erpweb.servicios.ventas.VentaService;
import com.erpweb.validadores.ventas.VentaValidator;

@CrossOrigin(origins = {"http://localhost:4200"}) //Conexion con angular 
@RestController
@RequestMapping("/venta")
public class VentaController {
	
	@Autowired 
	private VentaValidator ventaValidator;
	
	@Autowired
	private VentaService ventaService;

	@GetMapping("/venta")
	public String getVenta(  ) {
		return "";
	}
	
	@GetMapping("/ventas")
	public String getVentas(  ) {
		return "";
	}
	
	@GetMapping("/crearVenta")
	public String getCrearVenta(  ) {
		return "";
	}
	
	@PostMapping("/crearVenta")
	public String postCrearVenta( Venta venta, BindingResult result ) {
		
		this.ventaValidator.validate(venta, result);
		
		if(	result.hasErrors() ) {
			
			return "";
		}
		
		return "";
	}
	
	@GetMapping("/editarVenta")
	public String getEditarVenta(  ) {
		return "";
	}
	
	@PostMapping("/editarVenta")
	public String postEditarVenta( Venta venta, BindingResult result ) {
		
		this.ventaValidator.validate(venta, result);
		
		if(	result.hasErrors() ) {
			
			return "";
		}
		
		return "";
	}
	
	@PostMapping("/eliminarVenta")
	public String postEliminarVenta(  ) {
		return "";
	}
	
	
}
