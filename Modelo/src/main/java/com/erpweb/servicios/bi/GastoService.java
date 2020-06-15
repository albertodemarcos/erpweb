package com.erpweb.servicios.bi;

import java.util.HashMap;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpweb.dto.GastoDto;
import com.erpweb.entidades.bi.Gasto;
import com.erpweb.entidades.usuarios.Usuario;
import com.erpweb.repositorios.bi.GastoRepository;
import com.erpweb.utiles.AccionRespuesta;

@Service
public class GastoService {
	
	@Autowired
	private GastoRepository gastoRepository;

	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	public AccionRespuesta crearGastoDesdeGastoDto(GastoDto gastoDto) {
		
		logger.debug("Entramos en el metodo crearGastoDesdeGastoDto()" );
		
		Gasto gasto = new Gasto();
		
		gasto.setCodigo(gastoDto.getCodigo());
		gasto.setProcedencia(gastoDto.getProcedencia());
		gasto.setBaseImponible(gastoDto.getBaseImponible());
		gasto.setCuotaTributaria(gastoDto.getCuotaTributaria());
		gasto.setImporteTotal(gastoDto.getImporteTotal());
		gasto.setDescripcion(gastoDto.getDescripcion());
		gasto.setObservaciones(gastoDto.getObservaciones());
		
		try {
			
			//Guardamos el gasto en base de datos
			gastoRepository.save(gasto);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo crearGastoDesdeGastoDto() " );
			
			e.printStackTrace();			
			
			return new AccionRespuesta();
		}
				
		return new AccionRespuesta();
	}
	
	public AccionRespuesta actualizarGastoDesdeGastoDto(GastoDto gastoDto) {
		
		logger.debug("Entramos en el metodo actualizarGastoDesdeGastoDto() " );
		
		Gasto gasto = new Gasto();

		gasto.setId(gastoDto.getId());
		gasto.setCodigo(gastoDto.getCodigo());
		gasto.setProcedencia(gastoDto.getProcedencia());
		gasto.setBaseImponible(gastoDto.getBaseImponible());
		gasto.setCuotaTributaria(gastoDto.getCuotaTributaria());
		gasto.setImporteTotal(gastoDto.getImporteTotal());
		gasto.setDescripcion(gastoDto.getDescripcion());
		gasto.setObservaciones(gastoDto.getObservaciones());
		
		try {
			//Actualizamos el gasto en base de datos
			gastoRepository.save(gasto);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo actualizarGastoDesdeGastoDto() con la empresa{} " );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
				
		return new AccionRespuesta();
	}
	
	public AccionRespuesta eliminarGasto(Gasto gasto) {
		
		logger.error("Entramos en el metodo eliminarGasto() con ID={} ", gasto.getId() );
		
		if(gasto == null || gasto.getId() == null) {
			
			return new AccionRespuesta();
		}
		
		try {
			//Elimnamos el gasto
			gastoRepository.deleteById(gasto.getId());
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo eliminarGasto() con ID={} ", gasto.getId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		
		return new AccionRespuesta();
	}
	
	public AccionRespuesta eliminarGastoPorId(Long gastoId) {
		
		logger.error("Entramos en el metodo eliminarGastoPorId() con id={}", gastoId );
				
		try {
			//Elimnamos el gasto
			gastoRepository.deleteById(gastoId);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo eliminarGastoPorId() con id={}", gastoId );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		
		return new AccionRespuesta();
	}
	
	public GastoDto obtenerGastoDtoDesdeGasto(Long id) {
		
		logger.debug("Entramos en el metodo obtenerGastoDtoDesdeGasto() con ID={} ", id );
		
		if( (id ==  null || id.doubleValue() < 1 ) ) {
			
			return null;
		}
		
		GastoDto gastoDto = new GastoDto();
		
		try {
			
			Optional<Gasto> gastoOptional = gastoRepository.findById(id);
			
			Gasto gasto = gastoOptional.get();
			
			gastoDto.setCodigo(gasto.getCodigo());
			gastoDto.setProcedencia(gasto.getProcedencia());
			gastoDto.setBaseImponible(gasto.getBaseImponible());
			gastoDto.setCuotaTributaria(gasto.getCuotaTributaria());
			gastoDto.setImporteTotal(gasto.getImporteTotal());
			gastoDto.setDescripcion(gasto.getDescripcion());
			gastoDto.setObservaciones(gasto.getObservaciones());
			
		} catch(Exception e) {
			
			logger.error("Error en el metodo obtenerGastoDtoDesdeGasto() con ID={} ", id  );
			
			e.printStackTrace();
		}
		
		return gastoDto;
	}
	
	public AccionRespuesta getGasto(Long gastoId, Usuario user) {
		
		logger.debug("Entramos en el metodo getCrearGasto()");
		
		if( gastoId == null) {
			
			return new AccionRespuesta(-1L, "Error, existe el gasto", Boolean.FALSE);
		}
		
		GastoDto gastoDto = this.obtenerGastoDtoDesdeGasto(gastoId );
		
		AccionRespuesta AccionRespuesta = new AccionRespuesta();
		
		if( gastoDto != null ) {
			
			AccionRespuesta.setId( gastoDto.getId() );
			
			AccionRespuesta.setRespuesta("");
			
			AccionRespuesta.setResultado(Boolean.TRUE);
			
			HashMap<String, Object> mapa = new HashMap<String, Object>();
			
			mapa.put("gastoDto", gastoDto);
			
			AccionRespuesta.setData(new HashMap<String, Object>(mapa));
			
		}else {
			
			AccionRespuesta.setId( -1L );
			
			AccionRespuesta.setRespuesta("Error, no se ha podido recuperar el gasto");
			
			AccionRespuesta.setResultado(Boolean.FALSE);
		}
		
		return AccionRespuesta;
	}
	
	public AccionRespuesta getCrearEditarGasto(GastoDto gastoDto, Usuario user) {
		
		logger.debug("Entramos en el metodo getCrearEditarGasto() con usuario={}", user.getId() );
		
		if( gastoDto.getId() != null && gastoDto.getId().longValue() > 0) {
			
			logger.debug("Se va a realizar una actualizacion del gasto con usuario={}", user.getId() );
			
			return this.actualizarGastoDesdeGastoDto(gastoDto);
			
		} else {
			
			logger.debug("Se va a crear un gasto con usuario={}", user.getId() );
			
			return this.crearGastoDesdeGastoDto(gastoDto);
		}
	}
	
	
}
