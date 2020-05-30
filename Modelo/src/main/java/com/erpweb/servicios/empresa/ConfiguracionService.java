package com.erpweb.servicios.empresa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpweb.dto.ConfiguracionDto;
import com.erpweb.entidades.empresa.Configuracion;
import com.erpweb.entidades.empresa.Empresa;
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

		Configuracion configuracion = new Configuracion();
		
		if( configuracionDto.getEmpresaId() == null ) {
			
			return new AccionRespuesta();
		}
		
		Empresa empresa = empresaRepository.findById(configuracionDto.getEmpresaId()).orElse(new Empresa());
		
		configuracion.setCodigo(configuracionDto.getCodigo());
		configuracion.setEmpresa(empresa);
		configuracion.setIdiomaApp(configuracionDto.getIdiomaApp());
		
		try {
			//Guardamos el gasto en base de datos
			configuracionRepository.save(configuracion);
			
		}catch(Exception e) {
			
			System.out.println("Error: " + e.getLocalizedMessage() );
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public AccionRespuesta actualizarConfiguracionDesdeConfiguracionDto(ConfiguracionDto configuracionDto) {

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
			//Guardamos el gasto en base de datos
			configuracionRepository.save(configuracion);
			
		}catch(Exception e) {
			
			System.out.println("Error: " + e.getLocalizedMessage() );
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public AccionRespuesta eliminarConfiguracion(Configuracion configuracion) {
		
		if(configuracion == null || configuracion.getId() == null) {
			
			return new AccionRespuesta();
		}
		
		try {
			//Elimnamos la configuracion
			configuracionRepository.deleteById(configuracion.getId());
			
		}catch(Exception e) {
			
			System.out.println("Error: " + e.getLocalizedMessage());
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public ConfiguracionDto obtenerConfiguracionDtoDesdeConfiguracion(Long id, Long empresaId) {

		Configuracion configuracion = configuracionRepository.findByIdAndEmpresaId(id, empresaId);
		
		if(configuracion == null) {
			return new ConfiguracionDto();
		}
		
		ConfiguracionDto configuracionDto = new ConfiguracionDto();
		
		configuracionDto.setId(configuracion.getId());
		configuracionDto.setCodigo(configuracion.getCodigo());
		configuracionDto.setEmpresaId(configuracion.getEmpresa().getId());
		configuracionDto.setIdiomaApp(configuracion.getIdiomaApp());
		
		return configuracionDto;
	}

}
