package com.erpweb.controladores.inventario;

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

import com.erpweb.entidades.inventario.Articulo;
import com.erpweb.entidades.usuarios.Usuario;
import com.erpweb.servicios.inventario.ArticuloService;
import com.erpweb.utiles.AccionRespuesta;
import com.erpweb.validadores.inventario.ArticuloValidator;

@CrossOrigin(origins = {"http://localhost:4200"}) //Conexion con angular 
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
	
	@GetMapping("/articulos")
	public String getArticulos(  ) {
		
		
		return "";
	}
	
	@GetMapping( "/crearArticulo" )
	public @ResponseBody AccionRespuesta getCrearArticulo( Model model, Usuario user) throws Exception {
		
		return new AccionRespuesta();
	}
	
	@GetMapping( "/editarArticulo/{articuloId}" )
	public @ResponseBody AccionRespuesta getEditarArticulo( @PathVariable Long articuloId, Usuario user) throws Exception {
		
		return this.articuloService.getArticulo(articuloId, user);
	}
	
	@PostMapping( {"/crearArticulo", "/editarArticulo"} )
	public @ResponseBody AccionRespuesta postCrearArticulo( Articulo articulo, BindingResult result ) {
		
		this.articuloValidator.validate(articulo, result);
		
		if( result.hasErrors() ) {
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	@PostMapping("/eliminarArticulo")
	public @ResponseBody AccionRespuesta postEliminarArticulo(  ) {
		
		return new AccionRespuesta();
	}
	
	
}
