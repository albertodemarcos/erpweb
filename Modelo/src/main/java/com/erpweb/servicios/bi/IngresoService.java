package com.erpweb.servicios.bi;


import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpweb.dto.IngresoDto;
import com.erpweb.entidades.bi.Ingreso;
import com.erpweb.entidades.empresa.Empresa;
import com.erpweb.entidades.usuarios.Usuario;
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
		
		logger.debug("Entramos en el metodo crearIngresoDesdeIngresoDto() con la empresa={}", ingresoDto.getEmpresaId() );
		
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
			
			logger.error("Error en el metodo crearIngresoDesdeIngresoDto() con la empresa{} ", ingresoDto.getEmpresaId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public AccionRespuesta actualizarIngresoDesdeIngresoDto(IngresoDto ingresoDto) {
		
		logger.debug("Entramos en el metodo actualizarIngresoDesdeIngresoDto() con la empresa={}", ingresoDto.getEmpresaId() );

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
			
			logger.error("Error en el metodo actualizarIngresoDesdeIngresoDto() con la empresa{} ", ingresoDto.getEmpresaId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public AccionRespuesta eliminarIngreso(Ingreso ingreso) {
		
		logger.debug("Entramos en el metodo eliminarIngreso() con la empresa={}", ingreso.getEmpresa().getId() );
		
		if(ingreso == null || ingreso.getId() == null) {
			
			return new AccionRespuesta();
		}
		
		try {
			
			//Elimnamos el ingreso
			ingresoRepository.deleteById(ingreso.getId());
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo eliminarIngreso() con la empresa{} ", ingreso.getEmpresa().getId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
		
	}
	
	public AccionRespuesta eliminarIngresoPorId(Long ingresoId) {
		
		logger.error("Entramos en el metodo eliminarIngresoPorId() con id={}", ingresoId );
				
		try {
			
			//Elimnamos el ingreso
			ingresoRepository.deleteById(ingresoId);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo eliminarIngresoPorId() con id={}", ingresoId );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
		
	public IngresoDto obtenerIngresoDtoDesdeIngreso(Long id, Long empresaId) {
		
		logger.debug("Entramos en el metodo obtenerIngresoDtoDesdeIngreso() con la empresa={}", empresaId );
		
		Ingreso ingreso = ingresoRepository.findByIdAndEmpresaId(id, empresaId);
		
		if(ingreso == null) {
			
			return new IngresoDto();
		}
		
		IngresoDto ingresoDto = new IngresoDto();
		
		try {
			
			ingresoDto.setCodigo(ingresoDto.getCodigo());
			ingresoDto.setEmpresaId(empresaId);
			ingresoDto.setProcedencia(ingreso.getProcedencia());
			ingresoDto.setBaseImponible(ingreso.getBaseImponible());
			ingresoDto.setCuotaTributaria(ingreso.getCuotaTributaria());
			ingresoDto.setImporteTotal(ingreso.getImporteTotal());
			ingresoDto.setDescripcion(ingreso.getDescripcion());
			ingresoDto.setObservaciones(ingreso.getObservaciones()); 
			
		} catch(Exception e) {
			
			logger.error("Error en el metodo obtenerIngresoDtoDesdeIngreso() con la empresa{} ", empresaId );
			
			e.printStackTrace();
		}
		
		
		return ingresoDto;
	}
	
	public AccionRespuesta getIngreso(Long ingresoId, Usuario user) {
		
		logger.debug("Entramos en el metodo getIngreso()");
		
		if( ingresoId == null) {
			
			return new AccionRespuesta(-1L, "Error, existe el ingreso", Boolean.FALSE);
		}
		
		IngresoDto ingresoDto = this.obtenerIngresoDtoDesdeIngreso(ingresoId, user.getEmpresa().getId());
		
		AccionRespuesta AccionRespuesta = new AccionRespuesta();
		
		if( ingresoDto != null ) {
			
			AccionRespuesta.setId( ingresoDto.getId() );
			
			AccionRespuesta.setRespuesta("");
			
			AccionRespuesta.setResultado(Boolean.TRUE);
			
			HashMap<String, Object> mapa = new HashMap<String, Object>();
			
			mapa.put("ingresoDto", ingresoDto);
			
			AccionRespuesta.setData(new HashMap<String, Object>(mapa));
			
		}else {
			
			AccionRespuesta.setId( -1L );
			
			AccionRespuesta.setRespuesta("Error, no se ha podido recuperar el ingreso");
			
			AccionRespuesta.setResultado(Boolean.FALSE);
		}
		
		return AccionRespuesta;
	}

}
