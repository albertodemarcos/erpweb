package com.erpweb.controladores.inventario;

import java.util.HashMap;
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

import com.erpweb.dto.ArticuloDto;
import com.erpweb.entidades.usuarios.Usuario;
import com.erpweb.servicios.inventario.ArticuloService;
import com.erpweb.utiles.AccionRespuesta;
import com.erpweb.validadores.inventario.ArticuloValidator;

@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true") //Conexion con angular 
@RestController
@RequestMapping("/articulos")
public class ArticuloController {
	
	@Autowired 
	private ArticuloValidator articuloValidator;

	@Autowired
	private ArticuloService articuloService;
	
	@GetMapping("/articulo/{articuloId}")
	public @ResponseBody AccionRespuesta getArticulo( @PathVariable Long articuloId, Usuario user) throws Exception {
		
		return this.articuloService.getArticulo(articuloId, user);
	}
	
	@GetMapping("/listado.json")
	public @ResponseBody List<ArticuloDto> getArticulos() {
		
		return this.articuloService.getListadoArticulos();
	}
	
	@GetMapping( "/editarArticulo/{articuloId}" )
	public @ResponseBody AccionRespuesta getEditarArticulo( @PathVariable Long articuloId, Usuario user) throws Exception {
		
		return this.articuloService.getArticulo(articuloId, user);
	}
	
	@PostMapping( "/crearArticulo" )
	public @ResponseBody AccionRespuesta postCrearArticulo( ArticuloDto articuloDto, BindingResult result ) {
		
		Usuario user = new Usuario();
		
		this.articuloValidator.validate(articuloDto, result);
		
		if( result.hasErrors() ) {
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE, new HashMap<String, Object> (result.getModel()));
		}
		
		return this.articuloService.getCrearEditarArticulo(articuloDto, user);
	}
	
	@PostMapping( "/editarArticulo" )
	public @ResponseBody AccionRespuesta postEditarArticulo( ArticuloDto articuloDto, BindingResult result ) {
		
		Usuario user = new Usuario();
		
		this.articuloValidator.validate(articuloDto, result);
		
		if( result.hasErrors() ) {
			
			return this.articuloService.getArticulo(articuloDto.getId(), user);
		}
		
		return this.articuloService.getCrearEditarArticulo(articuloDto, user);
	}
	
	@PostMapping("/eliminarArticulo")
	public @ResponseBody AccionRespuesta postEliminarArticulo( @PathVariable Long articuloId, Usuario user) throws Exception {
		
		if(articuloId == null || articuloId.longValue() < 1L ) {
			
			return new AccionRespuesta();
		}
		
		return this.articuloService.eliminarArticuloPorId(articuloId);
	}
	
	
}
