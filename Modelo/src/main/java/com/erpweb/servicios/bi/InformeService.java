package com.erpweb.servicios.bi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpweb.dto.InformeDto;
import com.erpweb.entidades.bi.Informe;
import com.erpweb.entidades.empresa.Empresa;
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

}
