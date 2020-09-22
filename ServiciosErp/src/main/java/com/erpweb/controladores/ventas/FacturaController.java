package com.erpweb.controladores.ventas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.erpweb.dto.FacturaDto;
import com.erpweb.servicios.errores.ErroresService;
import com.erpweb.servicios.ventas.FacturaService;
import com.erpweb.utiles.AccionRespuesta;
import com.erpweb.validadores.ventas.FacturaValidator;

@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true") //Conexion con angular 
@RestController
@RequestMapping("/facturas")
public class FacturaController {
	
	@Autowired 
	private FacturaValidator facturaValidator;

	@Autowired
	private FacturaService facturaService;
	
	@Autowired
	private ErroresService erroresService;
	
	@GetMapping("/factura/{facturaId}")
	public @ResponseBody AccionRespuesta getFactura( @PathVariable Long facturaId) throws Exception {
		
		return this.facturaService.getFactura(facturaId);
	}
	
	@GetMapping("/listado.json")
	public @ResponseBody List<FacturaDto> getFacturas( ) {
		
		return this.facturaService.getListadoFacturas();
	}
	
	@GetMapping( "/crearFactura" )
	public @ResponseBody AccionRespuesta getCrearFactura(  Model model) throws Exception {
		
		return new AccionRespuesta();
	}
	
	@GetMapping( "/editarFactura/{facturaId}" )
	public @ResponseBody AccionRespuesta getEditarFactura( @PathVariable Long facturaId) throws Exception {
		
		return this.facturaService.getFactura(facturaId);
	}
	
	@PostMapping( "/crearFactura" )
	public @ResponseBody AccionRespuesta postCrearFactura( @RequestBody FacturaDto facturaDto, BindingResult result ) {
		
		this.facturaValidator.validate(facturaDto, result);
		
		if( result.hasErrors() ) {
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE, this.erroresService.erroresValidacionEnDto(result) );
		}
		
		return this.facturaService.getCrearEditarFactura(facturaDto);
	}
	
	@PostMapping( "/editarFactura" )
	public @ResponseBody AccionRespuesta postEditarFactura( @RequestBody FacturaDto facturaDto, BindingResult result ) {
		
		this.facturaValidator.validate(facturaDto, result);
		
		if( result.hasErrors() ) {
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE, this.erroresService.erroresValidacionEnDto(result) );
		}
		
		return this.facturaService.getCrearEditarFactura(facturaDto);
	}
	
	@GetMapping("/eliminarFactura/{facturaId}")
	public @ResponseBody AccionRespuesta getEliminarFactura( @PathVariable Long facturaId) throws Exception {
		
		if(facturaId == null || facturaId.longValue() < 1L ) {
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
		
		return this.facturaService.eliminarFacturaPorId(facturaId);
	}
	
	
}
