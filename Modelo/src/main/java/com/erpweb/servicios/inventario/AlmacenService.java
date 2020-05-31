package com.erpweb.servicios.inventario;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpweb.dto.AlmacenDto;
import com.erpweb.entidades.empresa.Empresa;
import com.erpweb.entidades.inventario.Almacen;
import com.erpweb.entidades.usuarios.Usuario;
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
		
		logger.debug("Entramos en el metodo eliminarAlmacen() con la empresa={}", almacen.getEmpresa().getId() );
		
		if(almacen == null || almacen.getId() == null) {
			
			return new AccionRespuesta();
		}
		
		try {
			//Elimnamos el almacen
			almacenRepository.deleteById(almacen.getId());
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo eliminarAlmacen() con la empresa{} ", almacen.getEmpresa().getId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public AccionRespuesta eliminarAlmacenPorId(Long almacenId) {
		
		logger.error("Entramos en el metodo eliminarAlmacenPorId() con id={}", almacenId );
				
		try {
			
			//Elimnamos el almacen
			almacenRepository.deleteById(almacenId);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo eliminarAlmacenPorId() con id={}", almacenId );
			
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
		
		try {
			
			almacenDto.setId(almacen.getId());
			almacenDto.setCodigo(almacen.getCodigo());
			almacenDto.setEmpresaId(almacen.getEmpresa().getId());
			almacenDto.setNombre(almacen.getNombre());
			
		} catch(Exception e) {
			
			logger.error("Error en el metodo obtenerAlmacenDtoDesdeAlmacen() con la empresa{} ", empresaId );
			
			e.printStackTrace();
		}
		
		return almacenDto;
	}
	
	public AccionRespuesta getAlmacen(Long almacenId, Usuario user) {
		
		logger.debug("Entramos en el metodo getAlmacen()");
		
		if( almacenId == null) {
			
			return new AccionRespuesta(-1L, "Error, existe el almacen", Boolean.FALSE);
		}
		
		AlmacenDto almacenDto = this.obtenerAlmacenDtoDesdeAlmacen(almacenId, user.getEmpresa().getId());
		
		AccionRespuesta AccionRespuesta = new AccionRespuesta();
		
		if( almacenDto != null ) {
			
			AccionRespuesta.setId( almacenDto.getId() );
			
			AccionRespuesta.setRespuesta("");
			
			AccionRespuesta.setResultado(Boolean.TRUE);
			
			HashMap<String, Object> mapa = new HashMap<String, Object>();
			
			mapa.put("almacenDto", almacenDto);
			
			AccionRespuesta.setData(new HashMap<String, Object>(mapa));
			
		}else {
			
			AccionRespuesta.setId( -1L );
			
			AccionRespuesta.setRespuesta("Error, no se ha podido recuperar el almacen");
			
			AccionRespuesta.setResultado(Boolean.FALSE);
		}
		
		return AccionRespuesta;
	}

}
