package com.erpweb.servicios.empresa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpweb.dto.EmpresaDto;
import com.erpweb.entidades.empresa.Empresa;
import com.erpweb.repositorios.empresa.EmpresaRepository;
import com.erpweb.servicios.empresa.interfaces.EmpresaServiceInterfaz;



@Service
public class EmpresaService implements EmpresaServiceInterfaz {

	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Override
	public Boolean creaEmpresaDesdeEmpresaDto(EmpresaDto empresaDto) {
		
		Empresa empresa = new Empresa();
		
		if(empresaDto == null) {
			return Boolean.FALSE;
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
			
			return Boolean.FALSE;
		}
		
		return Boolean.TRUE;
	}

	@Override
	public EmpresaDto obtieneEmpresaDto(Long id) {
		
		Empresa empresa = empresaRepository.findOne(id);
		
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

	@Override
	public Boolean actualizaEmpresa(EmpresaDto empresaDto) {

		Empresa empresa = new Empresa();
		
		if(empresaDto.getId() == null) {
			return Boolean.FALSE;
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
			
			return Boolean.FALSE;
		}
		
		return Boolean.TRUE;
	}

	@Override
	public Boolean eliminaEmpresa(Empresa empresa) {
		
		if(empresa == null || empresa.getId() == null) {
			return Boolean.FALSE;
		}
		
		try {
			//Elimnamos la empresa
			empresaRepository.deleteById(empresa.getId());
			
		}catch(Exception e) {
			
			System.out.println("Error: " + e.getLocalizedMessage());
			
			return Boolean.FALSE;
		}
		
		return Boolean.TRUE;
	}

	@Override
	public Empresa obtieneEmpresa(Long id) {
		
		Empresa empresa;
		
		try {
			//Recuperamos el gasto
			empresa = empresaRepository.findOne(id);
			
		}catch(Exception e) {
			
			System.out.println("Error al recuperar la empresa con ID: "+ id + " con error: " + e.getLocalizedMessage());
			
			return new Empresa();
		}
		
		return empresa;
	}
	

}
