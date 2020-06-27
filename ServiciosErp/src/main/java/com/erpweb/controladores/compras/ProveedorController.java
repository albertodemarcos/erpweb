package com.erpweb.controladores.compras;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.erpweb.dto.ProveedorDto;
import com.erpweb.entidades.usuarios.Usuario;
import com.erpweb.servicios.ErroresService;
import com.erpweb.servicios.compras.ProveedorService;
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
	public @ResponseBody AccionRespuesta getProveedor( @PathVariable Long proveedorId, Usuario user ) throws Exception {
		
		return this.proveedorService.getProveedor(proveedorId, user);
	}
	
	@GetMapping("/listado.json")
	public @ResponseBody List<ProveedorDto> getProveedores() {
		
		return this.proveedorService.getListadoProveedores();
	}
	
	@GetMapping( "/editarProveedor/{proveedorId}" )
	public @ResponseBody AccionRespuesta getEditarProveedor( @PathVariable Long proveedorId, Usuario user ) throws Exception {
		
		return this.proveedorService.getProveedor(proveedorId, user);
	}
	
	@PostMapping( "/crearProveedor" )
	public @ResponseBody AccionRespuesta postCrearProveedor( ProveedorDto proveedorDto, BindingResult result ) {
		
		Usuario user = new Usuario();
		
		this.proveedorValidator.validate(proveedorDto, result);
		
		if(	result.hasErrors() ) {
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE, this.erroresService.erroresValidacionEnDto(result) );
		}
		
		return this.proveedorService.getCrearEditarProveedor(proveedorDto, user);
	}
	
	@PostMapping( "/editarProveedor" )
	public @ResponseBody AccionRespuesta postEditarProveedor( ProveedorDto proveedorDto, BindingResult result ) {
		
		Usuario user = new Usuario();
		
		this.proveedorValidator.validate(proveedorDto, result);
		
		if(	result.hasErrors() ) {
			
			return this.proveedorService.getProveedor(proveedorDto.getId(), user);
		}
		
		return this.proveedorService.getCrearEditarProveedor(proveedorDto, user);
	}
	
	@PostMapping("/eliminarProveedor/{proveedorId}")
	public @ResponseBody AccionRespuesta postEliminarProveedor( @PathVariable Long proveedorId, Usuario user ) throws Exception {
		
		if(proveedorId == null || proveedorId.longValue() < 1L ) {
			
			return new AccionRespuesta();
		}
		
		return this.proveedorService.eliminarProveedorPorId(proveedorId);
	}
	
}
