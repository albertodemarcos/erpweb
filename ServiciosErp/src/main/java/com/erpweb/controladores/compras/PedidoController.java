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

import com.erpweb.dto.PedidoDto;
import com.erpweb.entidades.usuarios.Usuario;
import com.erpweb.servicios.compras.PedidoService;
import com.erpweb.utiles.AccionRespuesta;
import com.erpweb.validadores.compras.PedidoValidator;

@CrossOrigin(origins = {"http://localhost:4200"}) //Conexion con angular 
@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	
	@Autowired 
	private PedidoValidator pedidoValidator;
	
	@Autowired
	private PedidoService pedidoService;

	@GetMapping("/pedido/{pedidoId}")
	public @ResponseBody AccionRespuesta getPedido( @PathVariable Long pedidoId, Usuario user ) throws Exception {
		
		return this.pedidoService.getPedido(pedidoId, user);
	}
	
	@GetMapping("/listado")
	public String getPedidos(  ) {
		
		return "";
	}
	
	@GetMapping( "/crearPedido" )
	public @ResponseBody AccionRespuesta getCrearPedido( Model model, Usuario user ) throws Exception {
		
		return new AccionRespuesta();
	}
	
	@GetMapping( "/editarPedido/{pedidoId}"  )
	public @ResponseBody AccionRespuesta getCrearPedido( @PathVariable Long pedidoId, Usuario user ) throws Exception {
		
		return this.pedidoService.getPedido(pedidoId, user);
	}
	
	@PostMapping( { "/crearPedido/pedido/{pedidoDto}.json", "/editarPedido/pedido/{pedidoDto}.json" } )
	public @ResponseBody AccionRespuesta postCrearPedido( PedidoDto pedidoDto, Usuario user, BindingResult result ) {
		
		this.pedidoValidator.validate(pedidoDto, result);
		
		if(	result.hasErrors() ) {
			
			return this.pedidoService.getPedido(pedidoDto.getId(), user);
		}
		
		return this.pedidoService.getCrearEditarPedido(pedidoDto, user);
	}
	
	@PostMapping("/eliminarPedido/{pedidoId}")
	public @ResponseBody AccionRespuesta postEliminarPedido( @PathVariable Long pedidoId, Usuario user ) throws Exception {
		
		if(pedidoId == null || pedidoId.longValue() < 1L ) {
			
			return new AccionRespuesta();
		}
		
		return this.pedidoService.eliminarPedidoPorId(pedidoId);
	}
	
	
	
}
