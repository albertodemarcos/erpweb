package com.erpweb.controladores.compras;

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

import com.erpweb.entidades.compras.Compra;
import com.erpweb.entidades.usuarios.Usuario;
import com.erpweb.servicios.compras.CompraService;
import com.erpweb.utiles.AccionRespuesta;
import com.erpweb.validadores.compras.CompraValidator;

@CrossOrigin(origins = {"http://localhost:4200"}) //Conexion con angular 
@RestController
@RequestMapping("/compras")
public class CompraController {
	
	@Autowired 
	private CompraValidator compraValidator;
	
	@Autowired
	private CompraService compraService;

	@GetMapping("/compra/{compraId}")
	public @ResponseBody AccionRespuesta getCompra( @PathVariable Long compraId, Usuario user ) throws Exception {
		
		return this.compraService.getCompra(compraId, user);
	}
	
	@GetMapping("/listado")
	public String getCompras(  ) {
		
		return "";
	}
	
	@GetMapping( "/crearCompra" )
	public @ResponseBody AccionRespuesta getCrearCompra( Model model, Usuario user ) throws Exception {
		
		return new AccionRespuesta();
	}
	
	@GetMapping( "/editarCompra/{compraId}"  )
	public @ResponseBody AccionRespuesta getCrearCompra( @PathVariable Long compraId, Usuario user ) throws Exception {
		
		return this.compraService.getCompra(compraId, user);
	}
	
	@PostMapping( { "/crearCompra", "/editarCompra" } )
	public @ResponseBody AccionRespuesta postCrearCompra( Compra compra, BindingResult result ) {
		
		this.compraValidator.validate(compra, result);
		
		if(	result.hasErrors() ) {
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	@PostMapping("/eliminarCompra/{compraId}")
	public @ResponseBody AccionRespuesta postEliminarCompra( @PathVariable Long compraId, Usuario user ) throws Exception {
		
		if(compraId == null || compraId.longValue() < 1L ) {
			
			return new AccionRespuesta();
		}
		
		return this.compraService.eliminarCompraPorId(compraId);
	}
}
