package com.erpweb.servicios.bi;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpweb.dto.IngresoDto;
import com.erpweb.entidades.bi.Gasto;
import com.erpweb.entidades.bi.Ingreso;
import com.erpweb.entidades.empresa.Empresa;
import com.erpweb.repositorios.bi.IngresoRepository;
import com.erpweb.repositorios.empresa.EmpresaRepository;
import com.erpweb.servicios.bi.interfaces.IngresoServiceInterfaz;

@Service
public class IngresoService implements IngresoServiceInterfaz {

	@Autowired
	private IngresoRepository ingresoRepository;
	@Autowired
	private EmpresaRepository empresaRepository;
	
	
	@Override
	public Boolean creaIngresoDesdeIngresoDto(IngresoDto ingresoDto) {
		
		Ingreso ingreso = new Ingreso();
		
		if(ingresoDto.getEmpresaId() == null) {
			return Boolean.FALSE;
		}
		
		Empresa empresa = empresaRepository.findOne(ingresoDto.getEmpresaId());
		
		ingreso.setCodigo(ingresoDto.getCodigo());
		ingreso.setEmpresa(empresa);
		ingreso.setProcedencia(ingresoDto.getProcedencia());
		ingreso.setBaseImponible(ingresoDto.getBaseImponible());
		ingreso.setCuotaTributaria(ingresoDto.getCuotaTributaria());
		ingreso.setImporteTotal(ingresoDto.getImporteTotal());
		ingreso.setDescripcion(ingresoDto.getDescripcion());
		ingreso.setObservaciones(ingresoDto.getObservaciones()); 
		
		try {
			//Guardamos el ingreso en base de datos
			ingresoRepository.save(ingreso);
			
		}catch(Exception e) {
			
			System.out.println("Error: " + e.getLocalizedMessage() );
			
			return Boolean.FALSE;
		}
		
		return Boolean.TRUE;
	}

	@Override
	public IngresoDto obtieneIngresoDto(Long id, Long empresaId) {
		
		Ingreso ingreso = ingresoRepository.findByIdAndEmpresaId(id, empresaId);
		
		if(ingreso == null) {
			return new IngresoDto();
		}
		
		IngresoDto ingresoDto = new IngresoDto();
		
		ingresoDto.setCodigo(ingresoDto.getCodigo());
		ingresoDto.setEmpresaId(empresaId);
		ingresoDto.setProcedencia(ingreso.getProcedencia());
		ingresoDto.setBaseImponible(ingreso.getBaseImponible());
		ingresoDto.setCuotaTributaria(ingreso.getCuotaTributaria());
		ingresoDto.setImporteTotal(ingreso.getImporteTotal());
		ingresoDto.setDescripcion(ingreso.getDescripcion());
		ingresoDto.setObservaciones(ingreso.getObservaciones()); 
		
		return ingresoDto;
	}

	@Override
	public Boolean actualizaIngreso(IngresoDto ingresoDto) {

		Ingreso ingreso = new Ingreso();
		
		if(ingresoDto.getEmpresaId() == null) {
			return Boolean.FALSE;
		}
		
		Empresa empresa = empresaRepository.findOne(ingresoDto.getEmpresaId());
		
		ingreso.setId(ingresoDto.getId());
		ingreso.setCodigo(ingresoDto.getCodigo());
		ingreso.setEmpresa(empresa);
		ingreso.setProcedencia(ingresoDto.getProcedencia());
		ingreso.setBaseImponible(ingresoDto.getBaseImponible());
		ingreso.setCuotaTributaria(ingresoDto.getCuotaTributaria());
		ingreso.setImporteTotal(ingresoDto.getImporteTotal());
		ingreso.setDescripcion(ingresoDto.getDescripcion());
		ingreso.setObservaciones(ingresoDto.getObservaciones()); 
		
		try {
			//Guardamos el ingreso en base de datos
			ingresoRepository.save(ingreso);
			
		}catch(Exception e) {
			
			System.out.println("Error: " + e.getLocalizedMessage() );
			
			return Boolean.FALSE;
		}
		
		return Boolean.TRUE;
	}

	@Override
	public Boolean eliminaIngreso(Ingreso ingreso) {
		
		if(ingreso == null || ingreso.getId() == null) {
			return Boolean.FALSE;
		}
		
		try {
			//Elimnamos el ingreso
			ingresoRepository.deleteById(ingreso.getId());
			
		}catch(Exception e) {
			
			System.out.println("Error: " + e.getLocalizedMessage());
			
			return Boolean.FALSE;
		}
		
		return Boolean.TRUE;
	}

	@Override
	public Ingreso obtieneIngreso(Long id, Long empresaId) {
		
		Ingreso ingreso;
		
		try {
			//Recuperamos el gasto
			ingreso = ingresoRepository.findByIdAndEmpresaId(id, empresaId);
			
		}catch(Exception e) {
			
			System.out.println("Error: " + e.getLocalizedMessage());
			
			return new Ingreso();
		}
		
		
		return ingreso;
	}


	

}
