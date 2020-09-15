package com.erpweb.controladores.ventas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.erpweb.dto.VentaDto;
import com.erpweb.entidades.usuarios.Usuario;
import com.erpweb.servicios.errores.ErroresService;
import com.erpweb.servicios.ventas.VentaService;
import com.erpweb.utiles.AccionRespuesta;
import com.erpweb.validadores.ventas.VentaValidator;

@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true") //Conexion con angular 
@RestController
@RequestMapping("/ventas")
public class VentaController {
	
	@Autowired 
	private VentaValidator ventaValidator;
	
	@Autowired
	private VentaService ventaService;
	
	@Autowired
	private ErroresService erroresService;

	@GetMapping("/venta/{ventaId}")
	public @ResponseBody AccionRespuesta getVenta( @PathVariable Long ventaId, Usuario user) throws Exception {
		
		return this.ventaService.getVenta(ventaId, user);
	}
	
	@GetMapping("/listado.json")
	public @ResponseBody List<VentaDto> getVentas( ) {
		
		return this.ventaService.getListadoVentas();
	}
			
	@GetMapping( "/editarVenta/{ventaId}" )
	public @ResponseBody AccionRespuesta getEditarVenta( @PathVariable Long ventaId, Usuario user) throws Exception {
		
		return this.ventaService.getVenta(ventaId, user);
	}
	
	@PostMapping( "/crearVenta" )
	public @ResponseBody AccionRespuesta postCrearVenta( @RequestBody VentaDto ventaDto, BindingResult result ) {
		
		this.ventaValidator.validate(ventaDto, result);
		
		if(	result.hasErrors() ) {
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE, this.erroresService.erroresValidacionEnVentaDto(ventaDto, result) );
		}
		
		return this.ventaService.getCrearEditarVenta(ventaDto);
	}
	
	@PostMapping( "/editarVenta" )
	public @ResponseBody AccionRespuesta postEditarVenta( @RequestBody VentaDto ventaDto, BindingResult result ) {
		
		this.ventaValidator.validate(ventaDto, result);
		
		if(	result.hasErrors() ) {
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE, this.erroresService.erroresValidacionEnVentaDto(ventaDto, result) );
		}
		
		return this.ventaService.getCrearEditarVenta(ventaDto);
	}
	
	@GetMapping("/eliminarVenta/{ventaId}")
	public @ResponseBody AccionRespuesta getEliminarVenta( @PathVariable Long ventaId, Usuario user) throws Exception {
		
		if(ventaId == null || ventaId.longValue() < 1L ) {
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
		
		return this.ventaService.eliminarVentaPorId(ventaId);
	}
	
	
}
