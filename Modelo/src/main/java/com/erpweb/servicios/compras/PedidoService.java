package com.erpweb.servicios.compras;

import java.util.HashMap;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpweb.dto.PedidoDto;
import com.erpweb.entidades.compras.Pedido;
import com.erpweb.entidades.usuarios.Usuario;
import com.erpweb.repositorios.compras.PedidoRepository;
import com.erpweb.utiles.AccionRespuesta;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public AccionRespuesta crearPedidoDesdePedidoDto(PedidoDto pedidoDto) {
		
		logger.debug("Entramos en el metodo crearPedidoDesdePedidoDto() con ID={}", pedidoDto.getId() );
		
		Pedido pedido = new Pedido();
		
		pedido.setCodigo(pedidoDto.getCodigo());
		pedido.setFechaPedido(pedidoDto.getFechaPedido());
		
		try {
			
			//Guardamos el pedido en base de datos
			pedidoRepository.save(pedido);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo crearPedidoDesdePedidoDto() con ID={}", pedidoDto.getId() );
			
			e.printStackTrace();
						
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public AccionRespuesta actualizarPedidoDesdePedidoDto(PedidoDto pedidoDto) {
		
		logger.debug("Entramos en el metodo actualizarPedidoDesdePedidoDto() con ID={}", pedidoDto.getId() );
		
		Pedido pedido = new Pedido();
		
		
		pedido.setId(pedidoDto.getId());
		pedido.setCodigo(pedidoDto.getCodigo());
		pedido.setFechaPedido(pedidoDto.getFechaPedido());
		
		try {
			
			//Actualizamos el pedido en base de datos
			pedidoRepository.save(pedido);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo actualizarPedidoDesdePedidoDto() con ID={}", pedidoDto.getId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
				
		return new AccionRespuesta();
	}
	
	public AccionRespuesta eliminarPedido(Pedido pedido) {
		
		logger.debug("Entramos en el metodo eliminarPedido() con ID={}", pedido.getId() );
		
		if(pedido == null || pedido.getId() == null) {
			
			return new AccionRespuesta();
		}
		
		try {
			
			//Elimnamos el pedido
			pedidoRepository.deleteById(pedido.getId());
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo eliminarPedido() con ID={} ", pedido.getId() );
			
			e.printStackTrace();
						
			return new AccionRespuesta();
		}
				
		return new AccionRespuesta();
	}
	
	public AccionRespuesta eliminarPedidoPorId(Long pedidoId) {
		
		logger.error("Entramos en el metodo eliminarPedidoPorId() con id={}", pedidoId );
				
		try {
			
			//Elimnamos el pedido
			pedidoRepository.deleteById(pedidoId);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo eliminarPedidoPorId() con id={}", pedidoId );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
		
	public PedidoDto obtenerPedidoDtoDesdePedido(Long id) {
		
		logger.debug("Entramos en el metodo obtenerPedidoDtoDesdePedido() con ID={}", id );
		
		Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);
		
		Pedido pedido = pedidoOptional.get();
		
		if(pedido == null) {
			
			return new PedidoDto();
		}
		
		PedidoDto pedidoDto = new PedidoDto();
		
		try {
			
			pedidoDto.setCodigo(pedido.getCodigo());
			pedidoDto.setFechaPedido(pedido.getFechaPedido());
			
		} catch(Exception e) {
			
			logger.error("Error en el metodo obtenerPedidoDtoDesdePedido() con ID={} ", id );
			
			e.printStackTrace();
		}
		
		return pedidoDto;
	}
	
	public AccionRespuesta getPedido(Long pedidoId, Usuario user) {
		
		logger.debug("Entramos en el metodo getPedido()");
		
		if( pedidoId == null) {
			
			return new AccionRespuesta(-1L, "Error, existe el pedido", Boolean.FALSE);
		}
		
		PedidoDto pedidoDto = this.obtenerPedidoDtoDesdePedido(pedidoId);
		
		AccionRespuesta AccionRespuesta = new AccionRespuesta();
		
		if( pedidoDto != null ) {
			
			AccionRespuesta.setId( pedidoDto.getId() );
			
			AccionRespuesta.setRespuesta("");
			
			AccionRespuesta.setResultado(Boolean.TRUE);
			
			HashMap<String, Object> mapa = new HashMap<String, Object>();
			
			mapa.put("pedidoDto", pedidoDto);
			
			AccionRespuesta.setData(new HashMap<String, Object>(mapa));
			
		}else {
			
			AccionRespuesta.setId( -1L );
			
			AccionRespuesta.setRespuesta("Error, no se ha podido recuperar el pedido");
			
			AccionRespuesta.setResultado(Boolean.FALSE);
		}
		
		return AccionRespuesta;
	}
	
	public AccionRespuesta getCrearEditarPedido(PedidoDto pedidoDto, Usuario user) {
		
		logger.debug("Entramos en el metodo getCrearEditarPedido() con usuario={}", user.getId() );
		
		if( pedidoDto.getId() != null && pedidoDto.getId().longValue() > 0) {
			
			logger.debug("Se va a realizar una actualizacion del Pedido con usuario={}", user.getId() );
			
			return this.actualizarPedidoDesdePedidoDto(pedidoDto);
			
		} else {
			
			logger.debug("Se va a crear un Pedido con usuario={}", user.getId() );
			
			return this.crearPedidoDesdePedidoDto(pedidoDto);
		}
	}
	
	
	
	
}
