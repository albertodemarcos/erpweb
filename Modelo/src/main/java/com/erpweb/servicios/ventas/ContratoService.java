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

import com.erpweb.dto.ContratoDto;
import com.erpweb.entidades.inventario.Articulo;
import com.erpweb.entidades.usuarios.Usuario;
import com.erpweb.entidades.ventas.Contrato;
import com.erpweb.entidades.ventas.LineaContrato;
import com.erpweb.patrones.builder.constructores.ConstructorContrato;
import com.erpweb.repositorios.inventario.ArticuloRepository;
import com.erpweb.repositorios.ventas.ContratoRepository;
import com.erpweb.repositorios.ventas.LineaContratoRepository;
import com.erpweb.utiles.AccionRespuesta;


@Service
public class ContratoService {

	//Services
	@Autowired 
	private RegeneraFacturasService regeneraFacturasService;
		
	//Repositorys
	@Autowired
	private ContratoRepository contratoRepository;
	@Autowired
	private LineaContratoRepository lineaContratoRepository;
	@Autowired
	private ArticuloRepository articuloRepository;
	
	//Otros
	@Autowired
	private ConstructorContrato constructorContrato;
	
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	public AccionRespuesta crearContratoDesdeContratoDto(ContratoDto contratoDto) {
		
		logger.debug("Entramos en el metodo crearContratoDesdeContratoDto() con ID={}", contratoDto.getId() );
		
		try {
			
			//Guardamos el contrato en base de datos
			Contrato contratoSave = constructorContrato.crearEntidadLineasEntidad(contratoDto);
			
			return this.devolverDatosContratoDto(contratoDto, contratoSave);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo crearContratoDesdeContratoDto() con ID={}", contratoDto.getId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
	}
	
	public AccionRespuesta actualizarContratoDesdeContratoDto(ContratoDto contratoDto) {
		
		logger.debug("Entramos en el metodo actualizarContratoDesdeContratoDto() con ID={}", contratoDto.getId() );

		try {
			
			Optional<Contrato> contratoOptional = contratoRepository.findById(contratoDto.getId());
			
			Contrato contrato = contratoOptional.orElse(null);
			
			if( contrato == null) {
				
				return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
			}
			
			contrato.setCodigo(contratoDto.getCodigo());
			contrato.setFechaCreacion(contratoDto.getFechaCreacion());
			contrato.setFechaInicio(contratoDto.getFechaInicio());
			contrato.setFechaFin(contratoDto.getFechaFin());
			contrato.setDescripcion(contratoDto.getDescripcion());
			contrato.setBaseImponibleTotal(contratoDto.getBaseImponibleTotal());
			contrato.setImpuesto(contratoDto.getImpuesto());
			contrato.setImporteTotal(contratoDto.getImporteTotal());
			
			Set<Long> articuloIds = contratoDto.getArticulosCantidad().entrySet().stream().map(art -> art.getKey() ).collect(Collectors.toSet());
			
			List<Articulo> articulos = articuloRepository.findByIdIn( articuloIds );
			
			//Eliminamos las anteriores lineas
			lineaContratoRepository.deleteAll( contrato.getLineasContrato() );

			Set<LineaContrato> lineasContrato = new HashSet<LineaContrato>();
			
			//Por cada articulo, se crea una linea de contrato
			for(Articulo articulo : articulos) {
				
				LineaContrato lineaContrato = new LineaContrato();
				
				lineaContrato.setContrato(contrato);
				lineaContrato.setArticulo(articulo);
				lineaContrato.setBaseImponible(articulo.getBaseImponible());
				lineaContrato.setImporteTotal(articulo.getImporteTotal());
				BigDecimal importeImpuesto = new BigDecimal("" + (articulo.getImporteTotal().doubleValue() - articulo.getBaseImponible().doubleValue()) );
				lineaContrato.setImporteImpuesto( importeImpuesto ); 
				lineaContrato.setDescripcionLinea("");
				
				lineasContrato.add(lineaContrato);
			}
			
			lineaContratoRepository.saveAll(lineasContrato);
			
			contrato.getLineasContrato().clear();
			
			contrato.getLineasContrato().addAll(lineasContrato);
			
			//Guardamos el contrato en base de datos
			Contrato contratoSave = contratoRepository.saveAndFlush(contrato);
			
			//Actualizamos las lineas de factura
			this.regeneraFacturasService.actualizarFacturaContrato(contratoSave);
			
			return this.devolverDatosActualizadosContratoDto(contratoDto, contratoSave);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo actualizarContratoDesdeContratoDto() con ID={}", contratoDto.getId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
	}
	
	public AccionRespuesta eliminarContrato(Contrato contrato) {
		
		logger.debug("Entramos en el metodo eliminarContrato()" );
		
		if(contrato == null || contrato.getId() == null) {
			
			logger.error("ERROR en el metodo eliminarContrato()");
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
		
		try {
			
			//Elimnamos el contrato
			contratoRepository.deleteById(contrato.getId());
			
			return new AccionRespuesta(-2L, "OK", Boolean.TRUE);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo eliminarContrato() con ID={}", contrato.getId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
	}
	
	public AccionRespuesta eliminarContratoPorId(Long contratoId) {
		
		logger.error("Entramos en el metodo eliminarContratoPorId()" );
		
		if( contratoId == null) {
			
			logger.error("ERROR en el metodo eliminarContratoPorId()");
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
		
		try {
			
			//Elimnamos el contrato
			contratoRepository.deleteById(contratoId);
			
			return new AccionRespuesta(-2L, "OK", Boolean.TRUE);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo eliminarContratoPorId() con id={}", contratoId );
			
			e.printStackTrace();
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
	}
	
	public ContratoDto obtenerContratoDtoDesdeContrato(Long id ) {
		
		logger.debug("Entramos en el metodo obtenerContratoDtoDesdeContrato() con ID={}", id );
		
		Optional<Contrato> contratoOptional = contratoRepository.findById(id);
		
		Contrato contrato = contratoOptional.get();
		
		if(contrato == null) {
			return new ContratoDto();
		}
		
		ContratoDto contratoDto = new ContratoDto();
		
		try {
			
			contratoDto.setId(contrato.getId());
			contratoDto.setCodigo(contrato.getCodigo());
			contratoDto.setFechaCreacion(contrato.getFechaCreacion());
			contratoDto.setFechaInicio(contrato.getFechaInicio());
			contratoDto.setFechaFin(contrato.getFechaFin());
			contratoDto.setDescripcion(contrato.getDescripcion());
			contratoDto.setBaseImponibleTotal(contrato.getBaseImponibleTotal());
			contratoDto.setImpuesto(contrato.getImpuesto());
			contratoDto.setImporteTotal(contrato.getImporteTotal());
			
		} catch(Exception e) {
			
			logger.error("Error en el metodo obtenerContratoDtoDesdeContrato() con ID={}", id );
			
			e.printStackTrace();
		}
		
		return contratoDto;
	}
	
	public List<ContratoDto> getListadoContratos() {
		
		logger.debug("Entramos en el metodo getListadoContratos()" );
		
		try {
			
			List<Contrato> contratos = contratoRepository.findAll();
			
			return this.obtieneListadoContratoDtoDelRepository(contratos);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo getListadoContratos()" );
			
			e.printStackTrace();
		}
			
		return new ArrayList<ContratoDto>();
	}

	public AccionRespuesta getContrato(Long contratoId, Usuario user) {
		
		logger.debug("Entramos en el metodo getContrato()");
		
		if( contratoId == null) {
			
			return new AccionRespuesta(-1L, "Error, existe el contrato", Boolean.FALSE);
		}
		
		ContratoDto contratoDto = this.obtenerContratoDtoDesdeContrato(contratoId);
		
		AccionRespuesta AccionRespuesta = new AccionRespuesta();
		
		if( contratoDto != null ) {
			
			AccionRespuesta.setId( contratoDto.getId() );
			
			AccionRespuesta.setRespuesta("");
			
			AccionRespuesta.setResultado(Boolean.TRUE);
			
			HashMap<String, Object> mapa = new HashMap<String, Object>();
			
			mapa.put("contratoDto", contratoDto);
			
			AccionRespuesta.setData(new HashMap<String, Object>(mapa));
			
		}else {
			
			AccionRespuesta.setId( -1L );
			
			AccionRespuesta.setRespuesta("Error, no se ha podido recuperar el contrato");
			
			AccionRespuesta.setResultado(Boolean.FALSE);
		}
		
		return AccionRespuesta;
	}
	
	public AccionRespuesta getCrearEditarContrato(ContratoDto contratoDto, Usuario user) {
		
		logger.debug("Entramos en el metodo getCrearEditarContrato() con usuario={}", user.getId() );
		
		if( contratoDto.getId() != null && contratoDto.getId().longValue() > 0) {
			
			logger.debug("Se va a realizar una actualizacion del Contrato con usuario={}", user.getId() );
			
			return this.actualizarContratoDesdeContratoDto(contratoDto);
			
		} else {
			
			logger.debug("Se va a crear un Contrato con usuario={}", user.getId() );
			
			return this.crearContratoDesdeContratoDto(contratoDto);
		}
	}
	
	private AccionRespuesta devolverDatosContratoDto(ContratoDto contratoDto, Contrato contratoSave) {
		
		AccionRespuesta respuesta = new AccionRespuesta();
		
		//Guardado el cliente se devuelve en su dto
		if(contratoSave != null && contratoSave.getId() != null) {
			
			contratoDto.setId(contratoSave.getId());
			
			respuesta.setId(contratoSave.getId());
			
			respuesta.setCodigo("OK");
						
			respuesta.setResultado(Boolean.TRUE);
			
			HashMap<String, Object> data= new HashMap<String, Object> ();
			
			data.put("contratoDto", contratoDto);
			
			respuesta.setData(data);
			
		}else {
			
			respuesta.setCodigo("NOK");
						
			respuesta.setResultado(Boolean.FALSE);
			
			HashMap<String, Object> data= new HashMap<String, Object> ();
			
			data.put("contratoDto", contratoDto);
			
			respuesta.setData(data);
		}
		
		return respuesta;
	}
	
	private AccionRespuesta devolverDatosActualizadosContratoDto(ContratoDto contratoDto, Contrato contratoSave) {
		
		AccionRespuesta respuesta = new AccionRespuesta();
		
		if(contratoSave != null && contratoDto != null) {
			
			contratoDto.setId(contratoSave.getId());
			
			respuesta.setId(contratoSave.getId());
			
			respuesta.setCodigo("OK");
						
			respuesta.setResultado(Boolean.TRUE);
			
			HashMap<String, Object> data= new HashMap<String, Object> ();
			
			data.put("contratoDto", contratoDto);
			
			respuesta.setData(data);
			
		}else {
			
			respuesta.setId(-1L);
			
			respuesta.setCodigo("NOK");
			
			respuesta.setResultado(Boolean.FALSE);
			
			HashMap<String, Object> data= new HashMap<String, Object> ();
			
			data.put("contratoDto", contratoDto);
			
			respuesta.setData(data);			
		}
		
		return respuesta;		
	}
	
	private List<ContratoDto> obtieneListadoContratoDtoDelRepository(List<Contrato> contratos){
		
		List<ContratoDto> contratosDto = new ArrayList<ContratoDto>();
		
		if(CollectionUtils.isNotEmpty(contratos) ) {
			
			for(Contrato contrato : contratos) {
				
				ContratoDto contratoDto = new ContratoDto();
				
				contratoDto.setId(contrato.getId());
				contratoDto.setCodigo(contrato.getCodigo());
				contratoDto.setFechaCreacion(contrato.getFechaCreacion());
				contratoDto.setFechaInicio(contrato.getFechaInicio());
				contratoDto.setFechaFin(contrato.getFechaFin());
				contratoDto.setDescripcion(contrato.getDescripcion());
				contratoDto.setBaseImponibleTotal(contrato.getBaseImponibleTotal());
				contratoDto.setImpuesto(contrato.getImpuesto());
				contratoDto.setImporteTotal(contrato.getImporteTotal());
				
				contratosDto.add(contratoDto);		
			}
		}
		
		return contratosDto;
	}
	
}
