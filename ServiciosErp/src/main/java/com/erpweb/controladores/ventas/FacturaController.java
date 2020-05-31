package com.erpweb.controladores.ventas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.erpweb.entidades.usuarios.Usuario;
import com.erpweb.entidades.ventas.Factura;
import com.erpweb.servicios.ventas.FacturaService;
import com.erpweb.utiles.AccionRespuesta;
import com.erpweb.validadores.ventas.FacturaValidator;

@CrossOrigin(origins = {"http://localhost:4200"}) //Conexion con angular 
@RestController
@RequestMapping("/facturas")
public class FacturaController {
	
	@Autowired 
	private FacturaValidator facturaValidator;

	@Autowired
	private FacturaService facturaService;
	
	@GetMapping("/factura/{facturaId}")
	public @ResponseBody AccionRespuesta getFactura( @PathVariable Long facturaId, Usuario user) throws Exception {
		
		return this.facturaService.getFactura(facturaId, user);
	}
	
	@GetMapping("/listado")
	public String getFacturas(  ) {
		
		
		return "";
	}
	
	@GetMapping("/crearFactura")
	public @ResponseBody AccionRespuesta getCrearFactura(  ) {
		
		
		return new AccionRespuesta();
	}
	
	@PostMapping("/crearFactura")
	public @ResponseBody AccionRespuesta postCrearFactura( Factura factura, BindingResult result ) {
		
		this.facturaValidator.validate(factura, result);
		
		if( result.hasErrors() ) {
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	@GetMapping("/editarFactura")
	public @ResponseBody AccionRespuesta getEditarFactura(  ) {
		
		
		return new AccionRespuesta();
	}
	
	@PostMapping("/editarFactura")
	public @ResponseBody AccionRespuesta postEditarFactura( Factura factura, BindingResult result ) {
		
		this.facturaValidator.validate(factura, result);
		
		if( result.hasErrors() ) {
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	@PostMapping("/eliminarFactura")
	public @ResponseBody AccionRespuesta postEliminarFactura(  ) {
		
		
		return new AccionRespuesta();
	}
	
	
}
