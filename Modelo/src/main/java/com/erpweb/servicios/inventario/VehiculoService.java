package com.erpweb.servicios.inventario;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpweb.dto.VehiculoDto;
import com.erpweb.entidades.empresa.Empresa;
import com.erpweb.entidades.inventario.Vehiculo;
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
		
		logger.debug("Entramos en el metodo crearGastoDesdeGastoDto() con la empresa={}", vehiculoDto.getEmpresaId() );
		
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
			
			logger.error("Error en el metodo crearGastoDesdeGastoDto() con la empresa{} ", vehiculoDto.getEmpresaId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public AccionRespuesta actualizarVehiculoDesdeVehiculoDto(VehiculoDto vehiculoDto) {
		
		logger.debug("Entramos en el metodo crearGastoDesdeGastoDto() con la empresa={}", vehiculoDto.getEmpresaId() );
		
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
			
			logger.error("Error en el metodo crearGastoDesdeGastoDto() con la empresa{} ", vehiculoDto.getEmpresaId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public AccionRespuesta eliminarVehiculo(Vehiculo vehiculo) {
		
		logger.debug("Entramos en el metodo crearGastoDesdeGastoDto() con la empresa={}", vehiculo.getId() );
		
		if(vehiculo == null || vehiculo.getId() == null) {
			
			return new AccionRespuesta();
		}
		
		try {
			//Elimnamos el gasto
			vehiculoRepository.deleteById(vehiculo.getId());
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo crearGastoDesdeGastoDto() con la empresa{} ", vehiculo.getId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public VehiculoDto obtenerVehiculoDtoDesdeVehiculo(Long id, Long empresaId) {
		
		logger.debug("Entramos en el metodo crearGastoDesdeGastoDto() con la empresa={}", empresaId );
		
		Vehiculo vehiculo = vehiculoRepository.findByIdAndEmpresaId(id, empresaId);
		
		if(vehiculo == null) {
			return new VehiculoDto();
		}
		
		VehiculoDto vehiculoDto = new VehiculoDto();
		
		vehiculoDto.setCodigo(vehiculo.getCodigo());
		vehiculoDto.setEmpresaId(vehiculo.getEmpresa().getId());
		vehiculoDto.setMatricula(vehiculo.getMatricula());
		vehiculoDto.setMarca(vehiculo.getMarca());
		vehiculoDto.setModelo(vehiculo.getModelo());
		vehiculoDto.setTipoVehiculo(vehiculo.getTipoVehiculo());
		vehiculoDto.setFechaMatriculacion(vehiculo.getFechaMatriculacion());
		
		logger.error("Error en el metodo crearGastoDesdeGastoDto() con la empresa{} ", empresaId );
		
		return vehiculoDto;
	}
	
}
