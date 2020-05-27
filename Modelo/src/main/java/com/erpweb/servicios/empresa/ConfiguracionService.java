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

@Service
public class ConfiguracionService {

	@Autowired
	private ConfiguracionRepository configuracionRepository;
	@Autowired
	private EmpresaRepository empresaRepository;
	
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	public void crearConfiguracionDesdeConfiguracionDto(ConfiguracionDto configuracionDto) {

		Configuracion configuracion = new Configuracion();
		
		if(configuracionDto.getEmpresaId() == null) {
			//return Boolean.FALSE;
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
			
			//return Boolean.FALSE;
		}
		
		//return Boolean.TRUE;
	}
	
	public void actualizarConfiguracionDesdeConfiguracionDto(ConfiguracionDto configuracionDto) {

		Configuracion configuracion = new Configuracion();
		
		if(configuracionDto.getEmpresaId() == null) {
			//return Boolean.FALSE;
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
			
			//return Boolean.FALSE;
		}
		
		//return Boolean.TRUE;
	}
	
	public void eliminarConfiguracion(Configuracion configuracion) {
		
		if(configuracion == null || configuracion.getId() == null) {
			//return Boolean.FALSE;
		}
		
		try {
			//Elimnamos la configuracion
			configuracionRepository.deleteById(configuracion.getId());
			
		}catch(Exception e) {
			
			System.out.println("Error: " + e.getLocalizedMessage());
			
			//return Boolean.FALSE;
		}
		
		//return Boolean.TRUE;
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
