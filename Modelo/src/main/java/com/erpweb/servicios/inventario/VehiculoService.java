package com.erpweb.servicios.inventario;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpweb.dto.VehiculoDto;
import com.erpweb.entidades.empresa.Empresa;
import com.erpweb.entidades.inventario.Vehiculo;
import com.erpweb.repositorios.empresa.EmpresaRepository;
import com.erpweb.repositorios.inventario.VehiculoRepository;
import com.erpweb.servicios.inventario.interfaces.VehiculoServiceInterfaz;



@Service
public class VehiculoService implements VehiculoServiceInterfaz {

	@Autowired
	private VehiculoRepository vehiculoRepository;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Override
	public Boolean creaVehiculoDesdeVehiculoDto(VehiculoDto vehiculoDto) {
		
		Vehiculo vehiculo = new Vehiculo();

		if(vehiculoDto.getEmpresaId() == null) {
			return Boolean.FALSE;
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
			
			System.out.println("Error: " + e.getLocalizedMessage() );
			
			return Boolean.FALSE;
		}
		
		return Boolean.TRUE;
	}

	@Override
	public VehiculoDto obtieneVehiculoDto(Long id, Long empresaId) {

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
		
		return vehiculoDto;
	}

	@Override
	public Boolean actualizaVehiculo(VehiculoDto vehiculoDto) {
		
		Vehiculo vehiculo = new Vehiculo();

		if(vehiculoDto.getEmpresaId() == null) {
			return Boolean.FALSE;
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
			
			System.out.println("Error: " + e.getLocalizedMessage() );
			
			return Boolean.FALSE;
		}
		
		return Boolean.TRUE;
	}

	@Override
	public Boolean eliminaVehiculo(Vehiculo vehiculo) {
		
		if(vehiculo == null || vehiculo.getId() == null) {
			return Boolean.FALSE;
		}
		
		try {
			//Elimnamos el gasto
			vehiculoRepository.deleteById(vehiculo.getId());
			
		}catch(Exception e) {
			
			System.out.println("Error: " + e.getLocalizedMessage());
			
			return Boolean.FALSE;
		}
		
		return Boolean.TRUE;
	}

	@Override
	public Vehiculo obtieneVehiculo(Long id, Long empresaId) {
		
		Vehiculo vehiculo;
		
		try {
			//Recuperamos el vehiculo
			vehiculo = vehiculoRepository.findByIdAndEmpresaId(id, empresaId);
			
		}catch(Exception e) {
			
			System.out.println("Error: " + e.getLocalizedMessage());
			
			return new Vehiculo();
		}
		
		return vehiculo;
	}


}
