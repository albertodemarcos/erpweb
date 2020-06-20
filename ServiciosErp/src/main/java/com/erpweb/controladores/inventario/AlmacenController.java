package com.erpweb.controladores.inventario;

import java.util.List;

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

import com.erpweb.dto.AlmacenDto;
import com.erpweb.entidades.usuarios.Usuario;
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

	@GetMapping("/almacen/{almacenId}")
	public @ResponseBody AccionRespuesta getAlmacen( @PathVariable Long almacenId, Usuario user) throws Exception {
		
		return this.almacenService.getAlmacen(almacenId, user);
	}
	
	@GetMapping("/listado.json")
	public @ResponseBody List<AlmacenDto> getAlmacenes( ) {
		
		return this.almacenService.getListadoAlmacenes();
	}
	
	@GetMapping( "/crearAlmacen" )
	public @ResponseBody AccionRespuesta getCrearAlmacen( Model model, Usuario user) throws Exception {
		
		return new AccionRespuesta();
	}
	
	@GetMapping( "/editarAlmacen/{almacenId}" )
	public @ResponseBody AccionRespuesta getEditarAlmacen( @PathVariable Long almacenId, Usuario user) throws Exception {
		
		return this.almacenService.getAlmacen(almacenId, user);
	}
	
	@PostMapping( { "/crearAlmacen/almacen/{almacenDto}.json", "/editarAlmacen/almacen/{almacenDto}.json" } )
	public @ResponseBody AccionRespuesta postCrearAlmacen( AlmacenDto almacenDto, Usuario user, BindingResult result ) {
		
		this.almacenValidator.validate(almacenDto, result);
		
		if( result.hasErrors() ) {
			
			return this.almacenService.getAlmacen(almacenDto.getId(), user);
		}
		
		return this.almacenService.getCrearEditarAlmacen(almacenDto, user);
	}
	
	@PostMapping("/eliminarAlmacen/{almacenId}")
	public @ResponseBody AccionRespuesta postEliminarAlmacen( @PathVariable Long almacenId, Usuario user) throws Exception {
		
		if(almacenId == null || almacenId.longValue() < 1L ) {
			
			return new AccionRespuesta();
		}
		
		return this.almacenService.eliminarAlmacenPorId(almacenId);
	}
	
	
}
