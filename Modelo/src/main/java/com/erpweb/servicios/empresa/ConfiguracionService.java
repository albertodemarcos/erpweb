package com.erpweb.servicios.empresa;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpweb.dto.ConfiguracionDto;
import com.erpweb.entidades.empresa.Configuracion;
import com.erpweb.entidades.empresa.Empresa;
import com.erpweb.entidades.usuarios.Usuario;
import com.erpweb.repositorios.empresa.ConfiguracionRepository;
import com.erpweb.repositorios.empresa.EmpresaRepository;
import com.erpweb.utiles.AccionRespuesta;

@Service
public class ConfiguracionService {

	@Autowired
	private ConfiguracionRepository configuracionRepository;
	@Autowired
	private EmpresaRepository empresaRepository;
	
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	public AccionRespuesta crearConfiguracionDesdeConfiguracionDto(ConfiguracionDto configuracionDto) {
		
		logger.debug("Entramos en el metodo crearConfiguracionDesdeConfiguracionDto() con la empresa={}", configuracionDto.getEmpresaId() );

		Configuracion configuracion = new Configuracion();
		
		if( configuracionDto.getEmpresaId() == null ) {
			
			return new AccionRespuesta();
		}
		
		Empresa empresa = empresaRepository.findById(configuracionDto.getEmpresaId()).orElse(new Empresa());
		
		configuracion.setCodigo(configuracionDto.getCodigo());
		configuracion.setEmpresa(empresa);
		configuracion.setIdiomaApp(configuracionDto.getIdiomaApp());
		
		try {
			//Guardamos el Configuracion en base de datos
			configuracionRepository.save(configuracion);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo crearConfiguracionDesdeConfiguracionDto() con la empresa{} ", configuracionDto.getEmpresaId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public AccionRespuesta actualizarConfiguracionDesdeConfiguracionDto(ConfiguracionDto configuracionDto) {
		
		logger.debug("Entramos en el metodo actualizarConfiguracionDesdeConfiguracionDto() con la empresa={}", configuracionDto.getEmpresaId() );

		Configuracion configuracion = new Configuracion();
		
		if(configuracionDto.getEmpresaId() == null) {
			
			return new AccionRespuesta();
		}
		
		Empresa empresa = empresaRepository.findById(configuracionDto.getEmpresaId()).orElse(new Empresa());
		
		configuracion.setId(configuracionDto.getId());
		configuracion.setCodigo(configuracionDto.getCodigo());
		configuracion.setEmpresa(empresa);
		configuracion.setIdiomaApp(configuracionDto.getIdiomaApp());
		
		try {
			//Guardamos el Configuracion en base de datos
			configuracionRepository.save(configuracion);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo actualizarConfiguracionDesdeConfiguracionDto() con la empresa{} ", configuracionDto.getEmpresaId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public AccionRespuesta eliminarConfiguracion(Configuracion configuracion) {
		
		logger.debug("Entramos en el metodo eliminarConfiguracion() con la empresa={}", configuracion.getEmpresa().getId() );
		
		if(configuracion == null || configuracion.getId() == null) {
			
			return new AccionRespuesta();
		}
		
		try {
			//Elimnamos la configuracion
			configuracionRepository.deleteById(configuracion.getId());
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo eliminarConfiguracion() con la empresa{} ", configuracion.getEmpresa().getId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public AccionRespuesta eliminarConfiguracionPorId(Long configuracionId) {
		
		logger.error("Entramos en el metodo eliminarConfiguracionPorId() con id={}", configuracionId );
				
		try {
			//Elimnamos el configuracion
			configuracionRepository.deleteById(configuracionId);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo eliminarConfiguracionPorId() con id={}", configuracionId );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		
		return new AccionRespuesta();
	}
	
	public ConfiguracionDto obtenerConfiguracionDtoDesdeConfiguracion(Long id, Long empresaId) {
		
		logger.debug("Entramos en el metodo obtenerConfiguracionDtoDesdeConfiguracion() con la empresa={}", empresaId );

		Configuracion configuracion = configuracionRepository.findByIdAndEmpresaId(id, empresaId);
		
		if(configuracion == null) {
			return new ConfiguracionDto();
		}
		
		ConfiguracionDto configuracionDto = new ConfiguracionDto();
		
		try {
			
			configuracionDto.setId(configuracion.getId());
			configuracionDto.setCodigo(configuracion.getCodigo());
			configuracionDto.setEmpresaId(configuracion.getEmpresa().getId());
			configuracionDto.setIdiomaApp(configuracion.getIdiomaApp());
			
		} catch(Exception e) {
			
			logger.error("Error en el metodo obtenerConfiguracionDtoDesdeConfiguracion() con la empresa{} ", empresaId );
			
			e.printStackTrace();
		}
		
		return configuracionDto;
	}
	
	public AccionRespuesta getConfiguracion(Long ConfiguracionId, Usuario user) {
		
		logger.debug("Entramos en el metodo getConfiguracion()");
		
		if( ConfiguracionId == null) {
			
			return new AccionRespuesta(-1L, "Error, existe el configuración", Boolean.FALSE);
		}
		
		ConfiguracionDto ConfiguracionDto = this.obtenerConfiguracionDtoDesdeConfiguracion(ConfiguracionId, user.getEmpresa().getId());
		
		AccionRespuesta AccionRespuesta = new AccionRespuesta();
		
		if( ConfiguracionDto != null ) {
			
			AccionRespuesta.setId( ConfiguracionDto.getId() );
			
			AccionRespuesta.setRespuesta("");
			
			AccionRespuesta.setResultado(Boolean.TRUE);
			
			HashMap<String, Object> mapa = new HashMap<String, Object>();
			
			mapa.put("ConfiguracionDto", ConfiguracionDto);
			
			AccionRespuesta.setData(new HashMap<String, Object>(mapa));
			
		}else {
			
			AccionRespuesta.setId( -1L );
			
			AccionRespuesta.setRespuesta("Error, no se ha podido recuperar la configuración");
			
			AccionRespuesta.setResultado(Boolean.FALSE);
		}
		
		return AccionRespuesta;
	}

}
