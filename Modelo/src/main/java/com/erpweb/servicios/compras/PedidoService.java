package com.erpweb.servicios.compras;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpweb.dto.PedidoDto;
import com.erpweb.entidades.compras.LineaPedido;
import com.erpweb.entidades.compras.Pedido;
import com.erpweb.entidades.inventario.Articulo;
import com.erpweb.entidades.usuarios.Usuario;
import com.erpweb.patrones.builder.constructores.ConstructorPedido;
import com.erpweb.repositorios.compras.LineaPedidoRepository;
import com.erpweb.repositorios.compras.PedidoRepository;
import com.erpweb.repositorios.inventario.ArticuloRepository;
import com.erpweb.repositorios.ventas.FacturaRepository;
import com.erpweb.servicios.ventas.RegeneraFacturasService;
import com.erpweb.utiles.AccionRespuesta;

@Service
public class PedidoService {
	
	//Services
	@Autowired 
	private RegeneraFacturasService regeneraFacturasService;

	//Repositorys
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private LineaPedidoRepository lineaPedidoRepository;
	@Autowired
	private ArticuloRepository articuloRepository;
	@Autowired 
	private FacturaRepository facturaRepository;
	
	//Otros
	@Autowired
	private ConstructorPedido constructorPedido; 
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	
	public AccionRespuesta crearPedidoDesdePedidoDto(PedidoDto pedidoDto) {
		
		logger.debug("Entramos en el metodo crearPedidoDesdePedidoDto() con ID={}", pedidoDto.getId() );

		try {
			
			Pedido pedioSave = constructorPedido.crearEntidadLineasEntidad(pedidoDto);
			
			return this.devolverDatosPedidoDto(pedidoDto, pedioSave);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo crearPedidoDesdePedidoDto() con ID={}", pedidoDto.getId() );
			
			e.printStackTrace();
						
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
	}
	
	public AccionRespuesta actualizarPedidoDesdePedidoDto(PedidoDto pedidoDto) {
		
		logger.debug("Entramos en el metodo actualizarPedidoDesdePedidoDto() con ID={}", pedidoDto.getId() );
		
		try {
			
			//Recuperamos el pedido
			Optional<Pedido> pedidoOptional = pedidoRepository.findById( pedidoDto.getId() );
			
			Pedido pedido = pedidoOptional.orElse(null);
			
			if( pedido == null ) {
				
				return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
			}
			
			pedido.setCodigo(pedidoDto.getCodigo());
			pedido.setFechaPedido(pedidoDto.getFechaPedido());
			pedido.setBaseImponibleTotal(pedidoDto.getBaseImponibleTotal());
			pedido.setImpuesto(pedidoDto.getImpuesto());
			pedido.setImporteTotal(pedidoDto.getImporteTotal());
			
			Set<Long> articuloIds = pedidoDto.getArticulosCantidad().entrySet().stream().map(art -> art.getKey() ).collect(Collectors.toSet());
			
			List<Articulo> articulos = articuloRepository.findByIdIn( articuloIds );
			
			//Eliminamos las anteriores lineas
			lineaPedidoRepository.deleteAll( pedido.getLineasPedido() );

			Set<LineaPedido> lineasPedido = new HashSet<LineaPedido>();
			
			//Por cada articulo, se crea una linea de pedido
			for(Articulo articulo : articulos) {
				
				LineaPedido lineaPedido = new LineaPedido();
				
				lineaPedido.setPedido(pedido);
				lineaPedido.setArticulo(articulo);
				lineaPedido.setBaseImponible(articulo.getBaseImponible());
				lineaPedido.setImporteTotal(articulo.getImporteTotal());
				BigDecimal importeImpuesto = new BigDecimal("" + ( articulo.getImporteTotal().doubleValue() - articulo.getBaseImponible().doubleValue() ) );
				lineaPedido.setImporteImpuesto( importeImpuesto ); 
				lineaPedido.setDescripcionLinea("");
				
				lineasPedido.add(lineaPedido);
			}
			
			lineaPedidoRepository.saveAll(lineasPedido);
			
			pedido.getLineasPedido().clear();
			
			pedido.getLineasPedido().addAll(lineasPedido);
			
			//Actualizamos el pedido en base de datos
			Pedido pedioSave = pedidoRepository.saveAndFlush(pedido);
			
			//Actualizamos las lineas de factura
			this.regeneraFacturasService.actualizarFacturaPedido(pedioSave);
			
			return this.devolverDatosActualizadosPedidoDto(pedidoDto, pedioSave);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo actualizarPedidoDesdePedidoDto() con ID={}", pedidoDto.getId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
	}
	
	public AccionRespuesta eliminarPedido(Pedido pedido) {
		
		logger.debug("Entramos en el metodo eliminarPedido()" );
		
		if(pedido == null || pedido.getId() == null) {
			
			logger.error("ERROR en el metodo eliminarPedido()");
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
		
		try {
			
			//Elimnamos el pedido
			pedidoRepository.deleteById(pedido.getId());
			
			return new AccionRespuesta(-2L, "OK", Boolean.TRUE);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo eliminarPedido() con ID={}", pedido.getId() );
			
			e.printStackTrace();
						
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
	}
	
	public AccionRespuesta eliminarPedidoPorId(Long pedidoId) {
		
		logger.error("Entramos en el metodo eliminarPedidoPorId()" );
		
		if( pedidoId == null ) {
			
			logger.error("ERROR en el metodo eliminarPedidoPorId()");
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
				
		try {
			
			//Paso previo
			Long facturaId = pedidoRepository.obtieneFacturaIdDesdePedidoId(pedidoId);
			
			if( facturaId != null && facturaId.longValue() > 0) {
				facturaRepository.deleteById(facturaId);
			}
			
			//Elimnamos el pedido
			pedidoRepository.deleteById(pedidoId);
			
			return new AccionRespuesta(-2L, "OK", Boolean.TRUE);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo eliminarPedidoPorId() con id={}", pedidoId );
			
			e.printStackTrace();
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
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
			
			pedidoDto.setId(pedido.getId());
			pedidoDto.setCodigo(pedido.getCodigo());
			pedidoDto.setFechaPedido(pedido.getFechaPedido());
			//pedidoDto.setArticulo(pedido.getArticulo());
			//pedidoDto.setCantidad(pedido.getCantidad());
			pedidoDto.setBaseImponibleTotal(pedido.getBaseImponibleTotal());
			pedidoDto.setImpuesto(pedido.getImpuesto());
			pedidoDto.setImporteTotal(pedido.getImporteTotal());
			
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
				//pedidoDto.setArticulo(pedido.getArticulo());
				//pedidoDto.setCantidad(pedido.getCantidad());
				pedidoDto.setBaseImponibleTotal(pedido.getBaseImponibleTotal());
				pedidoDto.setImpuesto(pedido.getImpuesto());
				pedidoDto.setImporteTotal(pedido.getImporteTotal());
				
				pedidosDto.add(pedidoDto);				
			}
		}
		
		return pedidosDto;
	}
	
	
}
