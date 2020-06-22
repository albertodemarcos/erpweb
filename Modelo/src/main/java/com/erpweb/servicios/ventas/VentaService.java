package com.erpweb.servicios.ventas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpweb.dto.VentaDto;
import com.erpweb.entidades.usuarios.Usuario;
import com.erpweb.entidades.ventas.Venta;
import com.erpweb.repositorios.ventas.VentaRepository;
import com.erpweb.utiles.AccionRespuesta;


@Service
public class VentaService {

	@Autowired
	private VentaRepository ventaRepository;
	
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	public AccionRespuesta crearVentaDesdeVentaDto(VentaDto ventaDto) {
		
		logger.debug("Entramos en el metodo crearVentaDesdeVentaDto() con ID={}", ventaDto.getId() );
		
		Venta venta = new Venta();
		
		venta.setCodigo(ventaDto.getCodigo());
		venta.setFechaCreacion(ventaDto.getFechaCreacion());
		venta.setFechaInicio(ventaDto.getFechaInicio());
		venta.setFechaFin(ventaDto.getFechaFin());
		venta.setDescripcion(ventaDto.getDescripcion());
		venta.setBaseImponibleTotal(ventaDto.getBaseImponibleTotal());
		venta.setImporteTotal(ventaDto.getImporteTotal());

		try {
			
			//Guardamos la venta en base de datos
			ventaRepository.save(venta);
			
			return this.devolverDatosVentaDto(ventaDto, venta);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo crearVentaDesdeVentaDto() con ID={}", ventaDto.getId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
	}
	
	public AccionRespuesta actualizarVentaDesdeVentaDto(VentaDto ventaDto) {
		
		logger.debug("Entramos en el metodo crearventaDesdeventaDto() con ID={}", ventaDto.getId() );
		
		Venta venta = new Venta();

		venta.setId(ventaDto.getId());
		venta.setCodigo(ventaDto.getCodigo());
		venta.setFechaCreacion(ventaDto.getFechaCreacion());
		venta.setFechaInicio(ventaDto.getFechaInicio());
		venta.setFechaFin(ventaDto.getFechaFin());
		venta.setDescripcion(ventaDto.getDescripcion());
		venta.setBaseImponibleTotal(ventaDto.getBaseImponibleTotal());
		venta.setImporteTotal(ventaDto.getImporteTotal());

		try {
			
			//Guardamos la venta en base de datos
			ventaRepository.save(venta);
			
			return this.devolverDatosActualizadosVentaDto(ventaDto, venta);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo actualizarVentaDesdeVentaDto() con ID={}", ventaDto.getId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
	}
	
	public AccionRespuesta eliminarVenta(Venta venta) {
		
		logger.debug("Entramos en el metodo eliminarVenta() con ID={}", venta.getId() );
		
		if(venta == null || venta.getId() == null) {
			
			return new AccionRespuesta();
		}
		
		try {
			
			//Elimnamos la venta
			ventaRepository.deleteById(venta.getId());
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo eliminarVenta() con ID={}", venta.getId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public AccionRespuesta eliminarVentaPorId(Long ventaId) {
		
		logger.error("Entramos en el metodo eliminarVentaPorId() con id={}", ventaId );
				
		try {
			
			//Elimnamos la venta
			ventaRepository.deleteById(ventaId);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo eliminarVentaPorId() con id={}", ventaId );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
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
				ventaDto.setImporteTotal(venta.getImporteTotal());
				
				ventasDto.add(ventaDto);				
			}
		}
		
		return ventasDto;
	}
	
}
