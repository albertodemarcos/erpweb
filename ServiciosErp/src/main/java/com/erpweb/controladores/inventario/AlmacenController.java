package com.erpweb.controladores.inventario;

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

import com.erpweb.dto.AlmacenDto;
import com.erpweb.entidades.usuarios.Usuario;
import com.erpweb.servicios.errores.ErroresService;
import com.erpweb.servicios.inventario.AlmacenService;
import com.erpweb.utiles.AccionRespuesta;
import com.erpweb.validadores.inventario.AlmacenValidator;

@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true") //Conexion con angular 
@RestController
@RequestMapping("/almacenes")
public class AlmacenController {
	
	@Autowired 
	private AlmacenValidator almacenValidator;
	
	@Autowired
	private AlmacenService almacenService;
	
	@Autowired
	private ErroresService erroresService;

	@GetMapping("/almacen/{almacenId}")
	public @ResponseBody AccionRespuesta getAlmacen( @PathVariable Long almacenId, Usuario user) throws Exception {
		
		return this.almacenService.getAlmacen(almacenId, user);
	}
	
	@GetMapping("/listado.json")
	public @ResponseBody List<AlmacenDto> getAlmacenes( ) {
		
		return this.almacenService.getListadoAlmacenes();
	}
	
	@GetMapping( "/editarAlmacen/{almacenId}" )
	public @ResponseBody AccionRespuesta getEditarAlmacen( @PathVariable Long almacenId, Usuario user) throws Exception {
		
		return this.almacenService.getAlmacen(almacenId, user);
	}
	
	@PostMapping( "/crearAlmacen" )
	public @ResponseBody AccionRespuesta postCrearAlmacen( @RequestBody AlmacenDto almacenDto, BindingResult result ) {
		
		Usuario user = new Usuario();
		
		this.almacenValidator.validate(almacenDto, result);
		
		if( result.hasErrors() ) {
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE, this.erroresService.erroresValidacionEnDto(result));
		}
		
		return this.almacenService.getCrearEditarAlmacen(almacenDto, user);
	}
	
	@PostMapping( "/editarAlmacen" )
	public @ResponseBody AccionRespuesta postEditarAlmacen( @RequestBody AlmacenDto almacenDto, BindingResult result ) {
		
		Usuario user = new Usuario();
		
		this.almacenValidator.validate(almacenDto, result);
		
		if( result.hasErrors() ) {
			
			return this.almacenService.getAlmacen(almacenDto.getId(), user);
		}
		
		return this.almacenService.getCrearEditarAlmacen(almacenDto, user);
	}
	
	@GetMapping("/eliminarAlmacen/{almacenId}")
	public @ResponseBody AccionRespuesta getEliminarAlmacen( @PathVariable Long almacenId, Usuario user) throws Exception {
		
		if(almacenId == null || almacenId.longValue() < 1L ) {
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
		
		return this.almacenService.eliminarAlmacenPorId(almacenId);
	}
	
	
}
