package com.erpweb.servicios.recursoshumanos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpweb.dto.EmpleadoDto;
import com.erpweb.entidades.comun.DireccionPostal;
import com.erpweb.entidades.comun.Poblacion;
import com.erpweb.entidades.comun.Provincia;
import com.erpweb.entidades.empresa.Empresa;
import com.erpweb.entidades.recursoshumanos.Empleado;
import com.erpweb.repositorios.comun.DireccionPostalRepository;
import com.erpweb.repositorios.comun.PoblacionRepository;
import com.erpweb.repositorios.comun.ProvinciaRepository;
import com.erpweb.repositorios.empresa.EmpresaRepository;
import com.erpweb.repositorios.recursoshumanos.EmpleadoRepository;
import com.erpweb.servicios.recursoshumanos.interfaces.EmpleadoServiceInterfaz;



@Service
public class EmpleadoService implements EmpleadoServiceInterfaz {

	@Autowired
	private EmpleadoRepository empleadoRepository;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private DireccionPostalRepository direccionPostalRepository;
	
	@Autowired
	private ProvinciaRepository provinciaRepository;
	
	@Autowired
	private PoblacionRepository poblacionRepository;
	
	
	@Override
	public Boolean creaEmpleadoDesdeEmpleadoDto(EmpleadoDto empleadoDto) {

		Empleado empleado = new Empleado();
		
		if(empleadoDto == null) {
			return Boolean.FALSE;
		}
		
		Empresa empresa = empresaRepository.findById(empleadoDto.getEmpresaId()).orElse(new Empresa());
		
		empleado.setCodigo(empleadoDto.getCodigo());
		empleado.setEmpresa(empresa);
		empleado.setNombre(empleadoDto.getNombre());
		empleado.setApellidoPrimero(empleadoDto.getApellidoPrimero());
		empleado.setApellidoSegundo(empleadoDto.getApellidoSegundo());
		empleado.setNif(empleadoDto.getNif());
		
		//Buscamos la provinicia
		Provincia provincia = provinciaRepository.findById(empleadoDto.getProvinciaId()).orElse(new Provincia());
		
		//Buscamos la poblacion
		Poblacion poblacion = poblacionRepository.findById(empleadoDto.getPoblacionId()).orElse(new Poblacion());
		
		//Rellenamos la direccion postal
		DireccionPostal direccionPostal = new DireccionPostal();
		
		direccionPostal.setCodigo(empleadoDto.getCodigoDireccionPostal());
		direccionPostal.setCodigoPostal(empleadoDto.getCodigoPostal());
		direccionPostal.setDireccion(empleadoDto.getDireccion());
		direccionPostal.setEdificio(empleadoDto.getEdificio());
		direccionPostal.setObservaciones(empleadoDto.getObservaciones());
		direccionPostal.setTelefono(empleadoDto.getTelefono());
		
		direccionPostal.setProvincia(provincia);
		direccionPostal.setPoblacion(poblacion);
		
		try {
			//Guardamos la direccion postal del empleado
			direccionPostalRepository.save(direccionPostal);
			
		}catch(Exception e) {
			//Error, el empleado se queda sin direccion postal por un error
			//pero permitimos que continue la ejecucion
			System.out.println("Error: " + e.getLocalizedMessage() );
			empleado.setDireccionPostal(null);
		}
	
		try {
			//Guardamos el empleado
			empleadoRepository.save(empleado);
			
		}catch(Exception e) {
			
			System.out.println("Error: " + e.getLocalizedMessage() );
			
			return Boolean.FALSE;
		}
		
		return Boolean.TRUE;
	}

	@Override
	public EmpleadoDto obtieneEmpleadoDto(Long id, Long empresaId) {

		Empleado empleado = empleadoRepository.findByIdAndEmpresaId(id, empresaId);
		
		//Rellenamos la direccion postal
		DireccionPostal direccionPostal = direccionPostalRepository.findByIdAndEmpresaId(id, empresaId);
		
		//Buscamos la provinicia
		Provincia provincia = direccionPostal.getProvincia() != null ? direccionPostal.getProvincia() : new Provincia();
		
		//Buscamos la poblacion
		Poblacion poblacion = direccionPostal.getPoblacion()!= null ? direccionPostal.getPoblacion() : new Poblacion();
		
		if(empleado == null) {
			return new EmpleadoDto();
		}
		
		EmpleadoDto empleadoDto = new EmpleadoDto();
		
		empleadoDto.setId(empleado.getId());
		empleadoDto.setCodigo(empleado.getCodigo());
		empleadoDto.setEmpresaId(empleado.getEmpresa().getId());
		empleadoDto.setNombre(empleado.getNombre());
		empleadoDto.setApellidoPrimero(empleado.getApellidoPrimero());
		empleadoDto.setApellidoSegundo(empleado.getApellidoSegundo());
		empleadoDto.setNif(empleado.getNif());
		
		empleadoDto.setDireccionPostalId(direccionPostal.getId());
		empleadoDto.setCodigoDireccionPostal(direccionPostal.getCodigo());
		empleadoDto.setCodigoPostal(direccionPostal.getCodigoPostal());
		empleadoDto.setDireccion(direccionPostal.getDireccion());
		empleadoDto.setEdificio(direccionPostal.getEdificio());
		empleadoDto.setTelefono(direccionPostal.getTelefono());
		empleadoDto.setObservaciones(direccionPostal.getObservaciones());
		
		empleadoDto.setPoblacionId(provincia.getId() !=null ? provincia.getId() : 0L );
		empleadoDto.setNombrePoblacion(provincia.getNombre() != null ? provincia.getNombre() : "SIN_PROVINCIA");
		
		empleadoDto.setProvinciaId(poblacion.getId() !=null ? poblacion.getId() : 0L);
		empleadoDto.setNombreProvincia(poblacion.getNombre() !=null ? poblacion.getNombre() : "SIN_POBLACION");
		
		return empleadoDto;
	}

