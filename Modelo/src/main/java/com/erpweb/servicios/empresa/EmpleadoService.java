package com.erpweb.servicios.empresa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpweb.dto.EmpleadoDto;
import com.erpweb.entidades.empresa.Empleado;
import com.erpweb.entidades.usuarios.Usuario;
import com.erpweb.repositorios.empresa.EmpleadoRepository;
import com.erpweb.utiles.AccionRespuesta;



@Service
public class EmpleadoService {

	@Autowired
	private EmpleadoRepository empleadoRepository;
	
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	public AccionRespuesta crearEmpleadoDesdeEmpleadoDto(EmpleadoDto empleadoDto) {
		
		logger.debug("Entramos en el metodo crearempleadoDesdeempleadoDto() con ID={}", empleadoDto.getId() );
		
		Empleado empleado = new Empleado();
		
		empleado.setCodigo(empleadoDto.getCodigo());
		empleado.setNombre(empleadoDto.getNombre());
		empleado.setApellidoPrimero(empleadoDto.getApellidoPrimero());
		empleado.setApellidoSegundo(empleadoDto.getApellidoSegundo());
		empleado.setNif(empleadoDto.getNif());
		empleado.setCodigoPostal(empleadoDto.getCodigoPostal());
		empleado.setDireccion(empleadoDto.getDireccion());
		empleado.setEdificio(empleadoDto.getEdificio());
		empleado.setObservaciones(empleadoDto.getObservaciones());
		empleado.setTelefono(empleadoDto.getTelefono());
		empleado.setProvincia(empleadoDto.getProvincia());
		empleado.setPoblacion(empleadoDto.getPoblacion());
		empleado.setRegion(empleadoDto.getRegion());
		empleado.setPais(empleadoDto.getPais());
		empleado.setTipoEmpleado(empleadoDto.getTipoEmpleado());
	
		try {
			
			//Guardamos el empleado
			empleadoRepository.save(empleado);
			
			return this.devolverDatosEmpleadoDto(empleadoDto, empleado);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo crearempleadoDesdeempleadoDto() con ID={}", empleadoDto.getId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
	}
	
	public AccionRespuesta actualizarEmpleadoDesdeEmpleadoDto(EmpleadoDto empleadoDto) {
		
		logger.debug("Entramos en el metodo crearempleadoDesdeempleadoDto() con ID={}", empleadoDto.getId() );
		
		Empleado empleado = new Empleado();
		
		
		empleado.setId(empleadoDto.getId());
		empleado.setCodigo(empleadoDto.getCodigo());
		empleado.setNombre(empleadoDto.getNombre());
		empleado.setApellidoPrimero(empleadoDto.getApellidoPrimero());
		empleado.setApellidoSegundo(empleadoDto.getApellidoSegundo());
		empleado.setNif(empleadoDto.getNif());
		empleado.setCodigoPostal(empleadoDto.getCodigoPostal());
		empleado.setDireccion(empleadoDto.getDireccion());
		empleado.setEdificio(empleadoDto.getEdificio());
		empleado.setObservaciones(empleadoDto.getObservaciones());
		empleado.setTelefono(empleadoDto.getTelefono());
		empleado.setProvincia(empleadoDto.getProvincia());
		empleado.setPoblacion(empleadoDto.getPoblacion());
		empleado.setRegion(empleadoDto.getRegion());
		empleado.setPais(empleadoDto.getPais());
		empleado.setTipoEmpleado(empleadoDto.getTipoEmpleado());
	
		try {
			
			//Guardamos el empleado
			empleadoRepository.save(empleado);
			
			return this.devolverDatosActualizadosEmpleadoDto(empleadoDto, empleado);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo crearempleadoDesdeempleadoDto() con ID={}", empleadoDto.getId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
	}
	
	public AccionRespuesta eliminarEmpleado(Empleado empleado) {
		
		logger.debug("Entramos en el metodo eliminarEmpleado()" );
		
		if(empleado == null || empleado.getId() == null) {
			
			logger.error("ERROR en el metodo eliminarEmpleado()");
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
		
		try {
			
			//Eliminamos el empleado
			empleadoRepository.deleteById(empleado.getId());
			
			return new AccionRespuesta(-2L, "OK", Boolean.TRUE);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo crearempleadoDesdeempleadoDto() con ID={} ", empleado.getId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
	}
	
	public AccionRespuesta eliminarEmpleadoPorId(Long empleadoId) {
		
		logger.error("Entramos en el metodo eliminarEmpleadoPorId()" );
		
		if( empleadoId == null) {
			
			logger.error("ERROR en el metodo eliminarEmpleadoPorId()");
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}

				
		try {
			
			//Elimnamos el empleado
			empleadoRepository.deleteById(empleadoId);
			
			return new AccionRespuesta(-2L, "OK", Boolean.TRUE);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo eliminarEmpleadoPorId() con ID={}", empleadoId );
			
			e.printStackTrace();
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
	}
	
	public EmpleadoDto obtenerEmpleadoDtoDesdeEmpleado(Long id) {
		
		logger.debug("Entramos en el metodo crearempleadoDesdeempleadoDto() con ID={}", id );
		
		Optional<Empleado> empleadoOptional = empleadoRepository.findById(id);
		
		Empleado empleado = empleadoOptional.get();
		
		if(empleado == null) {
			return new EmpleadoDto();
		}
		
		EmpleadoDto empleadoDto = new EmpleadoDto();
		
		try {
			
			empleadoDto.setId(empleado.getId());
			empleadoDto.setCodigo(empleado.getCodigo());
			empleadoDto.setNombre(empleado.getNombre());
			empleadoDto.setApellidoPrimero(empleado.getApellidoPrimero());
			empleadoDto.setApellidoSegundo(empleado.getApellidoSegundo());
			empleadoDto.setNif(empleado.getNif());
			empleadoDto.setCodigoPostal(empleado.getCodigoPostal());
			empleadoDto.setDireccion(empleado.getDireccion());
			empleadoDto.setEdificio(empleado.getEdificio());
			empleadoDto.setObservaciones(empleado.getObservaciones());
			empleadoDto.setTelefono(empleado.getTelefono());
			empleadoDto.setProvincia(empleado.getProvincia());
			empleadoDto.setPoblacion(empleado.getPoblacion());
			empleadoDto.setRegion(empleado.getRegion());
			empleadoDto.setPais(empleado.getPais());
			empleadoDto.setTipoEmpleado(empleado.getTipoEmpleado());
			
		} catch(Exception e) {
			
			logger.error("Error en el metodo crearempleadoDesdeempleadoDto() con ID={} ", id );
			
			e.printStackTrace();
		}
		
		return empleadoDto;
	}
	
	public List<EmpleadoDto> getListadoEmpleados() {
		
		logger.debug("Entramos en el metodo getListadoEmpleados()" );
		
		try {
			
			List<Empleado> clientes = empleadoRepository.findAll();
			
			return this.obtieneListadoEmpleadoDtoDelRepository(clientes);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo getListado()" );
			
			e.printStackTrace();
		}
			
		return new ArrayList<EmpleadoDto>();
	}
	
	public AccionRespuesta getEmpleado(Long empleadoId, Usuario user) {
		
		logger.debug("Entramos en el metodo getEmpleado()");
		
		if( empleadoId == null) {
			
			return new AccionRespuesta(-1L, "Error, existe el empleado", Boolean.FALSE);
		}
		
		EmpleadoDto empleadoDto = this.obtenerEmpleadoDtoDesdeEmpleado(empleadoId);
		
		AccionRespuesta AccionRespuesta = new AccionRespuesta();
		
		if( empleadoDto != null ) {
			
			AccionRespuesta.setId( empleadoDto.getId() );
			
			AccionRespuesta.setRespuesta("");
			
			AccionRespuesta.setResultado(Boolean.TRUE);
			
			HashMap<String, Object> mapa = new HashMap<String, Object>();
			
			mapa.put("empleadoDto", empleadoDto);
			
			AccionRespuesta.setData(new HashMap<String, Object>(mapa));
			
		}else {
			
			AccionRespuesta.setId( -1L );
			
			AccionRespuesta.setRespuesta("Error, no se ha podido recuperar el empleado");
			
			AccionRespuesta.setResultado(Boolean.FALSE);
		}
		
		return AccionRespuesta;
	}
	
	public AccionRespuesta getCrearEditarEmpleado(EmpleadoDto empleadoDto, Usuario user) {
		
		logger.debug("Entramos en el metodo getCrearEditarEmpleado() con usuario={}", user.getId() );
		
		if( empleadoDto.getId() != null && empleadoDto.getId().longValue() > 0) {
			
			logger.debug("Se va a realizar una actualizacion del Empleado con usuario={}", user.getId() );
			
			return this.actualizarEmpleadoDesdeEmpleadoDto(empleadoDto);
			
		} else {
			
			logger.debug("Se va a crear un Empleado con usuario={}", user.getId() );
			
			return this.crearEmpleadoDesdeEmpleadoDto(empleadoDto);
		}
	}
	
	private AccionRespuesta devolverDatosEmpleadoDto(EmpleadoDto empleadoDto, Empleado empleadoSave) {
		
		AccionRespuesta respuesta = new AccionRespuesta();
		
		//Guardado el cliente se devuelve en su dto
		if(empleadoSave != null && empleadoSave.getId() != null) {
			
			empleadoDto.setId(empleadoSave.getId());
			
			respuesta.setId(empleadoSave.getId());
			
			respuesta.setCodigo("OK");
						
			respuesta.setResultado(Boolean.TRUE);
			
			HashMap<String, Object> data= new HashMap<String, Object> ();
			
			data.put("empleadoDto", empleadoDto);
			
			respuesta.setData(data);
			
		}else {
			
			respuesta.setCodigo("NOK");
						
			respuesta.setResultado(Boolean.FALSE);
			
			HashMap<String, Object> data= new HashMap<String, Object> ();
			
			data.put("empleadoDto", empleadoDto);
			
			respuesta.setData(data);
		}
		
		return respuesta;
	}
	
	private AccionRespuesta devolverDatosActualizadosEmpleadoDto(EmpleadoDto empleadoDto, Empleado empleadoSave) {
		
		AccionRespuesta respuesta = new AccionRespuesta();
		
		if(empleadoSave != null && empleadoDto != null) {
			
			respuesta.setId(empleadoSave.getId());
			
			respuesta.setCodigo("OK");
						
			respuesta.setResultado(Boolean.TRUE);
			
			HashMap<String, Object> data= new HashMap<String, Object> ();
			
			data.put("empleadoDto", empleadoDto);
			
			respuesta.setData(data);
			
		}else {
			
			respuesta.setId(-1L);
			
			respuesta.setCodigo("NOK");
			
			respuesta.setResultado(Boolean.FALSE);
			
			HashMap<String, Object> data= new HashMap<String, Object> ();
			
			data.put("empleadoDto", empleadoDto);
			
			respuesta.setData(data);			
		}
		
		return respuesta;		
	}
	
	private List<EmpleadoDto> obtieneListadoEmpleadoDtoDelRepository(List<Empleado> empleados){
		
		List<EmpleadoDto> empleadosDto = new ArrayList<EmpleadoDto>();
		
		if(CollectionUtils.isNotEmpty(empleados) ) {
			
			for(Empleado empleado : empleados) {
				
				EmpleadoDto empleadoDto = new EmpleadoDto();
				
				empleadoDto.setId(empleado.getId());
				empleadoDto.setCodigo(empleado.getCodigo());
				empleadoDto.setNombre(empleado.getNombre());
				empleadoDto.setApellidoPrimero(empleado.getApellidoPrimero());
				empleadoDto.setApellidoSegundo(empleado.getApellidoSegundo());
				empleadoDto.setNif(empleado.getNif());
				empleadoDto.setCodigoPostal(empleado.getCodigoPostal());
				empleadoDto.setDireccion(empleado.getDireccion());
				empleadoDto.setEdificio(empleado.getEdificio());
				empleadoDto.setObservaciones(empleado.getObservaciones());
				empleadoDto.setTelefono(empleado.getTelefono());
				empleadoDto.setProvincia(empleado.getProvincia());
				empleadoDto.setPoblacion(empleado.getPoblacion());
				empleadoDto.setRegion(empleado.getRegion());
				empleadoDto.setPais(empleado.getPais());
				empleadoDto.setTipoEmpleado(empleado.getTipoEmpleado());
				
				empleadosDto.add(empleadoDto);				
			}
		}
		
		return empleadosDto;
	}

}
