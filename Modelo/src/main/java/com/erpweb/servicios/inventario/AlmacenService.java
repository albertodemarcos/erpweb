package com.erpweb.servicios.inventario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpweb.dto.AlmacenDto;
import com.erpweb.entidades.embebidos.DireccionPostal;
import com.erpweb.entidades.embebidos.OrigenPersona;
import com.erpweb.entidades.inventario.Almacen;
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
		
		almacen.setDireccionPostal(new DireccionPostal());
		
		almacen.getDireccionPostal().setCodigoPostal(almacenDto.getCodigoPostal());
		almacen.getDireccionPostal().setDireccion(almacenDto.getDireccion());
		almacen.getDireccionPostal().setEdificio(almacenDto.getEdificio());
		almacen.getDireccionPostal().setObservaciones(almacenDto.getObservaciones());
		almacen.getDireccionPostal().setTelefono(almacenDto.getTelefono());
		
		almacen.setOrigenPersona(new OrigenPersona());
		
		almacen.getOrigenPersona().setPoblacion(almacenDto.getPoblacion());
		almacen.getOrigenPersona().setProvincia(almacenDto.getProvincia());
		almacen.getOrigenPersona().setRegion(almacenDto.getRegion());
		almacen.getOrigenPersona().setPais(almacenDto.getPais());
		
		try {
			
			//Guardamos el almacen en base de datos
			Almacen almacenSave = almacenRepository.save(almacen);
			
			return this.devolverDatosAlmacenDto(almacenDto, almacenSave);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo crearAlmacenDesdeAlmacenDto() con ID={}", almacenDto.getId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
	}
	
	public AccionRespuesta actualizarAlmacenDesdeAlmacenDto(AlmacenDto almacenDto) {
		
		logger.debug("Entramos en el metodo actualizarAlmacenDesdeAlmacenDto() con ID={}", almacenDto.getId() );
		
		Almacen almacen = new Almacen();
		
		almacen.setId(almacenDto.getId());
		almacen.setCodigo(almacenDto.getCodigo());
		almacen.setNombre(almacenDto.getNombre());
		
		almacen.setDireccionPostal(new DireccionPostal());
		
		almacen.getDireccionPostal().setCodigoPostal(almacenDto.getCodigoPostal());
		almacen.getDireccionPostal().setDireccion(almacenDto.getDireccion());
		almacen.getDireccionPostal().setEdificio(almacenDto.getEdificio());
		almacen.getDireccionPostal().setObservaciones(almacenDto.getObservaciones());
		almacen.getDireccionPostal().setTelefono(almacenDto.getTelefono());
		
		almacen.setOrigenPersona(new OrigenPersona());
		
		almacen.getOrigenPersona().setPoblacion(almacenDto.getPoblacion());
		almacen.getOrigenPersona().setProvincia(almacenDto.getProvincia());
		almacen.getOrigenPersona().setRegion(almacenDto.getRegion());
		almacen.getOrigenPersona().setPais(almacenDto.getPais());
		
		try {
			//Guardamos el almacen en base de datos
			Almacen almacenSave = almacenRepository.save(almacen);
			
			return this.devolverDatosActualizadosAlmacenDto(almacenDto, almacenSave);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo actualizarAlmacenDesdeAlmacenDto() con ID={}", almacenDto.getId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
	}
	
	public AccionRespuesta eliminarAlmacen(Almacen almacen) {
		
		logger.debug("Entramos en el metodo eliminarAlmacen()");
		
		if(almacen == null || almacen.getId() == null) {
			
			logger.error("ERROR en el metodo eliminarAlmacen()");
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
		
		try {
			//Elimnamos el almacen
			almacenRepository.deleteById(almacen.getId());
			
			return new AccionRespuesta(-2L, "OK", Boolean.TRUE);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo eliminarAlmacen() con ID={}", almacen.getId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
	}
	
	public AccionRespuesta eliminarAlmacenPorId(Long almacenId) {
		
		logger.trace("Entramos en el metodo eliminarAlmacenPorId()");
		
		if( almacenId == null) {
			
			logger.error("ERROR en el metodo eliminarAlmacenPorId()");
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
				
		try {
			
			//Elimnamos el almacen
			almacenRepository.deleteById(almacenId);
			
			return new AccionRespuesta(-2L, "OK", Boolean.TRUE);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo eliminarAlmacenPorId() con id={}", almacenId );
			
			e.printStackTrace();
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
	}
	
	public AlmacenDto obtenerAlmacenDtoDesdeAlmacen(Long id ) {
		
		logger.debug("Entramos en el metodo obtenerAlmacenDtoDesdeAlmacen() con ID={}", id );
		
		Optional<Almacen> almacenOptional = almacenRepository.findById(id);
		
		Almacen almacen = almacenOptional.orElse(null);
		
		if(almacen == null) {
			return new AlmacenDto();
		}
		
		AlmacenDto almacenDto = new AlmacenDto();
		
		try {
			
			almacenDto.setId(almacen.getId());
			almacenDto.setCodigo(almacen.getCodigo());
			almacenDto.setNombre(almacen.getNombre());
			almacenDto.setCodigoPostal(almacen.getDireccionPostal().getCodigoPostal());
			almacenDto.setDireccion(almacen.getDireccionPostal().getDireccion());
			almacenDto.setEdificio(almacen.getDireccionPostal().getEdificio());
			almacenDto.setObservaciones(almacen.getDireccionPostal().getObservaciones());
			almacenDto.setTelefono(almacen.getDireccionPostal().getTelefono());
			almacenDto.setPoblacion(almacen.getOrigenPersona().getPoblacion());
			almacenDto.setProvincia(almacen.getOrigenPersona().getProvincia());
			almacenDto.setRegion(almacen.getOrigenPersona().getRegion());
			almacenDto.setPais(almacen.getOrigenPersona().getPais());
			
		} catch(Exception e) {
			
			logger.error("Error en el metodo obtenerAlmacenDtoDesdeAlmacen() con ID={}", id );
			
			e.printStackTrace();
		}
		
		return almacenDto;
	}
	
	public List<AlmacenDto> getListadoAlmacenes() {
		
		logger.debug("Entramos en el metodo getListadoAlmacenes()");
		
		try {
			
			List<Almacen> almacenes = almacenRepository.findAll();
			
			return this.obtieneListadoAlmacenDtoDelRepository(almacenes);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo getListadoAlmacenes()" );
			
			e.printStackTrace();
		}
			
		return new ArrayList<AlmacenDto>();
	}
	
	public AccionRespuesta getAlmacen(Long almacenId) {
		
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
	
	public AccionRespuesta getCrearEditarAlmacen(AlmacenDto almacenDto) {
		
		logger.debug("Entramos en el metodo getCrearEditarAlmacen()" );
		
		if( almacenDto.getId() != null && almacenDto.getId().longValue() > 0) {
			
			logger.debug("Se va a realizar una actualizacion del almacen" );
			
			return this.actualizarAlmacenDesdeAlmacenDto(almacenDto);
			
		} else {
			
			logger.debug("Se va a crear un almacen");
			
			return this.crearAlmacenDesdeAlmacenDto(almacenDto);
		}
	}
	
	private AccionRespuesta devolverDatosAlmacenDto(AlmacenDto almacenDto, Almacen almacenSave) {
		
		AccionRespuesta respuesta = new AccionRespuesta();
		
		//Guardado el cliente se devuelve en su dto
		if(almacenSave != null && almacenSave.getId() != null) {
			
			almacenDto.setId(almacenSave.getId());
			
			respuesta.setId(almacenSave.getId());
			
			respuesta.setCodigo("OK");
						
			respuesta.setResultado(Boolean.TRUE);
			
			HashMap<String, Object> data= new HashMap<String, Object>();
			
			data.put("almacenDto", almacenDto);
			
			respuesta.setData(data);
			
		}else {
			
			respuesta.setCodigo("NOK");
						
			respuesta.setResultado(Boolean.FALSE);
			
			HashMap<String, Object> data= new HashMap<String, Object>();
			
			data.put("almacenDto", almacenDto);
			
			respuesta.setData(data);
		}
		
		return respuesta;
	}
	
	private AccionRespuesta devolverDatosActualizadosAlmacenDto(AlmacenDto almacenDto, Almacen almacenSave) {
		
		AccionRespuesta respuesta = new AccionRespuesta();
		
		if(almacenSave != null && almacenDto != null) {
			
			almacenDto.setId(almacenSave.getId());
			
			respuesta.setId(almacenSave.getId());
			
			respuesta.setCodigo("OK");
						
			respuesta.setResultado(Boolean.TRUE);
			
			HashMap<String, Object> data= new HashMap<String, Object>();
			
			data.put("almacenDto", almacenDto);
			
			respuesta.setData(data);
			
		}else {
			
			respuesta.setId(-1L);
			
			respuesta.setCodigo("NOK");
			
			respuesta.setResultado(Boolean.FALSE);
			
			HashMap<String, Object> data= new HashMap<String, Object>();
			
			data.put("almacenDto", almacenDto);
			
			respuesta.setData(data);			
		}
		
		return respuesta;		
	}

	private List<AlmacenDto> obtieneListadoAlmacenDtoDelRepository(List<Almacen> almacenes){
		
		List<AlmacenDto> almacenesDto = new ArrayList<AlmacenDto>();
		
		if(CollectionUtils.isNotEmpty(almacenes) ) {
			
			for(Almacen almacen : almacenes) {
				
				AlmacenDto almacenDto = new AlmacenDto();
				
				almacenDto.setId(almacen.getId());
				almacenDto.setCodigo(almacen.getCodigo());
				almacenDto.setNombre(almacen.getNombre());
				almacenDto.setCodigoPostal(almacen.getDireccionPostal().getCodigoPostal());
				almacenDto.setDireccion(almacen.getDireccionPostal().getDireccion());
				almacenDto.setEdificio(almacen.getDireccionPostal().getEdificio());
				almacenDto.setObservaciones(almacen.getDireccionPostal().getObservaciones());
				almacenDto.setTelefono(almacen.getDireccionPostal().getTelefono());
				almacenDto.setPoblacion(almacen.getOrigenPersona().getPoblacion());
				almacenDto.setProvincia(almacen.getOrigenPersona().getProvincia());
				almacenDto.setRegion(almacen.getOrigenPersona().getRegion());
				almacenDto.setPais(almacen.getOrigenPersona().getPais());
				
				almacenesDto.add(almacenDto);			
			}
		}
		
		return almacenesDto;
	}
}
