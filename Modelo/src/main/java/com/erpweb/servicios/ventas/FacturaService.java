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

import com.erpweb.dto.ArticuloDto;
import com.erpweb.dto.FacturaDto;
import com.erpweb.dto.LineaFacturaDto;
import com.erpweb.entidades.inventario.Articulo;
import com.erpweb.entidades.ventas.Factura;
import com.erpweb.entidades.ventas.LineaFactura;
import com.erpweb.patrones.builder.constructores.ConstructorFactura;
import com.erpweb.repositorios.inventario.ArticuloRepository;
import com.erpweb.repositorios.ventas.FacturaRepository;
import com.erpweb.repositorios.ventas.LineaFacturaRepository;
import com.erpweb.utiles.AccionRespuesta;

@Service
public class FacturaService {

	@Autowired
	private FacturaRepository facturaRepository;
	@Autowired
	private LineaFacturaRepository lineaFacturaRepository;
	@Autowired
	private ArticuloRepository articuloRepository;
	
	@Autowired
	private ConstructorFactura constructorFactura;
	
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	public AccionRespuesta crearFacturaDesdeFacturaDto(FacturaDto facturaDto) {
		
		logger.debug("Entramos en el metodo crearFacturaDesdeFacturaDto() con ID={}", facturaDto.getId() );
		
		try {
			
			//Guardamos la factura en base de datos
			Factura facturaSave = constructorFactura.crearEntidadLineasEntidad(facturaDto);
			
			return this.devolverDatosFacturaDto(facturaDto, facturaSave);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo crearFacturaDesdeFacturaDto() con ID={}", facturaDto.getId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
	}
	
	public AccionRespuesta actualizarFacturaDesdeFacturaDto(FacturaDto facturaDto) {
		
		logger.debug("Entramos en el metodo actualizarFacturaDesdeFacturaDto() con ID={}", facturaDto.getId() );
		
		try {
			
			Optional<Factura> facturaOptional = facturaRepository.findById( facturaDto.getId() );
			
			Factura factura = facturaOptional.orElse(null);
			
			if( factura == null ) {
				
				return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
			}
			
			factura.setCodigo(facturaDto.getCodigo());
			//factura.setFechaCreacion(facturaDto.getFechaCreacion());
			factura.setFechaFactura(facturaDto.getFechaInicio());
			factura.setDescripcion(facturaDto.getDescripcion());
			factura.setBaseImponible(facturaDto.getBaseImponible());
			factura.setImpuesto(facturaDto.getImpuesto());
			factura.setImporteTotal(facturaDto.getImporteTotal());
			
			Set<Long> articuloIds = facturaDto.getArticulosCantidad().entrySet().stream().map(art -> art.getKey() ).collect(Collectors.toSet());
			
			List<Articulo> articulos = articuloRepository.findByIdIn( articuloIds );
			
			//Eliminamos las anteriores lineas
			lineaFacturaRepository.deleteAll( factura.getLineasFactura() );

			Set<LineaFactura> lineasVenta = new HashSet<LineaFactura>();
			
			//Por cada articulo, se crea una linea de contrato
			for(Articulo articulo : articulos) {
				
				LineaFactura lineaFactura = new LineaFactura();
				
				lineaFactura.setFactura(factura);
				lineaFactura.setArticulo(articulo);
				lineaFactura.setBaseImponible(articulo.getBaseImponible());
				lineaFactura.setImporteTotal(articulo.getImporteTotal());
				BigDecimal importeImpuesto = new BigDecimal("" + (articulo.getImporteTotal().doubleValue() - articulo.getBaseImponible().doubleValue()) );
				lineaFactura.setImporteImpuesto( importeImpuesto ); 
				lineaFactura.setDescripcionLinea("");
				
				lineasVenta.add(lineaFactura);
			}
			
			lineaFacturaRepository.saveAll(lineasVenta);
			
			factura.getLineasFactura().clear();
			
			factura.getLineasFactura().addAll(lineasVenta);
			
			facturaRepository.saveAndFlush(factura);
			
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
			facturaDto.setFechaInicio(factura.getFechaFactura());
			facturaDto.setDescripcion(factura.getDescripcion());
			facturaDto.setBaseImponible(factura.getBaseImponible());
			facturaDto.setImpuesto(factura.getImpuesto());
			facturaDto.setImporteTotal(factura.getImporteTotal());
			
			//Rellenamos la lineas
			Set<LineaFacturaDto> lineasFacturaDto = this.obtieneLineasFacturaDtoDeFactura(factura);
			facturaDto.getLineasFacturaDto().addAll(lineasFacturaDto);
			
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

	public AccionRespuesta getFactura(Long facturaId) {
		
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
	
	public AccionRespuesta getCrearEditarFactura(FacturaDto facturaDto) {
		
		logger.debug("Entramos en el metodo getCrearEditarFactura()" );
		
		if( facturaDto.getId() != null && facturaDto.getId().longValue() > 0) {
			
			logger.debug("Se va a realizar una actualizacion de la Factura" );
			
			return this.actualizarFacturaDesdeFacturaDto(facturaDto);
			
		} else {
			
			logger.debug("Se va a crear una Factura con usuario={}");
			
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
				facturaDto.setFechaInicio(factura.getFechaFactura());
				facturaDto.setDescripcion(factura.getDescripcion());
				facturaDto.setBaseImponible(factura.getBaseImponible());
				facturaDto.setImpuesto(factura.getImpuesto());
				facturaDto.setImporteTotal(factura.getImporteTotal());
				
				facturasDto.add(facturaDto);				
			}
		}
		
		return facturasDto;
	}
	
	private Set<LineaFacturaDto> obtieneLineasFacturaDtoDeFactura(Factura factura) {
		
		Set<LineaFacturaDto> lineasFacturaDto = new HashSet<LineaFacturaDto>();
		
		if( factura != null && CollectionUtils.isNotEmpty(factura.getLineasFactura()) ) {
		
			for(LineaFactura lineaFactura : factura.getLineasFactura()) {
				
				LineaFacturaDto lineaFacturaDto = new LineaFacturaDto();
				
				//Primero: seteamos los objetos simples
				lineaFacturaDto.setId(lineaFactura.getId());
				lineaFacturaDto.setCantidad(lineaFactura.getCantidad());
				lineaFacturaDto.setBaseImponible(lineaFactura.getBaseImponible());
				lineaFacturaDto.setImporteImpuesto(lineaFactura.getImporteImpuesto());
				lineaFacturaDto.setImporteTotal(lineaFactura.getImporteTotal());
				
				//Segundo: seteamos el contrato
				lineaFacturaDto.setFacturaId(factura.getId());
				
				//Tercero: seteamos el articulo de la linea
				ArticuloDto articuloDto = new ArticuloDto();
				
				if( lineaFactura.getArticulo() != null) {
					
					articuloDto.setId(lineaFactura.getArticulo().getId());
					articuloDto.setCodigo(lineaFactura.getArticulo().getCodigo());
					articuloDto.setNombre(lineaFactura.getArticulo().getNombre());
					articuloDto.setBaseImponible(lineaFactura.getArticulo().getBaseImponible());
					articuloDto.setImpuesto(lineaFactura.getArticulo().getImpuesto());
					articuloDto.setImporteTotal(lineaFactura.getArticulo().getImporteTotal());
				}
				
				lineaFacturaDto.setArticuloDto(articuloDto);
				
				//Ultimo: introducimos la linea en las lineas de contrato
				lineasFacturaDto.add(lineaFacturaDto);
			}
		}
		
		return lineasFacturaDto;
	}
	
}
