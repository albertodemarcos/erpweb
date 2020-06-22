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

import com.erpweb.dto.VehiculoDto;
import com.erpweb.entidades.inventario.Vehiculo;
import com.erpweb.entidades.usuarios.Usuario;
import com.erpweb.repositorios.inventario.VehiculoRepository;
import com.erpweb.utiles.AccionRespuesta;



@Service
public class VehiculoService {

	@Autowired
	private VehiculoRepository vehiculoRepository;
	
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	public AccionRespuesta crearVehiculoDesdeVehiculoDto(VehiculoDto vehiculoDto) {
		
		logger.debug("Entramos en el metodo crearvehiculoDesdevehiculoDto() con ID={}", vehiculoDto.getId() );
		
		Vehiculo vehiculo = new Vehiculo();

		vehiculo.setCodigo(vehiculoDto.getCodigo());
		vehiculo.setMatricula(vehiculoDto.getMatricula());
		vehiculo.setMarca(vehiculoDto.getMarca());
		vehiculo.setModelo(vehiculoDto.getModelo());
		vehiculo.setTipoVehiculo(vehiculoDto.getTipoVehiculo());
		vehiculo.setTipoCombustible(vehiculoDto.getTipoCombustible());
		vehiculo.setFechaMatriculacion(vehiculoDto.getFechaMatriculacion());
		
		try {
			
			//Guardamos el vehiculo en base de datos
			vehiculoRepository.save(vehiculo);
			
			return this.devolverDatosVehiculoDto(vehiculoDto, vehiculo);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo crearvehiculoDesdevehiculoDto() con ID={}", vehiculoDto.getId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
	}
	
	public AccionRespuesta actualizarVehiculoDesdeVehiculoDto(VehiculoDto vehiculoDto) {
		
		logger.debug("Entramos en el metodo crearvehiculoDesdevehiculoDto() con ID={}", vehiculoDto.getId() );
		
		Vehiculo vehiculo = new Vehiculo();

		vehiculo.setId(vehiculoDto.getId());
		vehiculo.setCodigo(vehiculoDto.getCodigo());
		vehiculo.setMatricula(vehiculoDto.getMatricula());
		vehiculo.setMarca(vehiculoDto.getMarca());
		vehiculo.setModelo(vehiculoDto.getModelo());
		vehiculo.setTipoVehiculo(vehiculoDto.getTipoVehiculo());
		vehiculo.setTipoCombustible(vehiculoDto.getTipoCombustible());
		vehiculo.setFechaMatriculacion(vehiculoDto.getFechaMatriculacion());
		
		try {
			
			//Guardamos el vehiculo en base de datos
			vehiculoRepository.save(vehiculo);
			
			return new AccionRespuesta();
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo crearvehiculoDesdevehiculoDto() con ID={}", vehiculoDto.getId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
	}
	
	public AccionRespuesta eliminarVehiculo(Vehiculo vehiculo) {
		
		logger.debug("Entramos en el metodo crearvehiculoDesdevehiculoDto() con ID={}", vehiculo.getId() );
		
		if(vehiculo == null || vehiculo.getId() == null) {
			
			return new AccionRespuesta();
		}
		
		try {
			//Elimnamos el vehiculo
			vehiculoRepository.deleteById(vehiculo.getId());
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo crearvehiculoDesdevehiculoDto() con ID={}", vehiculo.getId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public AccionRespuesta eliminarVehiculoPorId(Long vehiculoId) {
		
		logger.error("Entramos en el metodo eliminarVehiculoPorId() con id={}", vehiculoId );
				
		try {
			
			//Elimnamos el vehiculo
			vehiculoRepository.deleteById(vehiculoId);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo eliminarVehiculoPorId() con id={}", vehiculoId );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public VehiculoDto obtenerVehiculoDtoDesdeVehiculo(Long id) {
		
		logger.debug("Entramos en el metodo crearvehiculoDesdevehiculoDto() con ID={}", id );
		
		Optional<Vehiculo> vehiculoOptional = vehiculoRepository.findById(id);
		
		Vehiculo vehiculo = vehiculoOptional.get();
		
		if(vehiculo == null) {
			return new VehiculoDto();
		}
		
		VehiculoDto vehiculoDto = new VehiculoDto();
		
		try {
			
			vehiculoDto.setId(vehiculo.getId());
			vehiculoDto.setCodigo(vehiculo.getCodigo());
			vehiculoDto.setMatricula(vehiculo.getMatricula());
			vehiculoDto.setMarca(vehiculo.getMarca());
			vehiculoDto.setModelo(vehiculo.getModelo());
			vehiculoDto.setTipoVehiculo(vehiculo.getTipoVehiculo());
			vehiculoDto.setTipoCombustible(vehiculo.getTipoCombustible());
			vehiculoDto.setFechaMatriculacion(vehiculo.getFechaMatriculacion());
			
		} catch(Exception e) {
			
			logger.error("Error en el metodo crearvehiculoDesdevehiculoDto() con ID={}", id );
			
			e.printStackTrace();
		}
		
		return vehiculoDto;
	}
	
	public List<VehiculoDto> getListadoVehiculos() {
		
		logger.debug("Entramos en el metodo getListadoVehiculos()" );
		
		try {
			
			List<Vehiculo> vehiculos = vehiculoRepository.findAll();
			
			return this.obtieneListadoVehiculoDtoDelRepository(vehiculos);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo getListadoVehiculos()" );
			
			e.printStackTrace();
		}
			
		return new ArrayList<VehiculoDto>();
	}
	
	public AccionRespuesta getVehiculo(Long vehiculoId, Usuario user) {
		
		logger.debug("Entramos en el metodo getVehiculo()");
		
		if( vehiculoId == null) {
			
			return new AccionRespuesta(-1L, "Error, existe el vehiculo", Boolean.FALSE);
		}
		
		VehiculoDto vehiculoDto = this.obtenerVehiculoDtoDesdeVehiculo(vehiculoId);
		
		AccionRespuesta AccionRespuesta = new AccionRespuesta();
		
		if( vehiculoDto != null ) {
			
			AccionRespuesta.setId( vehiculoDto.getId() );
			
			AccionRespuesta.setRespuesta("");
			
			AccionRespuesta.setResultado(Boolean.TRUE);
			
			HashMap<String, Object> mapa = new HashMap<String, Object>();
			
			mapa.put("vehiculoDto", vehiculoDto);
			
			AccionRespuesta.setData(new HashMap<String, Object>(mapa));
			
		}else {
			
			AccionRespuesta.setId( -1L );
			
			AccionRespuesta.setRespuesta("Error, no se ha podido recuperar el vehiculo");
			
			AccionRespuesta.setResultado(Boolean.FALSE);
		}
		
		return AccionRespuesta;
	}
	
	public AccionRespuesta getCrearEditarVehiculo(VehiculoDto vehiculoDto, Usuario user) {
		
		logger.debug("Entramos en el metodo getCrearEditarVehiculo() con usuario={}", user.getId() );
		
		if( vehiculoDto.getId() != null && vehiculoDto.getId().longValue() > 0) {
			
			logger.debug("Se va a realizar una actualizacion del Vehiculo con usuario={}", user.getId() );
			
			return this.actualizarVehiculoDesdeVehiculoDto(vehiculoDto);
			
		} else {
			
			logger.debug("Se va a crear un Vehiculo con usuario={}", user.getId() );
			
			return this.crearVehiculoDesdeVehiculoDto(vehiculoDto);
		}
	}
	
	private AccionRespuesta devolverDatosVehiculoDto(VehiculoDto vehiculoDto, Vehiculo vehiculoSave) {
		
		AccionRespuesta respuesta = new AccionRespuesta();
		
		//Guardado el cliente se devuelve en su dto
		if(vehiculoSave != null && vehiculoSave.getId() != null) {
			
			vehiculoDto.setId(vehiculoSave.getId());
			
			respuesta.setId(vehiculoSave.getId());
			
			respuesta.setCodigo("OK");
						
			respuesta.setResultado(Boolean.TRUE);
			
			HashMap<String, Object> data= new HashMap<String, Object> ();
			
			data.put("vehiculoDto", vehiculoDto);
			
			respuesta.setData(data);
			
		}else {
			
			respuesta.setCodigo("NOK");
						
			respuesta.setResultado(Boolean.FALSE);
			
			HashMap<String, Object> data= new HashMap<String, Object> ();
			
			data.put("vehiculoDto", vehiculoDto);
			
			respuesta.setData(data);
		}
		
		return respuesta;
	}
	
	private List<VehiculoDto> obtieneListadoVehiculoDtoDelRepository(List<Vehiculo> vehiculos){
		
		List<VehiculoDto> vehiculosDto = new ArrayList<VehiculoDto>();
		
		if(CollectionUtils.isNotEmpty(vehiculos) ) {
			
			for(Vehiculo vehiculo  : vehiculos) {
				
				VehiculoDto vehiculoDto = new VehiculoDto();
				
				vehiculoDto.setId(vehiculo.getId());
				vehiculoDto.setCodigo(vehiculo.getCodigo());
				vehiculoDto.setMatricula(vehiculo.getMatricula());
				vehiculoDto.setMarca(vehiculo.getMarca());
				vehiculoDto.setModelo(vehiculo.getModelo());
				vehiculoDto.setTipoVehiculo(vehiculo.getTipoVehiculo());
				vehiculoDto.setTipoCombustible(vehiculo.getTipoCombustible());
				vehiculoDto.setFechaMatriculacion(vehiculo.getFechaMatriculacion());
				
				vehiculosDto.add(vehiculoDto);				
			}
		}
		
		return vehiculosDto;
	}
}
