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

import com.erpweb.dto.PedidoDto;
import com.erpweb.servicios.compras.ConversorPedidoCompraService;
import com.erpweb.servicios.compras.PedidoService;
import com.erpweb.servicios.errores.ErroresService;
import com.erpweb.utiles.AccionRespuesta;
import com.erpweb.validadores.compras.PedidoValidator;

@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true") //Conexion con angular 
@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	
	@Autowired 
	private PedidoValidator pedidoValidator;
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private ConversorPedidoCompraService conversorPedidoCompraService;
	
	@Autowired
	private ErroresService erroresService;

	@GetMapping("/pedido/{pedidoId}")
	public @ResponseBody AccionRespuesta getPedido( @PathVariable Long pedidoId ) throws Exception {
		
		return this.pedidoService.getPedido(pedidoId);
	}
	
	@GetMapping("/convertirCompraPedido/{pedidoId}")
	public @ResponseBody AccionRespuesta getConvetirPedidoEnCompra( @PathVariable Long pedidoId ) throws Exception {
		
		return this.conversorPedidoCompraService.convetirPedidoEnCompra(pedidoId);
	}
	
	@GetMapping("/listado.json")
	public @ResponseBody List<PedidoDto> getPedidos(  ) {
		
		return this.pedidoService.getListadoPedidos();
	}
	
	
	@GetMapping( "/editarPedido/{pedidoId}"  )
	public @ResponseBody AccionRespuesta getEditarPedido( @PathVariable Long pedidoId ) throws Exception {
		
		return this.pedidoService.getPedido(pedidoId);
	}
	
	@PostMapping( "/crearPedido" )
	public @ResponseBody AccionRespuesta postCrearPedido( @RequestBody PedidoDto pedidoDto, BindingResult result ) {
		
		this.pedidoValidator.validate(pedidoDto, result);
		
		if(	result.hasErrors() ) {
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE, this.erroresService.erroresValidacionEnDto(result) );
		}
		
		return this.pedidoService.getCrearEditarPedido(pedidoDto);
	}
	
	@PostMapping( "/editarPedido" )
	public @ResponseBody AccionRespuesta postEditarPedido( @RequestBody PedidoDto pedidoDto, BindingResult result ) {
		
		this.pedidoValidator.validate(pedidoDto, result);
		
		if(	result.hasErrors() ) {
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE, this.erroresService.erroresValidacionEnDto(result) );
		}
		
		return this.pedidoService.getCrearEditarPedido(pedidoDto);
	}
	
	@GetMapping("/eliminarPedido/{pedidoId}")
	public @ResponseBody AccionRespuesta getEliminarPedido( @PathVariable Long pedidoId ) throws Exception {
		
		if(pedidoId == null || pedidoId.longValue() < 1L ) {
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
		
		return this.pedidoService.eliminarPedidoPorId(pedidoId);
	}
	
	
	
}
