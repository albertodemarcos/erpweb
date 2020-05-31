package com.erpweb.servicios.bi;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpweb.dto.InformeDto;
import com.erpweb.dto.InformeDto;
import com.erpweb.entidades.bi.Informe;
import com.erpweb.entidades.empresa.Empresa;
import com.erpweb.entidades.usuarios.Usuario;
import com.erpweb.repositorios.bi.InformeRepository;
import com.erpweb.repositorios.empresa.EmpresaRepository;
import com.erpweb.utiles.AccionRespuesta;

@Service
public class InformeService {

	@Autowired
	private InformeRepository informeRepository;
	@Autowired
	private EmpresaRepository empresaRepository;
	
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public AccionRespuesta crearInformeDesdeInformeDto( InformeDto informeDto ) {
		
		logger.debug("Entramos en el metodo crearInformeDesdeInformeDto() con la empresa={}", informeDto.getEmpresaId() );
		
		Informe informe = new Informe();
		
		if(informeDto.getEmpresaId() == null) {
			
			return new AccionRespuesta();
		}
		
		Empresa empresa = empresaRepository.findById(informeDto.getEmpresaId()).orElse(new Empresa());
		
		informe.setCodigo(informeDto.getCodigo());
		informe.setEmpresa(empresa);
		informe.setGenerado(informeDto.getGenerado());
		
		try {
			//Guardamos el informe en base de datos
			informeRepository.save(informe);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo crearInformeDesdeInformeDto() con la empresa{} ", informeDto.getEmpresaId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public AccionRespuesta actualizarInformeDesdeInformeDto( InformeDto informeDto ) {
		
		logger.debug("Entramos en el metodo actualizarInformeDesdeInformeDto() con la empresa={}", informeDto.getEmpresaId() );
		
		Informe informe = new Informe();
		
		if(informeDto.getEmpresaId() == null) {
			
			return new AccionRespuesta();
		}
		
		Empresa empresa = empresaRepository.findById(informeDto.getEmpresaId()).orElse(new Empresa());
		
		informe.setId(informeDto.getId());
		informe.setCodigo(informeDto.getCodigo());
		informe.setEmpresa(empresa);
		informe.setGenerado(informeDto.getGenerado());
		
		try {
			
			//Actualizamos el informe en base de datos
			informeRepository.save(informe);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo actualizarInformeDesdeInformeDto() con la empresa{} ", informeDto.getEmpresaId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}		
		
		return new AccionRespuesta();
	}
	
	public AccionRespuesta eliminarInforme(Informe informe ) {
		
		logger.debug("Entramos en el metodo eliminarInforme() con la empresa={}", informe.getEmpresa().getId() );
		
		if(informe == null || informe.getId() == null) {
			
			return new AccionRespuesta();
		}
		
		try {
			
			//Eliminamos el informe
			informeRepository.deleteById(informe.getId());
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo eliminarInforme() con la empresa{} ", informe.getEmpresa().getId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}		
		
		return new AccionRespuesta();
	}
	
	public AccionRespuesta eliminarInformePorId(Long informeId) {
			
			logger.error("Entramos en el metodo eliminarInformePorId() con id={}", informeId );
					
			try {
				//Elimnamos el informe
				informeRepository.deleteById(informeId);
				
			}catch(Exception e) {
				
				logger.error("Error en el metodo eliminarinformePorId() con id={}", informeId );
				
				e.printStackTrace();
				
				return new AccionRespuesta();
			}
			
			
			return new AccionRespuesta();
		}
	
	public InformeDto obtenerInformeDtoDesdeInforme(Long id, Long empresaId) {
		
		logger.debug("Entramos en el metodo obtenerInformeDtoDesdeInforme() con la empresa={}", empresaId );
		
		Informe informe = informeRepository.findByIdAndEmpresaId(id, empresaId);
		
		if(informe == null) {
			
			return new InformeDto();
		}
		
		InformeDto informeDto = new InformeDto();
		
		try {
			
			informeDto.setCodigo(informe.getCodigo());
			informeDto.setEmpresaId(empresaId);
			informeDto.setGenerado(informe.getGenerado());
			
		} catch(Exception e) {
			
			logger.error("Error en el metodo obtenerInformeDtoDesdeInforme() con la empresa{} ", empresaId );

			e.printStackTrace();
		}
		
		return informeDto;
	}
	
	public AccionRespuesta getInforme(Long informeId, Usuario user) {
		
		logger.debug("Entramos en el metodo getIforme()");
		
		if( informeId == null) {
			
			return new AccionRespuesta(-1L, "Error, existe el informe", Boolean.FALSE);
		}
		
		InformeDto informeDto = this.obtenerInformeDtoDesdeInforme(informeId, user.getEmpresa().getId());
		
		AccionRespuesta AccionRespuesta = new AccionRespuesta();
		
		if( informeDto != null ) {
			
			AccionRespuesta.setId( informeDto.getId() );
			
			AccionRespuesta.setRespuesta("");
			
			AccionRespuesta.setResultado(Boolean.TRUE);
			
			HashMap<String, Object> mapa = new HashMap<String, Object>();
			
			mapa.put("informeDto", informeDto);
			
			AccionRespuesta.setData(new HashMap<String, Object>(mapa));
			
		}else {
			
			AccionRespuesta.setId( -1L );
			
			AccionRespuesta.setRespuesta("Error, no se ha podido recuperar el informe");
			
			AccionRespuesta.setResultado(Boolean.FALSE);
		}
		
		return AccionRespuesta;
	}
	
	public AccionRespuesta getCrearEditarInforme(InformeDto informeDto, Usuario user) {
		
		logger.debug("Entramos en el metodo getCrearEditarInforme() con usuario={}", user.getId() );
		
		if( informeDto.getId() != null && informeDto.getId().longValue() > 0) {
			
			logger.debug("Se va a realizar una actualizacion del Informe con usuario={}", user.getId() );
			
			return this.actualizarInformeDesdeInformeDto(informeDto);
			
		} else {
			
			logger.debug("Se va a crear un Informe con usuario={}", user.getId() );
			
			return this.crearInformeDesdeInformeDto(informeDto);
		}
	}

}
