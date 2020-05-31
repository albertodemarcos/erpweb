package com.erpweb.controladores.ventas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.erpweb.dto.VentaDto;
import com.erpweb.entidades.usuarios.Usuario;
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
	
	@GetMapping( "/crearVenta" )
	public @ResponseBody AccionRespuesta getCrearVenta( Model model, Usuario user) throws Exception {
		
		return new AccionRespuesta();
	}
	
	@GetMapping( "/editarVenta/{ventaId}" )
	public @ResponseBody AccionRespuesta getEditarVenta( @PathVariable Long ventaId, Usuario user) throws Exception {
		
		return this.ventaService.getVenta(ventaId, user);
	}
	
	@PostMapping( { "/crearVenta/venta/{ventaDto}.json", "/editarVenta/venta/{ventaDto}.json" } )
	public @ResponseBody AccionRespuesta postCrearVenta( VentaDto ventaDto, Usuario user, BindingResult result ) {
		
		this.ventaValidator.validate(ventaDto, result);
		
		if(	result.hasErrors() ) {
			
			return this.ventaService.getVenta(ventaDto.getId(), user);
		}
		
		return this.ventaService.getCrearEditarVenta(ventaDto, user);
	}
	
	@PostMapping("/eliminarVenta/{ventaId}")
	public @ResponseBody AccionRespuesta postEliminarVenta( @PathVariable Long ventaId, Usuario user) throws Exception {
		
		if(ventaId == null || ventaId.longValue() < 1L ) {
			
			return new AccionRespuesta();
		}
		
		return this.ventaService.eliminarVentaPorId(ventaId);
	}
	
	
}
