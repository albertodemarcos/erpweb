package com.erpweb.servicios.bi;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpweb.dto.IngresoDto;
import com.erpweb.entidades.bi.Ingreso;
import com.erpweb.entidades.empresa.Empresa;
import com.erpweb.repositorios.bi.IngresoRepository;
import com.erpweb.repositorios.empresa.EmpresaRepository;
import com.erpweb.utiles.AccionRespuesta;

@Service
public class IngresoService {

	@Autowired
	private IngresoRepository ingresoRepository;
	@Autowired
	private EmpresaRepository empresaRepository;
	
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public AccionRespuesta crearIngresoDesdeIngresoDto(IngresoDto ingresoDto) {
		
		Ingreso ingreso = new Ingreso();
		
		if(ingresoDto.getEmpresaId() == null) {
			
			return new AccionRespuesta();
		}
		
		Empresa empresa = empresaRepository.findById(ingresoDto.getEmpresaId()).orElse(new Empresa());
		
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
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public AccionRespuesta actualizarIngresoDesdeIngresoDto(IngresoDto ingresoDto) {

		Ingreso ingreso = new Ingreso();
		
		if(ingresoDto.getEmpresaId() == null) {
			return new AccionRespuesta();
		}
		
		Empresa empresa = empresaRepository.findById(ingresoDto.getEmpresaId()).orElse(new Empresa());
		
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
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public AccionRespuesta eliminarIngreso(Ingreso ingreso) {
		
		if(ingreso == null || ingreso.getId() == null) {
			
			return new AccionRespuesta();
		}
		
		try {
			//Elimnamos el ingreso
			ingresoRepository.deleteById(ingreso.getId());
			
		}catch(Exception e) {
			
			System.out.println("Error: " + e.getLocalizedMessage());
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
		
	}
		
	public IngresoDto obtenerIngresoDtoDesdeIngreso(Long id, Long empresaId) {
		
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

}
