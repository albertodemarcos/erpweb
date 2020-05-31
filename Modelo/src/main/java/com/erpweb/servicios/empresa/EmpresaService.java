package com.erpweb.servicios.empresa;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpweb.dto.EmpresaDto;
import com.erpweb.entidades.empresa.Empresa;
import com.erpweb.entidades.usuarios.Usuario;
import com.erpweb.repositorios.empresa.EmpresaRepository;
import com.erpweb.utiles.AccionRespuesta;



@Service
public class EmpresaService {

	@Autowired
	private EmpresaRepository empresaRepository;
	
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	public AccionRespuesta crearEmpresaDesdeEmpresaDto(EmpresaDto empresaDto) {
		
		logger.debug("Entramos en el metodo crearEmpresaDesdeEmpresaDto()" );

		Empresa empresa = new Empresa();
		
		if(empresaDto == null) {
			
			return new AccionRespuesta();
		}
		
		empresa.setCodigo(empresaDto.getCodigo());
		empresa.setNombre(empresaDto.getNombre());
		empresa.setCif(empresaDto.getCif());
		empresa.setTipoSociedadJuridica(empresaDto.getTipoSociedadJuridica());
		
		try {
			//Guardamos la empresa en base de datos
			empresaRepository.save(empresa);
			
		}catch(Exception e) {
			
			logger.error("Error al guardar la empresa" + empresaDto.getNombre() + " en base de datos: " + e.getLocalizedMessage() );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public AccionRespuesta actualizarEmpresaDesdeEmpresaDto(EmpresaDto empresaDto) {
		
		logger.debug("Entramos en el metodo actualizarEmpresaDesdeEmpresaDto() con la empresa={}", empresaDto.getId() );
		
		Empresa empresa = new Empresa();
		
		if(empresaDto.getId() == null) {
			
			return new AccionRespuesta();
		}
		
		empresa.setId(empresaDto.getId());
		empresa.setCodigo(empresaDto.getCodigo());
		empresa.setNombre(empresaDto.getNombre());
		empresa.setCif(empresaDto.getCif());
		empresa.setTipoSociedadJuridica(empresaDto.getTipoSociedadJuridica());
		
		try {
			//Guardamos la empresa en base de datos
			empresaRepository.save(empresa);
			
		}catch(Exception e) {
			
			logger.error("Error al guardar la empresa" + empresaDto.getNombre() + " en base de datos: " + e.getLocalizedMessage() );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public AccionRespuesta eliminarEmpresa(Empresa empresa) {
		
		logger.debug("Entramos en el metodo eliminarEmpresa() con la empresa={}", empresa.getId() );
		
		if(empresa == null || empresa.getId() == null) {
			
			return new AccionRespuesta();
		}
		
		try {
			//Elimnamos la empresa
			empresaRepository.deleteById(empresa.getId());
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo eliminarEmpresa() con la empresa{} ", empresa.getId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public AccionRespuesta eliminarEmpresaPorId(Long empresaId) {
		
		logger.error("Entramos en el metodo eliminarEmpresaPorId() con id={}", empresaId );
				
		try {
			
			//Elimnamos la empresa
			empresaRepository.deleteById(empresaId);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo eliminarEmpresaPorId() con id={}", empresaId );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public EmpresaDto obtenerEmpresaDtoDesdeEmpresa(Long id) {
		
		logger.debug("Entramos en el metodo obtenerEmpresaDtoDesdeEmpresa() con la empresa={}", id );
		
		Empresa empresa = empresaRepository.findById(id).orElse(new Empresa());
		
		if(empresa == null) {
			return new EmpresaDto();
		}
		
		EmpresaDto empresaDto = new EmpresaDto();
		
		try {
			
			empresaDto.setId(empresa.getId());
			empresaDto.setCodigo(empresa.getCodigo());
			empresaDto.setNombre(empresa.getNombre());
			empresaDto.setCif(empresa.getCif());
			empresaDto.setTipoSociedadJuridica(empresa.getTipoSociedadJuridica());
			
		} catch(Exception e) {
			
			logger.error("Error en el metodo obtenerEmpresaDtoDesdeEmpresa() con la empresa{} ", id );
			
			e.printStackTrace();
		}
		
		return empresaDto;
	}
	
	public AccionRespuesta getEmpresa(Long empresaId, Usuario user) {
		
		logger.debug("Entramos en el metodo getEmpresa()");
		
		if( empresaId == null) {
			
			return new AccionRespuesta(-1L, "Error, existe la empresa", Boolean.FALSE);
		}
		
		EmpresaDto empresaDto = this.obtenerEmpresaDtoDesdeEmpresa(empresaId);
		
		AccionRespuesta AccionRespuesta = new AccionRespuesta();
		
		if( empresaDto != null ) {
			
			AccionRespuesta.setId( empresaDto.getId() );
			
			AccionRespuesta.setRespuesta("");
			
			AccionRespuesta.setResultado(Boolean.TRUE);
			
			HashMap<String, Object> mapa = new HashMap<String, Object>();
			
			mapa.put("empresaDto", empresaDto);
			
			AccionRespuesta.setData(new HashMap<String, Object>(mapa));
			
		}else {
			
			AccionRespuesta.setId( -1L );
			
			AccionRespuesta.setRespuesta("Error, no se ha podido recuperar la empresa");
			
			AccionRespuesta.setResultado(Boolean.FALSE);
		}
		
		return AccionRespuesta;
	}
	
}
