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

import com.erpweb.dto.FacturaDto;
import com.erpweb.entidades.usuarios.Usuario;
import com.erpweb.entidades.ventas.Factura;
import com.erpweb.repositorios.ventas.FacturaRepository;
import com.erpweb.utiles.AccionRespuesta;


@Service
public class FacturaService {

	@Autowired
	private FacturaRepository facturaRepository;
	
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	public AccionRespuesta crearFacturaDesdeFacturaDto(FacturaDto facturaDto) {
		
		logger.debug("Entramos en el metodo crearFacturaDesdeFacturaDto() con ID={}", facturaDto.getId() );
		
		Factura factura = new Factura();
		
		factura.setCodigo(facturaDto.getCodigo());
		factura.setFechaCreacion(facturaDto.getFechaCreacion());
		factura.setFechaInicio(facturaDto.getFechaInicio());
		factura.setFechaFin(facturaDto.getFechaFin());
		factura.setDescripcion(facturaDto.getDescripcion());
		factura.setBaseImponible(facturaDto.getBaseImponible());
		factura.setImpuesto(facturaDto.getImpuesto());
		factura.setImporteTotal(facturaDto.getImporteTotal());
		
		try {
			
			//Guardamos la factura en base de datos
			Factura facturaSave = facturaRepository.save(factura);
			
			return this.devolverDatosFacturaDto(facturaDto, facturaSave);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo crearFacturaDesdeFacturaDto() con ID={}", facturaDto.getId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
	}
	
	public AccionRespuesta actualizarFacturaDesdeFacturaDto(FacturaDto facturaDto) {
		
		logger.debug("Entramos en el metodo actualizarFacturaDesdeFacturaDto() con ID={}", facturaDto.getId() );
		
		Factura factura = new Factura();
		
		factura.setId(facturaDto.getId());
		factura.setCodigo(facturaDto.getCodigo());
		factura.setFechaCreacion(facturaDto.getFechaCreacion());
		factura.setFechaInicio(facturaDto.getFechaInicio());
		factura.setFechaFin(facturaDto.getFechaFin());
		factura.setDescripcion(facturaDto.getDescripcion());
		factura.setBaseImponible(facturaDto.getBaseImponible());
		factura.setImpuesto(facturaDto.getImpuesto());
		factura.setImporteTotal(facturaDto.getImporteTotal());
		
		try {
			
			//Guardamos la factura en base de datos
			Factura facturaSave = facturaRepository.save(factura);
			
			return this.devolverDatosActualizadosFacturaDto(facturaDto, facturaSave);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo actualizarFacturaDesdeFacturaDto() con ID={}", facturaDto.getId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
	}
	
	public AccionRespuesta eliminarFactura(Factura factura) {
		
		logger.debug("Entramos en el metodo eliminarFactura()" );
		
		if(factura == null || factura.getId() == null) {
			
			logger.error("ERROR en el metodo eliminarFactura()");
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
		
		try {
			
			//Elimnamos la factura
			facturaRepository.deleteById(factura.getId());
			
			return new AccionRespuesta(-2L, "OK", Boolean.TRUE);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo eliminarFactura() con ID={}", factura.getId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
	}
	
	public AccionRespuesta eliminarFacturaPorId(Long facturaId) {
		
		logger.error("Entramos en el metodo eliminarFacturaPorId()" );
		
		if( facturaId == null) {
			
			logger.error("ERROR en el metodo eliminarFacturaPorId()");
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
		
		try {
			
			//Elimnamos la factura
			facturaRepository.deleteById(facturaId);
			
			return new AccionRespuesta(-2L, "OK", Boolean.TRUE);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo eliminarFacturaPorId() con id={}", facturaId );
			
			e.printStackTrace();
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
	}
	
	public FacturaDto obtenerFacturaDtoDesdeFactura(Long id) {
		
		logger.debug("Entramos en el metodo obtenerFacturaDtoDesdeFactura() con ID={}", id );

		Optional<Factura> facturaOptional = facturaRepository.findById(id);
		
		Factura factura = facturaOptional.get(); 
		
		if(factura == null) {
			return new FacturaDto();
		}
		
		FacturaDto facturaDto = new FacturaDto();
		
		try {
			
			facturaDto.setId(factura.getId());
			facturaDto.setCodigo(factura.getCodigo());
			facturaDto.setFechaCreacion(factura.getFechaCreacion());
			facturaDto.setFechaInicio(factura.getFechaInicio());
			facturaDto.setFechaFin(factura.getFechaFin());
			facturaDto.setDescripcion(factura.getDescripcion());
			facturaDto.setBaseImponible(factura.getBaseImponible());
			facturaDto.setImpuesto(factura.getImpuesto());
			facturaDto.setImporteTotal(factura.getImporteTotal());
			
		} catch(Exception e) {
			
			logger.error("Error en el metodo obtenerFacturaDtoDesdeFactura() con ID={}", id );
			
			e.printStackTrace();
		}
		
		return facturaDto;
	}
	
	public List<FacturaDto> getListadoFacturas() {
		
		logger.debug("Entramos en el metodo getListadoFacturas()" );
		
		try {
			
			List<Factura> facturas = facturaRepository.findAll();
			
			return this.obtieneListadoFacturaDtoDelRepository(facturas);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo getListadoFacturas()" );
			
			e.printStackTrace();
		}
			
		return new ArrayList<FacturaDto>();
	}

	public AccionRespuesta getFactura(Long facturaId, Usuario user) {
		
		logger.debug("Entramos en el metodo getFactura()");
		
		if( facturaId == null) {
			
			return new AccionRespuesta(-1L, "Error, existe la factura", Boolean.FALSE);
		}
		
		FacturaDto facturaDto = this.obtenerFacturaDtoDesdeFactura(facturaId);
		
		AccionRespuesta AccionRespuesta = new AccionRespuesta();
		
		if( facturaDto != null ) {
			
			AccionRespuesta.setId( facturaDto.getId() );
			
			AccionRespuesta.setRespuesta("");
			
			AccionRespuesta.setResultado(Boolean.TRUE);
			
			HashMap<String, Object> mapa = new HashMap<String, Object>();
			
			mapa.put("facturaDto", facturaDto);
			
			AccionRespuesta.setData(new HashMap<String, Object>(mapa));
			
		}else {
			
			AccionRespuesta.setId( -1L );
			
			AccionRespuesta.setRespuesta("Error, no se ha podido recuperar la factura");
			
			AccionRespuesta.setResultado(Boolean.FALSE);
		}
		
		return AccionRespuesta;
	}
	
	public AccionRespuesta getCrearEditarFactura(FacturaDto facturaDto, Usuario user) {
		
		logger.debug("Entramos en el metodo getCrearEditarFactura() con usuario={}", user.getId() );
		
		if( facturaDto.getId() != null && facturaDto.getId().longValue() > 0) {
			
			logger.debug("Se va a realizar una actualizacion de la Factura con usuario={}", user.getId() );
			
			return this.actualizarFacturaDesdeFacturaDto(facturaDto);
			
		} else {
			
			logger.debug("Se va a crear una Factura con usuario={}", user.getId() );
			
			return this.crearFacturaDesdeFacturaDto(facturaDto);
		}
	}
	
	private AccionRespuesta devolverDatosFacturaDto(FacturaDto facturaDto, Factura facturaSave) {
		
		AccionRespuesta respuesta = new AccionRespuesta();
		
		//Guardado el cliente se devuelve en su dto
		if(facturaSave != null && facturaSave.getId() != null) {
			
			facturaDto.setId(facturaSave.getId());
			
			respuesta.setId(facturaSave.getId());
			
			respuesta.setCodigo("OK");
						
			respuesta.setResultado(Boolean.TRUE);
			
			HashMap<String, Object> data= new HashMap<String, Object> ();
			
			data.put("facturaDto", facturaDto);
			
			respuesta.setData(data);
			
		}else {
			
			respuesta.setCodigo("NOK");
						
			respuesta.setResultado(Boolean.FALSE);
			
			HashMap<String, Object> data= new HashMap<String, Object> ();
			
			data.put("facturaDto", facturaDto);
			
			respuesta.setData(data);
		}
		
		return respuesta;
	}
	
	private AccionRespuesta devolverDatosActualizadosFacturaDto(FacturaDto facturaDto, Factura facturaSave) {
		
		AccionRespuesta respuesta = new AccionRespuesta();
		
		if(facturaSave != null && facturaDto != null) {
			
			facturaDto.setId(facturaSave.getId());
			
			respuesta.setId(facturaSave.getId());
			
			respuesta.setCodigo("OK");
						
			respuesta.setResultado(Boolean.TRUE);
			
			HashMap<String, Object> data= new HashMap<String, Object> ();
			
			data.put("facturaDto", facturaDto);
			
			respuesta.setData(data);
			
		}else {
			
			respuesta.setId(-1L);
			
			respuesta.setCodigo("NOK");
			
			respuesta.setResultado(Boolean.FALSE);
			
			HashMap<String, Object> data= new HashMap<String, Object> ();
			
			data.put("facturaDto", facturaDto);
			
			respuesta.setData(data);			
		}
		
		return respuesta;		
	}
	
	private List<FacturaDto> obtieneListadoFacturaDtoDelRepository(List<Factura> facturas){
		
		List<FacturaDto> facturasDto = new ArrayList<FacturaDto>();
		
		if(CollectionUtils.isNotEmpty(facturas) ) {
			
			for(Factura factura  : facturas) {
				
				FacturaDto facturaDto = new FacturaDto();
				
				facturaDto.setId(factura.getId());
				facturaDto.setCodigo(factura.getCodigo());
				facturaDto.setFechaCreacion(factura.getFechaCreacion());
				facturaDto.setFechaInicio(factura.getFechaInicio());
				facturaDto.setFechaFin(factura.getFechaFin());
				facturaDto.setDescripcion(factura.getDescripcion());
				facturaDto.setBaseImponible(factura.getBaseImponible());
				facturaDto.setImpuesto(factura.getImpuesto());
				facturaDto.setImporteTotal(factura.getImporteTotal());
				
				facturasDto.add(facturaDto);				
			}
		}
		
		return facturasDto;
	}
	
}
