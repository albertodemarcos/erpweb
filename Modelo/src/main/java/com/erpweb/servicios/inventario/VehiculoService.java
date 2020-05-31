package com.erpweb.servicios.inventario;


import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpweb.dto.VehiculoDto;
import com.erpweb.entidades.empresa.Empresa;
import com.erpweb.entidades.inventario.Vehiculo;
import com.erpweb.entidades.usuarios.Usuario;
import com.erpweb.repositorios.empresa.EmpresaRepository;
import com.erpweb.repositorios.inventario.VehiculoRepository;
import com.erpweb.utiles.AccionRespuesta;



@Service
public class VehiculoService {

	@Autowired
	private VehiculoRepository vehiculoRepository;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	public AccionRespuesta crearVehiculoDesdeVehiculoDto(VehiculoDto vehiculoDto) {
		
		logger.debug("Entramos en el metodo crearvehiculoDesdevehiculoDto() con la empresa={}", vehiculoDto.getEmpresaId() );
		
		Vehiculo vehiculo = new Vehiculo();

		if(vehiculoDto.getEmpresaId() == null) {
			
			return new AccionRespuesta();
		}
		
		Empresa empresa = empresaRepository.findById(vehiculoDto.getEmpresaId()).orElse(new Empresa());
		
		vehiculo.setCodigo(vehiculoDto.getCodigo());
		vehiculo.setEmpresa(empresa);
		vehiculo.setMatricula(vehiculoDto.getMatricula());
		vehiculo.setMarca(vehiculoDto.getMarca());
		vehiculo.setModelo(vehiculoDto.getModelo());
		vehiculo.setTipoVehiculo(vehiculoDto.getTipoVehiculo());
		vehiculo.setFechaMatriculacion(vehiculoDto.getFechaMatriculacion());
		
		try {
			
			//Guardamos el vehiculo en base de datos
			vehiculoRepository.save(vehiculo);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo crearvehiculoDesdevehiculoDto() con la empresa{} ", vehiculoDto.getEmpresaId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public AccionRespuesta actualizarVehiculoDesdeVehiculoDto(VehiculoDto vehiculoDto) {
		
		logger.debug("Entramos en el metodo crearvehiculoDesdevehiculoDto() con la empresa={}", vehiculoDto.getEmpresaId() );
		
		Vehiculo vehiculo = new Vehiculo();

		if(vehiculoDto.getEmpresaId() == null) {
			
			return new AccionRespuesta();
		}
		
		Empresa empresa = empresaRepository.findById(vehiculoDto.getEmpresaId()).orElse(new Empresa());
		
		vehiculo.setId(vehiculoDto.getId());
		vehiculo.setCodigo(vehiculoDto.getCodigo());
		vehiculo.setEmpresa(empresa);
		vehiculo.setMatricula(vehiculoDto.getMatricula());
		vehiculo.setMarca(vehiculoDto.getMarca());
		vehiculo.setModelo(vehiculoDto.getModelo());
		vehiculo.setTipoVehiculo(vehiculoDto.getTipoVehiculo());
		vehiculo.setFechaMatriculacion(vehiculoDto.getFechaMatriculacion());
		
		try {
			
			//Guardamos el vehiculo en base de datos
			vehiculoRepository.save(vehiculo);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo crearvehiculoDesdevehiculoDto() con la empresa{} ", vehiculoDto.getEmpresaId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public AccionRespuesta eliminarVehiculo(Vehiculo vehiculo) {
		
		logger.debug("Entramos en el metodo crearvehiculoDesdevehiculoDto() con la empresa={}", vehiculo.getEmpresa().getId() );
		
		if(vehiculo == null || vehiculo.getId() == null) {
			
			return new AccionRespuesta();
		}
		
		try {
			//Elimnamos el vehiculo
			vehiculoRepository.deleteById(vehiculo.getId());
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo crearvehiculoDesdevehiculoDto() con la empresa{} ", vehiculo.getEmpresa().getId() );
			
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
	
	public VehiculoDto obtenerVehiculoDtoDesdeVehiculo(Long id, Long empresaId) {
		
		logger.debug("Entramos en el metodo crearvehiculoDesdevehiculoDto() con la empresa={}", empresaId );
		
		Vehiculo vehiculo = vehiculoRepository.findByIdAndEmpresaId(id, empresaId);
		
		if(vehiculo == null) {
			return new VehiculoDto();
		}
		
		VehiculoDto vehiculoDto = new VehiculoDto();
		
		try {
			
			vehiculoDto.setCodigo(vehiculo.getCodigo());
			vehiculoDto.setEmpresaId(vehiculo.getEmpresa().getId());
			vehiculoDto.setMatricula(vehiculo.getMatricula());
			vehiculoDto.setMarca(vehiculo.getMarca());
			vehiculoDto.setModelo(vehiculo.getModelo());
			vehiculoDto.setTipoVehiculo(vehiculo.getTipoVehiculo());
			vehiculoDto.setFechaMatriculacion(vehiculo.getFechaMatriculacion());
			
		} catch(Exception e) {
			
			logger.error("Error en el metodo crearvehiculoDesdevehiculoDto() con la empresa{} ", empresaId );
			
			e.printStackTrace();
		}
		
		return vehiculoDto;
	}
	
	
	public AccionRespuesta getVehiculo(Long vehiculoId, Usuario user) {
		
		logger.debug("Entramos en el metodo getVehiculo()");
		
		if( vehiculoId == null) {
			
			return new AccionRespuesta(-1L, "Error, existe el vehiculo", Boolean.FALSE);
		}
		
		VehiculoDto vehiculoDto = this.obtenerVehiculoDtoDesdeVehiculo(vehiculoId, user.getEmpresa().getId());
		
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
}
