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

import com.erpweb.dto.ContratoDto;
import com.erpweb.entidades.usuarios.Usuario;
import com.erpweb.entidades.ventas.Contrato;
import com.erpweb.repositorios.ventas.ContratoRepository;
import com.erpweb.utiles.AccionRespuesta;


@Service
public class ContratoService {

	@Autowired
	private ContratoRepository contratoRepository;
	
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	public AccionRespuesta crearContratoDesdeContratoDto(ContratoDto contratoDto) {
		
		logger.debug("Entramos en el metodo crearContratoDesdeContratoDto() con ID={}", contratoDto.getId() );
		
		Contrato contrato = new Contrato();
		
		contrato.setCodigo(contratoDto.getCodigo());
		contrato.setFechaCreacion(contratoDto.getFechaCreacion());
		contrato.setFechaInicio(contratoDto.getFechaInicio());
		contrato.setFechaFin(contratoDto.getFechaFin());
		contrato.setDescripcion(contratoDto.getDescripcion());
		contrato.setBaseImponibleTotal(contratoDto.getBaseImponibleTotal());
		contrato.setImporteTotal(contratoDto.getImporteTotal());
		
		try {
			
			//Guardamos el contrato en base de datos
			contratoRepository.save(contrato);
			
			return this.devolverDatosContratoDto(contratoDto, contrato);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo crearContratoDesdeContratoDto() con ID={}", contratoDto.getId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
	}
	
	public AccionRespuesta actualizarContratoDesdeContratoDto(ContratoDto contratoDto) {
		
		logger.debug("Entramos en el metodo actualizarContratoDesdeContratoDto() con ID={}", contratoDto.getId() );
		
		Contrato contrato = new Contrato();

		contrato.setId(contratoDto.getId());
		contrato.setCodigo(contratoDto.getCodigo());
		contrato.setFechaCreacion(contratoDto.getFechaCreacion());
		contrato.setFechaInicio(contratoDto.getFechaInicio());
		contrato.setFechaFin(contratoDto.getFechaFin());
		contrato.setDescripcion(contratoDto.getDescripcion());
		contrato.setBaseImponibleTotal(contratoDto.getBaseImponibleTotal());
		contrato.setImporteTotal(contratoDto.getImporteTotal());

		try {
			//Guardamos el contrato en base de datos
			contratoRepository.save(contrato);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo actualizarContratoDesdeContratoDto() con ID={}", contratoDto.getId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public AccionRespuesta eliminarContrato(Contrato contrato) {
		
		logger.debug("Entramos en el metodo eliminarContrato() con ID={}", contrato.getId() );
		
		if(contrato == null || contrato.getId() == null) {
			
			return new AccionRespuesta();
		}
		
		try {
			
			//Elimnamos el contrato
			contratoRepository.deleteById(contrato.getId());
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo eliminarContrato() con ID={}", contrato.getId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public AccionRespuesta eliminarContratoPorId(Long contratoId) {
		
		logger.error("Entramos en el metodo eliminarContratoPorId() con id={}", contratoId );
				
		try {
			
			//Elimnamos el contrato
			contratoRepository.deleteById(contratoId);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo eliminarContratoPorId() con id={}", contratoId );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		
		return new AccionRespuesta();
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
				contratoDto.setImporteTotal(contrato.getImporteTotal());
				
				contratosDto.add(contratoDto);		
			}
		}
		
		return contratosDto;
	}
	
}
