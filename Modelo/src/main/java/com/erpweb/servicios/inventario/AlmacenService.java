package com.erpweb.servicios.inventario;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpweb.dto.AlmacenDto;
import com.erpweb.entidades.empresa.Empresa;
import com.erpweb.entidades.inventario.Almacen;
import com.erpweb.repositorios.empresa.EmpresaRepository;
import com.erpweb.repositorios.inventario.AlmacenRepository;
import com.erpweb.utiles.AccionRespuesta;



@Service
public class AlmacenService {

	@Autowired
	private AlmacenRepository almacenRepository;
	@Autowired
	private EmpresaRepository empresaRepository;
	
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	public AccionRespuesta crearAlmacenDesdeAlmacenDto(AlmacenDto almacenDto) {
		
		logger.debug("Entramos en el metodo crearAlmacenDesdeAlmacenDto() con la empresa={}", almacenDto.getEmpresaId() );
		
		Almacen almacen = new Almacen();
		
		if(almacenDto.getEmpresaId() == null) {
			
			return new AccionRespuesta();
		}
		
		Empresa empresa = empresaRepository.findById(almacenDto.getEmpresaId()).orElse(new Empresa());
		
		almacen.setCodigo(almacenDto.getCodigo());
		almacen.setEmpresa(empresa);
		almacen.setNombre(almacenDto.getNombre());
		
		try {
			
			//Guardamos el almacen en base de datos
			almacenRepository.save(almacen);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo crearAlmacenDesdeAlmacenDto() con la empresa{} ", almacenDto.getEmpresaId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public AccionRespuesta actualizarAlmacenDesdeAlmacenDto(AlmacenDto almacenDto) {
		
		logger.debug("Entramos en el metodo actualizarAlmacenDesdeAlmacenDto() con la empresa={}", almacenDto.getEmpresaId() );
		
		Almacen almacen = new Almacen();
		
		if(almacenDto.getEmpresaId() == null) {
			
			return new AccionRespuesta();
		}
		
		Empresa empresa = empresaRepository.findById(almacenDto.getEmpresaId()).orElse(new Empresa());
		
		almacen.setId(almacenDto.getId());
		almacen.setCodigo(almacenDto.getCodigo());
		almacen.setEmpresa(empresa);
		almacen.setNombre(almacenDto.getNombre());
		
		try {
			//Guardamos el almacen en base de datos
			almacenRepository.save(almacen);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo actualizarAlmacenDesdeAlmacenDto() con la empresa{} ", almacenDto.getEmpresaId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public AccionRespuesta eliminarAlmacen(Almacen almacen) {
		
		logger.debug("Entramos en el metodo eliminarAlmacen() con la empresa={}", almacen.getId() );
		
		if(almacen == null || almacen.getId() == null) {
			
			return new AccionRespuesta();
		}
		
		try {
			//Elimnamos el gasto
			almacenRepository.deleteById(almacen.getId());
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo eliminarAlmacen() con la empresa{} ", almacen.getId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public AlmacenDto obtenerAlmacenDtoDesdeAlmacen(Long id, Long empresaId) {
		
		logger.debug("Entramos en el metodo obtenerAlmacenDtoDesdeAlmacen() con la empresa={}", empresaId );
		
		Almacen almacen = almacenRepository.findByIdAndEmpresaId(id, empresaId);
		
		if(almacen == null) {
			return new AlmacenDto();
		}
		
		AlmacenDto almacenDto = new AlmacenDto();
		
		almacenDto.setId(almacen.getId());
		almacenDto.setCodigo(almacen.getCodigo());
		almacenDto.setEmpresaId(almacen.getEmpresa().getId());
		almacenDto.setNombre(almacen.getNombre());
		
		logger.error("Error en el metodo obtenerAlmacenDtoDesdeAlmacen() con la empresa{} ", empresaId );
		
		return almacenDto;
	}

}
