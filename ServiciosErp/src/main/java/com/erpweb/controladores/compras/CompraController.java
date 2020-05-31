package com.erpweb.controladores.compras;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@GetMapping("/crearCompra")
	public @ResponseBody AccionRespuesta getCrearCompra(  ) {
		
		return new AccionRespuesta();
	}
	
	@PostMapping("/crearCompra")
	public @ResponseBody AccionRespuesta postCrearCompra( Compra compra, BindingResult result ) {
		
		this.compraValidator.validate(compra, result);
		
		if(	result.hasErrors() ) {
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	@GetMapping("/editarCompra/{compraId}")
	public @ResponseBody AccionRespuesta getEditarCompra( @PathVariable Long compraId, Usuario user ) throws Exception {
		
		return new AccionRespuesta();
	}
	
	@PostMapping("/editarCompra")
	public @ResponseBody AccionRespuesta postEditarCompra( Compra compra, BindingResult result ) {
		
		this.compraValidator.validate(compra, result);
		
		if(	result.hasErrors() ) {
			
			return new AccionRespuesta();
		}
		return new AccionRespuesta();
	}
	
	@PostMapping("/eliminarCompra")
	public @ResponseBody AccionRespuesta postEliminarCompra(  ) {
		
		
		return new AccionRespuesta();
	}
}
