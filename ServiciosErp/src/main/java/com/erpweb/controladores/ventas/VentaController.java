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
import com.erpweb.entidades.ventas.Venta;
import com.erpweb.servicios.ventas.VentaService;
import com.erpweb.utiles.AccionRespuesta;
import com.erpweb.validadores.ventas.VentaValidator;

@CrossOrigin(origins = {"http://localhost:4200"}) //Conexion con angular 
@RestController
@RequestMapping("/ventas")
public class VentaController {
	
	@Autowired 
	private VentaValidator ventaValidator;
	
	@Autowired
	private VentaService ventaService;

	@GetMapping("/venta/{ventaId}")
	public @ResponseBody AccionRespuesta getVenta( @PathVariable Long ventaId, Usuario user) throws Exception {
		
		return this.ventaService.getVenta(ventaId, user);
	}
	
	@GetMapping("/listado")
	public String getVentas(  ) {
		
		
		return "";
	}
	
	@GetMapping("/crearVenta")
	public @ResponseBody AccionRespuesta getCrearVenta(  ) {
		
		return new AccionRespuesta();
	}
	
	@PostMapping("/crearVenta")
	public @ResponseBody AccionRespuesta postCrearVenta( Venta venta, BindingResult result ) {
		
		this.ventaValidator.validate(venta, result);
		
		if(	result.hasErrors() ) {
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	@GetMapping("/editarVenta")
	public @ResponseBody AccionRespuesta getEditarVenta(  ) {
		
		
		return new AccionRespuesta();
	}
	
	@PostMapping("/editarVenta")
	public @ResponseBody AccionRespuesta postEditarVenta( Venta venta, BindingResult result ) {
		
		this.ventaValidator.validate(venta, result);
		
		if(	result.hasErrors() ) {
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	@PostMapping("/eliminarVenta")
	public @ResponseBody AccionRespuesta postEliminarVenta(  ) {
		
		
		return new AccionRespuesta();
	}
	
	
}