	@Override
	public Boolean actualizaEmpleado(EmpleadoDto empleadoDto) {
		
		Empleado empleado = new Empleado();
		
		if(empleadoDto == null) {
			return Boolean.FALSE;
		}
		
		Empresa empresa = empresaRepository.findById(empleadoDto.getEmpresaId()).orElse(new Empresa());
		
		empleado.setId(empleadoDto.getId());
		empleado.setCodigo(empleadoDto.getCodigo());
		empleado.setEmpresa(empresa);
		empleado.setNombre(empleadoDto.getNombre());
		empleado.setApellidoPrimero(empleadoDto.getApellidoPrimero());
		empleado.setApellidoSegundo(empleadoDto.getApellidoSegundo());
		empleado.setNif(empleadoDto.getNif());
		
		//Buscamos la provinicia
		Provincia provincia = provinciaRepository.findById(empleadoDto.getProvinciaId()).orElse(new Provincia());
		
		//Buscamos la poblacion
		Poblacion poblacion = poblacionRepository.findById(empleadoDto.getPoblacionId()).orElse(new Poblacion());
		
		//Rellenamos la direccion postal
		DireccionPostal direccionPostal = new DireccionPostal();
		
		direccionPostal.setCodigo(empleadoDto.getCodigoDireccionPostal());
		direccionPostal.setCodigoPostal(empleadoDto.getCodigoPostal());
		direccionPostal.setDireccion(empleadoDto.getDireccion());
		direccionPostal.setEdificio(empleadoDto.getEdificio());
		direccionPostal.setObservaciones(empleadoDto.getObservaciones());
		direccionPostal.setTelefono(empleadoDto.getTelefono());
		
		direccionPostal.setProvincia(provincia);
		direccionPostal.setPoblacion(poblacion);
		
		try {
			//Guardamos la direccion postal del empleado
			direccionPostalRepository.save(direccionPostal);
			
		}catch(Exception e) {
			//Error, el empleado se queda sin direccion postal por un error
			//pero permitimos que continue la ejecucion
			System.out.println("Error: " + e.getLocalizedMessage() );
			empleado.setDireccionPostal(null);
		}
	
		try {
			//Guardamos el empleado
			empleadoRepository.save(empleado);
			
		}catch(Exception e) {
			
			System.out.println("Error: " + e.getLocalizedMessage() );
			
			return Boolean.FALSE;
		}
		
		return Boolean.TRUE;
	}

	@Override
	public Boolean eliminaEmpleado(Empleado empleado) {
		
		if(empleado == null || empleado.getId() == null) {
			return Boolean.FALSE;
		}
		
		DireccionPostal direccionPostal = empleado.getDireccionPostal();
		
		if(direccionPostal == null) {
			
			System.out.println("Error al eliminar la direccion postal del empleado: " + empleado.getId() );
			
			return Boolean.FALSE;
		}
		
		try {
			//Elimnamos la direccion postal primero
			direccionPostalRepository.deleteById(direccionPostal.getId());
			
		}catch(Exception e) {
			
			System.out.println("Error al eliminar la direccion postal de un empleado: " + e.getLocalizedMessage());
			
			return Boolean.FALSE;
		}
		
		try {
			
			//Eliminamos el empleado
			empleadoRepository.deleteById(empleado.getId());
			
		}catch(Exception e) {
			
			System.out.println("Error al eliminar el empleado: " + e.getLocalizedMessage());
			
			return Boolean.FALSE;
		}
		
		return Boolean.TRUE;
	}

	@Override
	public Empleado obtieneEmpleado(Long id, Long empresaId) {

		Empleado empleado;
		
		try {
			//Recuperamos el gasto
			empleado = empleadoRepository.findByIdAndEmpresaId(id, empresaId);
			
		}catch(Exception e) {
			
			System.out.println("Error al recuperar el empleado con ID: " + id + "de la empresa: " + empresaId + " con error localizado: " + e.getLocalizedMessage());
			
			return new Empleado();
		}
		
		return empleado;
	}

}
