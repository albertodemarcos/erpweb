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
		
		Informe informe = new Informe();
		
		if(informeDto.getEmpresaId() == null) {
			//return Boolean.FALSE;
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
			
			System.out.println("Error: " + e.getLocalizedMessage() );
			
			//return Boolean.FALSE;
			return new AccionRespuesta();
		}
		
		//return Boolean.TRUE;
		return new AccionRespuesta();
	}
	
	public AccionRespuesta actualizarInformeDesdeInformeDto( InformeDto informeDto ) {
		
		Informe informe = new Informe();
		
		if(informeDto.getEmpresaId() == null) {
			//return Boolean.FALSE;
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
			
			System.out.println("Error: " + e.getLocalizedMessage() );
			
			//return Boolean.FALSE;
			return new AccionRespuesta();
		}
		
		//return Boolean.TRUE;
		return new AccionRespuesta();
	}
	
	public AccionRespuesta eliminarInforme(Informe informe ) {
		
		if(informe == null || informe.getId() == null) {
			//return Boolean.FALSE;
			return new AccionRespuesta();
		}
		
		try {
			//Eliminamos el informe
			informeRepository.deleteById(informe.getId());
			
		}catch(Exception e) {
			
			System.out.println("Error: " + e.getLocalizedMessage() );
			
			//return Boolean.FALSE;
			return new AccionRespuesta();
		}
		
		//return Boolean.TRUE;
		return new AccionRespuesta();
	}
	
	public InformeDto obtenerInformeDtoDesdeInforme(Long id, Long empresaId) {
		
		Informe informe = informeRepository.findByIdAndEmpresaId(id, empresaId);
		
		if(informe == null) {
			return new InformeDto();
		}
		
		InformeDto informeDto = new InformeDto();
		
		informeDto.setCodigo(informe.getCodigo());
		informeDto.setEmpresaId(empresaId);
		informeDto.setGenerado(informe.getGenerado());
		
		return informeDto;
	}

}
