package com.erpweb.servicios.inventario;

import java.util.HashMap;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpweb.dto.AlmacenDto;
import com.erpweb.entidades.inventario.Almacen;
import com.erpweb.entidades.usuarios.Usuario;
import com.erpweb.repositorios.inventario.AlmacenRepository;
import com.erpweb.utiles.AccionRespuesta;



@Service
public class AlmacenService {

	@Autowired
	private AlmacenRepository almacenRepository;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	public AccionRespuesta crearAlmacenDesdeAlmacenDto(AlmacenDto almacenDto) {
		
		logger.debug("Entramos en el metodo crearAlmacenDesdeAlmacenDto() con ID={}", almacenDto.getId() );
		
		Almacen almacen = new Almacen();
		
		almacen.setCodigo(almacenDto.getCodigo());
		almacen.setNombre(almacenDto.getNombre());
		almacen.setCodigoPostal(almacenDto.getCodigoPostal());
		almacen.setDireccion(almacenDto.getDireccion());
		almacen.setEdificio(almacenDto.getEdificio());
		almacen.setObservaciones(almacenDto.getObservaciones());
		almacen.setTelefono(almacenDto.getTelefono());
		almacen.setPoblacion(almacenDto.getPoblacion());
		almacen.setProvincia(almacenDto.getProvincia());
		almacen.setRegion(almacenDto.getRegion());
		almacen.setPais(almacenDto.getPais());
		
		try {
			
			//Guardamos el almacen en base de datos
			almacenRepository.save(almacen);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo crearAlmacenDesdeAlmacenDto() con ID={}", almacenDto.getId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public AccionRespuesta actualizarAlmacenDesdeAlmacenDto(AlmacenDto almacenDto) {
		
		logger.debug("Entramos en el metodo actualizarAlmacenDesdeAlmacenDto() con ID={}", almacenDto.getId() );
		
		Almacen almacen = new Almacen();
		
		almacen.setId(almacenDto.getId());
		almacen.setCodigo(almacenDto.getCodigo());
		almacen.setNombre(almacenDto.getNombre());
		almacen.setCodigoPostal(almacenDto.getCodigoPostal());
		almacen.setDireccion(almacenDto.getDireccion());
		almacen.setEdificio(almacenDto.getEdificio());
		almacen.setObservaciones(almacenDto.getObservaciones());
		almacen.setTelefono(almacenDto.getTelefono());
		almacen.setPoblacion(almacenDto.getPoblacion());
		almacen.setProvincia(almacenDto.getProvincia());
		almacen.setRegion(almacenDto.getRegion());
		almacen.setPais(almacenDto.getPais());
		
		try {
			//Guardamos el almacen en base de datos
			almacenRepository.save(almacen);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo actualizarAlmacenDesdeAlmacenDto() con ID={}", almacenDto.getId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public AccionRespuesta eliminarAlmacen(Almacen almacen) {
		
		logger.debug("Entramos en el metodo eliminarAlmacen() con ID={}", almacen.getId() );
		
		if(almacen == null || almacen.getId() == null) {
			
			return new AccionRespuesta();
		}
		
		try {
			//Elimnamos el almacen
			almacenRepository.deleteById(almacen.getId());
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo eliminarAlmacen() con ID={}", almacen.getId() );
			
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
	
	public AlmacenDto obtenerAlmacenDtoDesdeAlmacen(Long id ) {
		
		logger.debug("Entramos en el metodo obtenerAlmacenDtoDesdeAlmacen() con ID={}", id );
		
		Optional<Almacen> almacenOptional = almacenRepository.findById(id);
		
		Almacen almacen = almacenOptional.get();
		
		if(almacen == null) {
			return new AlmacenDto();
		}
		
		AlmacenDto almacenDto = new AlmacenDto();
		
		try {
			
			almacenDto.setId(almacen.getId());
			almacenDto.setCodigo(almacen.getCodigo());
			almacenDto.setNombre(almacen.getNombre());
			almacenDto.setCodigoPostal(almacen.getCodigoPostal());
			almacenDto.setDireccion(almacen.getDireccion());
			almacenDto.setEdificio(almacen.getEdificio());
			almacenDto.setObservaciones(almacen.getObservaciones());
			almacenDto.setTelefono(almacen.getTelefono());
			almacenDto.setPoblacion(almacen.getPoblacion());
			almacenDto.setProvincia(almacen.getProvincia());
			almacenDto.setRegion(almacen.getRegion());
			almacenDto.setPais(almacen.getPais());
			
		} catch(Exception e) {
			
			logger.error("Error en el metodo obtenerAlmacenDtoDesdeAlmacen() con ID={}", id );
			
			e.printStackTrace();
		}
		
		return almacenDto;
	}
	
	public AccionRespuesta getAlmacen(Long almacenId, Usuario user) {
		
		logger.debug("Entramos en el metodo getAlmacen()");
		
		if( almacenId == null) {
			
			return new AccionRespuesta(-1L, "Error, existe el almacen", Boolean.FALSE);
		}
		
		AlmacenDto almacenDto = this.obtenerAlmacenDtoDesdeAlmacen(almacenId);
		
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
	
	public AccionRespuesta getCrearEditarAlmacen(AlmacenDto almacenDto, Usuario user) {
		
		logger.debug("Entramos en el metodo getCrearEditarAlmacen() con usuario={}", user.getId() );
		
		if( almacenDto.getId() != null && almacenDto.getId().longValue() > 0) {
			
			logger.debug("Se va a realizar una actualizacion del almacen con usuario={}", user.getId() );
			
			return this.actualizarAlmacenDesdeAlmacenDto(almacenDto);
			
		} else {
			
			logger.debug("Se va a crear un almacen con usuario={}", user.getId() );
			
			return this.crearAlmacenDesdeAlmacenDto(almacenDto);
		}
	}

}
