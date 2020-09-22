package com.erpweb.controladores.compras;

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

import com.erpweb.dto.ProveedorDto;
import com.erpweb.servicios.compras.ProveedorService;
import com.erpweb.servicios.errores.ErroresService;
import com.erpweb.utiles.AccionRespuesta;
import com.erpweb.validadores.compras.ProveedorValidator;

@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true") //Conexion con angular 
@RestController
@RequestMapping("/proveedores")
public class ProveedorController {
	
	@Autowired 
	private ProveedorValidator proveedorValidator;
	
	@Autowired
	private ProveedorService proveedorService;
	
	@Autowired
	private ErroresService erroresService;
	
	@GetMapping("/proveedor/{proveedorId}")
	public @ResponseBody AccionRespuesta getProveedor( @PathVariable Long proveedorId ) throws Exception {
		
		return this.proveedorService.getProveedor(proveedorId);
	}
	
	@GetMapping("/listado.json")
	public @ResponseBody List<ProveedorDto> getProveedores() {
		
		return this.proveedorService.getListadoProveedores();
	}
	
	@GetMapping( "/editarProveedor/{proveedorId}" )
	public @ResponseBody AccionRespuesta getEditarProveedor( @PathVariable Long proveedorId ) throws Exception {
		
		return this.proveedorService.getProveedor(proveedorId);
	}
	
	@PostMapping( "/crearProveedor" )
	public @ResponseBody AccionRespuesta postCrearProveedor( @RequestBody ProveedorDto proveedorDto, BindingResult result ) {
		
		this.proveedorValidator.validate(proveedorDto, result);
		
		if(	result.hasErrors() ) {
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE, this.erroresService.erroresValidacionEnDto(result) );
		}
		
		return this.proveedorService.getCrearEditarProveedor(proveedorDto);
	}
	
	@PostMapping( "/editarProveedor" )
	public @ResponseBody AccionRespuesta postEditarProveedor( @RequestBody ProveedorDto proveedorDto, BindingResult result ) {
		
		this.proveedorValidator.validate(proveedorDto, result);
		
		if(	result.hasErrors() ) {
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE, this.erroresService.erroresValidacionEnDto(result) );
		}
		
		return this.proveedorService.getCrearEditarProveedor(proveedorDto);
	}
	
	@GetMapping("/eliminarProveedor/{proveedorId}")
	public @ResponseBody AccionRespuesta getEliminarProveedor( @PathVariable Long proveedorId ) throws Exception {
		
		if(proveedorId == null || proveedorId.longValue() < 1L ) {
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
		
		return this.proveedorService.eliminarProveedorPorId(proveedorId);
	}
	
}
