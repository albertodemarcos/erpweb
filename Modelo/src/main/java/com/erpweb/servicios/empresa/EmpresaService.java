package com.erpweb.servicios.empresa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpweb.dto.EmpresaDto;
import com.erpweb.entidades.empresa.Empresa;
import com.erpweb.repositorios.empresa.EmpresaRepository;
import com.erpweb.utiles.AccionRespuesta;



@Service
public class EmpresaService {

	@Autowired
	private EmpresaRepository empresaRepository;
	
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	public AccionRespuesta crearEmpresaDesdeEmpresaDto(EmpresaDto empresaDto) {

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
			
			System.out.println("Error al guardar la empresa" + empresaDto.getNombre() + " en base de datos: " + e.getLocalizedMessage() );
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public AccionRespuesta actualizarEmpresaDesdeEmpresaDto(EmpresaDto empresaDto) {
		
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
			
			System.out.println("Error al guardar la empresa" + empresaDto.getNombre() + " en base de datos: " + e.getLocalizedMessage() );
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public AccionRespuesta eliminarEmpresa(Empresa empresa) {
		
		if(empresa == null || empresa.getId() == null) {
			
			return new AccionRespuesta();
		}
		
		try {
			//Elimnamos la empresa
			empresaRepository.deleteById(empresa.getId());
			
		}catch(Exception e) {
			
			System.out.println("Error: " + e.getLocalizedMessage());
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public EmpresaDto obtenerEmpresaDtoDesdeEmpresa(Long id) {
		
		Empresa empresa = empresaRepository.findById(id).orElse(new Empresa());
		
		if(empresa == null) {
			return new EmpresaDto();
		}
		
		EmpresaDto empresaDto = new EmpresaDto();
		
		empresaDto.setId(empresa.getId());
		empresaDto.setCodigo(empresa.getCodigo());
		empresaDto.setNombre(empresa.getNombre());
		empresaDto.setCif(empresa.getCif());
		empresaDto.setTipoSociedadJuridica(empresa.getTipoSociedadJuridica());
		
		return empresaDto;
	}
	
}
