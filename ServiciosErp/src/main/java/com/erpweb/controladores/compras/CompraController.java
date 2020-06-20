package com.erpweb.controladores.compras;

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

import com.erpweb.dto.CompraDto;
import com.erpweb.entidades.usuarios.Usuario;
import com.erpweb.servicios.compras.CompraService;
import com.erpweb.utiles.AccionRespuesta;
import com.erpweb.validadores.compras.CompraValidator;

@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true") //Conexion con angular 
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
	
	@GetMapping("/listado.json")
	public @ResponseBody List<CompraDto> getCompras() {
		
		return this.compraService.getListadoCompras();
	}
	
	@GetMapping( "/crearCompra" )
	public @ResponseBody AccionRespuesta getCrearCompra( Model model, Usuario user ) throws Exception {
		
		return new AccionRespuesta();
	}
	
	@GetMapping( "/editarCompra/{compraId}"  )
	public @ResponseBody AccionRespuesta getCrearCompra( @PathVariable Long compraId, Usuario user ) throws Exception {
		
		return this.compraService.getCompra(compraId, user);
	}
	
	@PostMapping( { "/crearCompra/compra/{compraDto}.json", "/editarCompra/compra/{compraDto}.json" } )
	public @ResponseBody AccionRespuesta postCrearCompra( CompraDto compraDto, Usuario user, BindingResult result ) {
		
		this.compraValidator.validate(compraDto, result);
		
		if(	result.hasErrors() ) {
			
			return this.compraService.getCompra(compraDto.getId(), user);
		}
		
		return this.compraService.getCrearEditarCompra(compraDto, user);
	}
	
	@PostMapping("/eliminarCompra/{compraId}")
	public @ResponseBody AccionRespuesta postEliminarCompra( @PathVariable Long compraId, Usuario user ) throws Exception {
		
		if(compraId == null || compraId.longValue() < 1L ) {
			
			return new AccionRespuesta();
		}
		
		return this.compraService.eliminarCompraPorId(compraId);
	}
}
