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

import com.erpweb.dto.CompraDto;
import com.erpweb.entidades.usuarios.Usuario;
import com.erpweb.servicios.compras.CompraService;
import com.erpweb.servicios.errores.ErroresService;
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
	
	@Autowired
	private ErroresService erroresService;

	@GetMapping("/compra/{compraId}")
	public @ResponseBody AccionRespuesta getCompra( @PathVariable Long compraId, Usuario user ) throws Exception {
		
		return this.compraService.getCompra(compraId, user);
	}
	
	@GetMapping("/listado.json")
	public @ResponseBody List<CompraDto> getCompras() {
		
		return this.compraService.getListadoCompras();
	}
			
	@GetMapping( "/editarCompra/{compraId}"  )
	public @ResponseBody AccionRespuesta getEditarCompra( @PathVariable Long compraId, Usuario user ) throws Exception {
		
		return this.compraService.getCompra(compraId, user);
	}
	
	@PostMapping( "/crearCompra" )
	public @ResponseBody AccionRespuesta postCrearCompra(@RequestBody CompraDto compraDto, BindingResult result ) {
		
		Usuario user = new Usuario();
		
		this.compraValidator.validate(compraDto, result);
		
		if(	result.hasErrors() ) {
									
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE, this.erroresService.erroresValidacionEnDto(result) );
		}
		
		return this.compraService.getCrearEditarCompra(compraDto, user);
	}
	
	@PostMapping( "/editarCompra" )
	public @ResponseBody AccionRespuesta postEditarCompra(@RequestBody CompraDto compraDto, BindingResult result ) {
		
		Usuario user = new Usuario();
		
		this.compraValidator.validate(compraDto, result);
		
		if(	result.hasErrors() ) {
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE, this.erroresService.erroresValidacionEnDto(result) );
		}
		
		return this.compraService.getCrearEditarCompra(compraDto, user);
	}
	
	@GetMapping("/eliminarCompra/{compraId}")
	public @ResponseBody AccionRespuesta getEliminarCompra( @PathVariable Long compraId, Usuario user ) throws Exception {
		
		if(compraId == null || compraId.longValue() < 1L ) {
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
		
		return this.compraService.eliminarCompraPorId(compraId);
	}
}
