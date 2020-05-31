package com.erpweb.servicios.bi;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpweb.dto.GastoDto;
import com.erpweb.entidades.bi.Gasto;
import com.erpweb.entidades.empresa.Empresa;
import com.erpweb.entidades.usuarios.Usuario;
import com.erpweb.repositorios.bi.GastoRepository;
import com.erpweb.repositorios.empresa.EmpresaRepository;
import com.erpweb.utiles.AccionRespuesta;

@Service
public class GastoService {
	
	@Autowired
	private GastoRepository gastoRepository;
	@Autowired
	private EmpresaRepository empresaRepository;

	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	public AccionRespuesta crearGastoDesdeGastoDto(GastoDto gastoDto) {
		
		logger.debug("Entramos en el metodo crearGastoDesdeGastoDto() con la empresa={}", gastoDto.getEmpresaId() );
		
		Gasto gasto = new Gasto();

		if(gastoDto.getEmpresaId() == null) {
			
			return new AccionRespuesta();
		}
		
		Empresa empresa = empresaRepository.findById(gastoDto.getEmpresaId()).orElse(new Empresa());
		
		gasto.setCodigo(gastoDto.getCodigo());
		gasto.setEmpresa(empresa);
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
			
			logger.error("Error en el metodo crearGastoDesdeGastoDto() con la empresa{} ", gastoDto.getEmpresaId() );
			
			e.printStackTrace();			
			
			return new AccionRespuesta();
		}
				
		return new AccionRespuesta();
	}
	
	public AccionRespuesta actualizarGastoDesdeGastoDto(GastoDto gastoDto) {
		
		logger.debug("Entramos en el metodo actualizarGastoDesdeGastoDto() con la empresa={}", gastoDto.getEmpresaId() );
		
		Gasto gasto = new Gasto();

		if(gastoDto.getEmpresaId() == null) {
			
			return new AccionRespuesta();
		}
		
		Empresa empresa = empresaRepository.findById(gastoDto.getEmpresaId()).orElse(new Empresa());
		
		gasto.setId(gastoDto.getId());
		gasto.setCodigo(gastoDto.getCodigo());
		gasto.setEmpresa(empresa);
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
			
			logger.error("Error en el metodo actualizarGastoDesdeGastoDto() con la empresa{} ", gastoDto.getEmpresaId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
				
		return new AccionRespuesta();
	}
	
	public AccionRespuesta eliminarGasto(Gasto gasto) {
		
		logger.error("Entramos en el metodo eliminarGasto() con la empresa{} ", gasto.getEmpresa().getId() );
		
		if(gasto == null || gasto.getId() == null) {
			
			return new AccionRespuesta();
		}
		
		try {
			//Elimnamos el gasto
			gastoRepository.deleteById(gasto.getId());
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo eliminarGasto() con la empresa{} ", gasto.getEmpresa().getId() );
			
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
	
	public GastoDto obtenerGastoDtoDesdeGasto(Long id, Long empresaId) {
		
		logger.debug("Entramos en el metodo obtenerGastoDtoDesdeGasto() con la empresa={}", empresaId );
		
		if( (id ==  null || id.doubleValue() < 1 ) || (empresaId == null || empresaId.intValue() < 1 ) ) {
			
			return new GastoDto();
		}
		
		GastoDto gastoDto = new GastoDto();
		
		try {
			
			Gasto gasto = gastoRepository.findByIdAndEmpresaId(id, empresaId);
			
			gastoDto.setCodigo(gasto.getCodigo());
			gastoDto.setEmpresaId(gasto.getEmpresa().getId());
			gastoDto.setProcedencia(gasto.getProcedencia());
			gastoDto.setBaseImponible(gasto.getBaseImponible());
			gastoDto.setCuotaTributaria(gasto.getCuotaTributaria());
			gastoDto.setImporteTotal(gasto.getImporteTotal());
			gastoDto.setDescripcion(gasto.getDescripcion());
			gastoDto.setObservaciones(gasto.getObservaciones());
			
		} catch(Exception e) {
			
			logger.error("Error en el metodo obtenerGastoDtoDesdeGasto() con la empresa{} ", empresaId );
			
			e.printStackTrace();
		}
		
		return gastoDto;
	}
	
	public AccionRespuesta getGasto(Long gastoId, Usuario user) {
		
		logger.debug("Entramos en el metodo getCrearGasto()");
		
		if( gastoId == null) {
			
			return new AccionRespuesta(-1L, "Error, existe el gasto", Boolean.FALSE);
		}
		
		GastoDto gastoDto = this.obtenerGastoDtoDesdeGasto(gastoId, user.getEmpresa().getId());
		
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
	
	public AccionRespuesta getCrearEditarGasto(Long gastoId, Usuario user) {
		
		return null;
		
	}
	
	
}
