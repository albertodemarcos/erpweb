package com.erpweb.servicios.ventas;

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

import com.erpweb.dto.VentaDto;
import com.erpweb.entidades.inventario.Articulo;
import com.erpweb.entidades.usuarios.Usuario;
import com.erpweb.entidades.ventas.LineaVenta;
import com.erpweb.entidades.ventas.Venta;
import com.erpweb.patrones.builder.constructores.ConstructorVenta;
import com.erpweb.repositorios.inventario.ArticuloRepository;
import com.erpweb.repositorios.ventas.FacturaRepository;
import com.erpweb.repositorios.ventas.LineaVentaRepository;
import com.erpweb.repositorios.ventas.VentaRepository;
import com.erpweb.utiles.AccionRespuesta;


@Service
public class VentaService {

	//Services
	@Autowired 
	private RegeneraFacturasService regeneraFacturasService;
	
	//Repositorys
	@Autowired
	private VentaRepository ventaRepository;
	@Autowired
	private LineaVentaRepository lineaVentaRepository;
	@Autowired
	private ArticuloRepository articuloRepository;
	@Autowired 
	private FacturaRepository facturaRepository;
	
	//Otros
	@Autowired
	private ConstructorVenta constructorVenta;
	
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	public AccionRespuesta crearVentaDesdeVentaDto(VentaDto ventaDto) {
		
		logger.debug("Entramos en el metodo crearVentaDesdeVentaDto() con ID={}", ventaDto.getId() );

		try {
			
			//Guardamos la venta en base de datos
			Venta ventaSave = constructorVenta.crearEntidadLineasEntidad(ventaDto);
			
			return this.devolverDatosVentaDto(ventaDto, ventaSave);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo crearVentaDesdeVentaDto() con ID={}", ventaDto.getId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
	}
	
	public AccionRespuesta actualizarVentaDesdeVentaDto(VentaDto ventaDto) {
		
		logger.debug("Entramos en el metodo crearventaDesdeventaDto() con ID={}", ventaDto.getId() );
		
		try {
			
			Optional<Venta> ventaOptional = ventaRepository.findById( ventaDto.getId() );
			
			Venta venta = ventaOptional.orElse(null);
			
			if( venta == null ) {
				
				return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
			}
			
			venta.setCodigo(ventaDto.getCodigo());
			venta.setFechaCreacion(ventaDto.getFechaCreacion());
			venta.setFechaInicio(ventaDto.getFechaInicio());
			venta.setFechaFin(ventaDto.getFechaFin());
			venta.setDescripcion(ventaDto.getDescripcion());
			venta.setBaseImponibleTotal(ventaDto.getBaseImponibleTotal());
			venta.setImpuesto(ventaDto.getImpuesto());
			venta.setImporteTotal(ventaDto.getImporteTotal());
			
			Set<Long> articuloIds = ventaDto.getArticulosCantidad().entrySet().stream().map(art -> art.getKey() ).collect(Collectors.toSet());
			
			List<Articulo> articulos = articuloRepository.findByIdIn( articuloIds );
			
			//Eliminamos las anteriores lineas
			lineaVentaRepository.deleteAll( venta.getLineasVenta() );

			Set<LineaVenta> lineasVenta = new HashSet<LineaVenta>();
			
			//Por cada articulo, se crea una linea de contrato
			for(Articulo articulo : articulos) {
				
				LineaVenta lineaVenta = new LineaVenta();
				
				lineaVenta.setVenta(venta);
				lineaVenta.setArticulo(articulo);
				lineaVenta.setBaseImponible(articulo.getBaseImponible());
				lineaVenta.setImporteTotal(articulo.getImporteTotal());
				BigDecimal importeImpuesto = new BigDecimal("" + (articulo.getImporteTotal().doubleValue() - articulo.getBaseImponible().doubleValue()) );
				lineaVenta.setImporteImpuesto( importeImpuesto ); 
				lineaVenta.setDescripcionLinea("");
				
				lineasVenta.add(lineaVenta);
			}
			
			lineaVentaRepository.saveAll(lineasVenta);
			
			venta.getLineasVenta().clear();
			
			venta.getLineasVenta().addAll(lineasVenta);
			
			//Actualizamos la venta en base de datos
			Venta ventaSave =  ventaRepository.saveAndFlush(venta);
			
			//Actualizamos las lineas de factura
			this.regeneraFacturasService.actualizarFacturaVenta(ventaSave);
			
			return this.devolverDatosActualizadosVentaDto(ventaDto, ventaSave);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo actualizarVentaDesdeVentaDto() con ID={}", ventaDto.getId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
	}
	
	public AccionRespuesta eliminarVenta(Venta venta) {
		
		logger.debug("Entramos en el metodo eliminarVenta()" );
		
		if(venta == null || venta.getId() == null) {
			
			logger.error("ERROR en el metodo eliminarVenta()");
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
		
		try {
			
			//Elimnamos la venta
			ventaRepository.deleteById(venta.getId());
			
			return new AccionRespuesta(-2L, "OK", Boolean.TRUE);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo eliminarVenta() con ID={}", venta.getId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
	}
	
	public AccionRespuesta eliminarVentaPorId(Long ventaId) {
		
		logger.error("Entramos en el metodo eliminarVentaPorId()" );
		
		if( ventaId == null) {
			
			logger.error("ERROR en el metodo eliminarVentaPorId()");
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
		
		try {
			
			//Paso previo
			Long facturaId = ventaRepository.obtieneFacturaIdDesdeVentaId(ventaId);
			
			if( facturaId != null && facturaId.longValue() > 0) {
				facturaRepository.deleteById(facturaId);
			}
			
			//Elimnamos la venta
			ventaRepository.deleteById(ventaId);
			
			return new AccionRespuesta(-2L, "OK", Boolean.TRUE);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo eliminarVentaPorId() con id={}", ventaId );
			
			e.printStackTrace();
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
	}
	
	public VentaDto obtenerVentaDtoDesdeVenta(Long id) {
		
		logger.debug("Entramos en el metodo obtenerVentaDtoDesdeVenta() con ID={}", id );
		
		Optional<Venta> ventaOptional = ventaRepository.findById(id);
		
		Venta venta = ventaOptional.get();
		
		if(venta == null) {
			return new VentaDto();
		}
		
		VentaDto ventaDto = new VentaDto();
		
		try {
			
			ventaDto.setId(venta.getId());
			ventaDto.setCodigo(venta.getCodigo());
			ventaDto.setFechaCreacion(venta.getFechaCreacion());
			ventaDto.setFechaInicio(venta.getFechaInicio());
			ventaDto.setFechaFin(venta.getFechaFin());
			ventaDto.setDescripcion(venta.getDescripcion());
			ventaDto.setBaseImponibleTotal(venta.getBaseImponibleTotal());
			ventaDto.setImpuesto(venta.getImpuesto());
			ventaDto.setImporteTotal(venta.getImporteTotal());
			
		} catch(Exception e) {
			
			logger.error("Error en el metodo obtenerVentaDtoDesdeVenta() con con ID={}", id );
			
			e.printStackTrace();
		}
		
		return ventaDto;
	}
	
	public List<VentaDto> getListadoVentas() {
		
		logger.debug("Entramos en el metodo getListadoVentas()" );
		
		try {
			
			List<Venta> ventas = ventaRepository.findAll();
			
			return this.obtieneListadoVentaDtoDelRepository(ventas);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo getListadoVentas()" );
			
			e.printStackTrace();
		}
			
		return new ArrayList<VentaDto>();
	}
	
	public AccionRespuesta getVenta(Long ventaId, Usuario user) {
		
		logger.debug("Entramos en el metodo getCrearventa()");
		
		if( ventaId == null) {
			
			return new AccionRespuesta(-1L, "Error, no existe la venta", Boolean.FALSE);
		}
		
		VentaDto ventaDto = this.obtenerVentaDtoDesdeVenta(ventaId);
		
		AccionRespuesta AccionRespuesta = new AccionRespuesta();
		
		if( ventaDto != null ) {
			
			AccionRespuesta.setId( ventaDto.getId() );
			
			AccionRespuesta.setRespuesta("");
			
			AccionRespuesta.setResultado(Boolean.TRUE);
			
			HashMap<String, Object> mapa = new HashMap<String, Object>();
			
			mapa.put("ventaDto", ventaDto);
			
			AccionRespuesta.setData(new HashMap<String, Object>(mapa));
			
		}else {
			
			AccionRespuesta.setId( -1L );
			
			AccionRespuesta.setRespuesta("Error, no se ha podido recuperar la venta");
			
			AccionRespuesta.setResultado(Boolean.FALSE);
		}
		
		return AccionRespuesta;
	}
	
	public AccionRespuesta getCrearEditarVenta(VentaDto ventaDto, Usuario user) {
		
		logger.debug("Entramos en el metodo getCrearEditarVenta() con usuario={}", user.getId() );
		
		if( ventaDto.getId() != null && ventaDto.getId().longValue() > 0) {
			
			logger.debug("Se va a realizar una actualizacion de la Venta con usuario={}", user.getId() );
			
			return this.actualizarVentaDesdeVentaDto(ventaDto);
			
		} else {
			
			logger.debug("Se va a crear una Venta con usuario={}", user.getId() );
			
			return this.crearVentaDesdeVentaDto(ventaDto);
		}
	}
	
	private AccionRespuesta devolverDatosVentaDto(VentaDto ventaDto, Venta ventaSave) {
		
		AccionRespuesta respuesta = new AccionRespuesta();
		
		//Guardado el cliente se devuelve en su dto
		if(ventaSave != null && ventaSave.getId() != null) {
			
			ventaDto.setId(ventaSave.getId());
			
			respuesta.setId(ventaSave.getId());
			
			respuesta.setCodigo("OK");
						
			respuesta.setResultado(Boolean.TRUE);
			
			HashMap<String, Object> data= new HashMap<String, Object> ();
			
			data.put("ventaDto", ventaDto);
			
			respuesta.setData(data);
			
		}else {
			
			respuesta.setCodigo("NOK");
						
			respuesta.setResultado(Boolean.FALSE);
			
			HashMap<String, Object> data= new HashMap<String, Object> ();
			
			data.put("ventaDto", ventaDto);
			
			respuesta.setData(data);
		}
		
		return respuesta;
	}
	
	private AccionRespuesta devolverDatosActualizadosVentaDto(VentaDto ventaDto, Venta ventaSave) {
		
		AccionRespuesta respuesta = new AccionRespuesta();
		
		if(ventaSave != null && ventaDto != null) {
			
			ventaDto.setId(ventaSave.getId());
			
			respuesta.setId(ventaSave.getId());
			
			respuesta.setCodigo("OK");
						
			respuesta.setResultado(Boolean.TRUE);
			
			HashMap<String, Object> data= new HashMap<String, Object> ();
			
			data.put("ventaDto", ventaDto);
			
			respuesta.setData(data);
			
		}else {
			
			respuesta.setId(-1L);
			
			respuesta.setCodigo("NOK");
			
			respuesta.setResultado(Boolean.FALSE);
			
			HashMap<String, Object> data= new HashMap<String, Object> ();
			
			data.put("ventaDto", ventaDto);
			
			respuesta.setData(data);			
		}
		
		return respuesta;		
	}
	
	private List<VentaDto> obtieneListadoVentaDtoDelRepository(List<Venta> ventas){
		
		List<VentaDto> ventasDto = new ArrayList<VentaDto>();
		
		if(CollectionUtils.isNotEmpty(ventas) ) {
			
			for(Venta venta : ventas) {
				
				VentaDto ventaDto = new VentaDto();
				
				ventaDto.setId(venta.getId());
				ventaDto.setCodigo(venta.getCodigo());
				ventaDto.setFechaCreacion(venta.getFechaCreacion());
				ventaDto.setFechaInicio(venta.getFechaInicio());
				ventaDto.setFechaFin(venta.getFechaFin());
				ventaDto.setDescripcion(venta.getDescripcion());
				ventaDto.setBaseImponibleTotal(venta.getBaseImponibleTotal());
				ventaDto.setImpuesto(venta.getImpuesto());
				ventaDto.setImporteTotal(venta.getImporteTotal());
				
				ventasDto.add(ventaDto);				
			}
		}
		
		return ventasDto;
	}
	
}
