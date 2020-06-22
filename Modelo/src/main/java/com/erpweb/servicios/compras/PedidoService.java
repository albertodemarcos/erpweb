package com.erpweb.servicios.compras;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
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
		pedido.setArticulo(pedidoDto.getArticulo());
		pedido.setCantidad(pedidoDto.getCantidad());
		pedido.setBaseImponibleTotal(pedidoDto.getBaseImponibleTotal());
		pedido.setImpuesto(pedidoDto.getImpuesto());
		pedido.setImporteTotal(pedidoDto.getImporteTotal());
		
		try {
			
			//Guardamos el pedido en base de datos
			pedidoRepository.save(pedido);
			
			return this.devolverDatosPedidoDto(pedidoDto, pedido);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo crearPedidoDesdePedidoDto() con ID={}", pedidoDto.getId() );
			
			e.printStackTrace();
						
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
	}
	
	public AccionRespuesta actualizarPedidoDesdePedidoDto(PedidoDto pedidoDto) {
		
		logger.debug("Entramos en el metodo actualizarPedidoDesdePedidoDto() con ID={}", pedidoDto.getId() );
		
		Pedido pedido = new Pedido();
		
		pedido.setId(pedidoDto.getId());
		pedido.setCodigo(pedidoDto.getCodigo());
		pedido.setFechaPedido(pedidoDto.getFechaPedido());
		pedido.setArticulo(pedidoDto.getArticulo());
		pedido.setCantidad(pedidoDto.getCantidad());
		pedido.setBaseImponibleTotal(pedidoDto.getBaseImponibleTotal());
		pedido.setImpuesto(pedidoDto.getImpuesto());
		pedido.setImporteTotal(pedidoDto.getImporteTotal());
		
		try {
			
			//Actualizamos el pedido en base de datos
			pedidoRepository.save(pedido);
			
			return this.devolverDatosActualizadosPedidoDto(pedidoDto, pedido);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo actualizarPedidoDesdePedidoDto() con ID={}", pedidoDto.getId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
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
	
	public List<PedidoDto> getListadoPedidos() {
		
		logger.debug("Entramos en el metodo getListadoPedidos()" );
		
		try {
			
			List<Pedido> pedidos = pedidoRepository.findAll();
			
			return this.obtieneListadoPedidoDtoDelRepository(pedidos);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo getListado()" );
			
			e.printStackTrace();
		}
			
		return new ArrayList<PedidoDto>();
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

	private AccionRespuesta devolverDatosPedidoDto(PedidoDto pedidoDto, Pedido pedidoSave) {
		
		AccionRespuesta respuesta = new AccionRespuesta();
		
		//Guardado el cliente se devuelve en su dto
		if(pedidoSave != null && pedidoSave.getId() != null) {
			
			pedidoDto.setId(pedidoSave.getId());
			
			respuesta.setId(pedidoSave.getId());
			
			respuesta.setCodigo("OK");
						
			respuesta.setResultado(Boolean.TRUE);
			
			HashMap<String, Object> data= new HashMap<String, Object> ();
			
			data.put("pedidoDto", pedidoDto);
			
			respuesta.setData(data);
			
		}else {
			
			respuesta.setCodigo("NOK");
						
			respuesta.setResultado(Boolean.FALSE);
			
			HashMap<String, Object> data= new HashMap<String, Object> ();
			
			data.put("pedidoDto", pedidoDto);
			
			respuesta.setData(data);
		}
		
		return respuesta;
	}
	
	private AccionRespuesta devolverDatosActualizadosPedidoDto(PedidoDto pedidoDto, Pedido pedidoSave) {
		
		AccionRespuesta respuesta = new AccionRespuesta();
		
		if(pedidoDto != null && pedidoSave != null) {
			
			respuesta.setId(pedidoSave.getId());
			
			respuesta.setCodigo("OK");
						
			respuesta.setResultado(Boolean.TRUE);
			
			HashMap<String, Object> data= new HashMap<String, Object> ();
			
			data.put("pedidoDto", pedidoDto);
			
			respuesta.setData(data);
			
		}else {
			
			respuesta.setId(-1L);
			
			respuesta.setCodigo("NOK");
			
			respuesta.setResultado(Boolean.FALSE);
			
			HashMap<String, Object> data= new HashMap<String, Object> ();
			
			data.put("pedidoDto", pedidoDto);
			
			respuesta.setData(data);			
		}
		
		return respuesta;		
	}
	
	private List<PedidoDto> obtieneListadoPedidoDtoDelRepository(List<Pedido> pedidos){
		
		List<PedidoDto> pedidosDto = new ArrayList<PedidoDto>();
		
		if(CollectionUtils.isNotEmpty(pedidos) ) {
			
			for(Pedido pedido  : pedidos) {
				
				PedidoDto pedidoDto = new PedidoDto();	
				
				pedidoDto.setId(pedido.getId());
				pedidoDto.setCodigo(pedido.getCodigo());
				pedidoDto.setFechaPedido(pedido.getFechaPedido());
				pedidoDto.setArticulo(pedido.getArticulo());
				pedidoDto.setCantidad(pedido.getCantidad());
				pedidoDto.setBaseImponibleTotal(pedido.getBaseImponibleTotal());
				pedidoDto.setImpuesto(pedido.getImpuesto());
				pedidoDto.setImporteTotal(pedido.getImporteTotal());
				
				pedidosDto.add(pedidoDto);				
			}
		}
		
		return pedidosDto;
	}
	
	
}
