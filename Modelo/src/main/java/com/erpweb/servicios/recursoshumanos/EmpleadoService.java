package com.erpweb.servicios.recursoshumanos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.erpweb.utiles.AccionRespuesta;



@Service
public class EmpleadoService {

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
	
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	public AccionRespuesta crearEmpleadoDesdeEmpleadoDto(EmpleadoDto empleadoDto) {
		
		logger.debug("Entramos en el metodo crearGastoDesdeGastoDto() con la empresa={}", empleadoDto.getEmpresaId() );
		
		Empleado empleado = new Empleado();
		
		if(empleadoDto == null || empleadoDto.getEmpresaId() == null ) {
			
			return new AccionRespuesta();
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
			logger.error("Error en el metodo crearGastoDesdeGastoDto() con la empresa{} al guardar la direccion postal", empleadoDto.getEmpresaId() );
			
			e.printStackTrace();
			
			empleado.setDireccionPostal(null);
		}
	
		try {
			//Guardamos el empleado
			empleadoRepository.save(empleado);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo crearGastoDesdeGastoDto() con la empresa{} ", empleadoDto.getEmpresaId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public AccionRespuesta actualizarEmpleadoDesdeEmpleadoDto(EmpleadoDto empleadoDto) {
		
		logger.debug("Entramos en el metodo crearGastoDesdeGastoDto() con la empresa={}", empleadoDto.getEmpresaId() );
		
		Empleado empleado = new Empleado();
		
		if(empleadoDto == null || empleadoDto.getEmpresaId() == null) {
			
			return new AccionRespuesta();
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
			logger.error("Error en el metodo crearGastoDesdeGastoDto() con la empresa{} al guardar la direccion postal ", empleadoDto.getEmpresaId() );
			
			e.printStackTrace();
			
			empleado.setDireccionPostal(null);
		}
	
		try {
			//Guardamos el empleado
			empleadoRepository.save(empleado);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo crearGastoDesdeGastoDto() con la empresa{} ", empleadoDto.getEmpresaId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public AccionRespuesta eliminarEmpleado(Empleado empleado) {
		
		logger.debug("Entramos en el metodo crearGastoDesdeGastoDto() con la empresa={}", empleado.getEmpresa().getId() );
		
		if(empleado == null || empleado.getId() == null) {
			
			return new AccionRespuesta();
		}
		
		DireccionPostal direccionPostal = empleado.getDireccionPostal();
		
		if(direccionPostal == null) {
			
			logger.error("Error en el metodo crearGastoDesdeGastoDto() con la empresa{} al eliminar la direccion postal del empleado={} ", empleado.getEmpresa().getCif(),  empleado.getId() );
			
			return new AccionRespuesta();
		}
		
		try {
			//Elimnamos la direccion postal primero
			direccionPostalRepository.deleteById(direccionPostal.getId());
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo crearGastoDesdeGastoDto() con la empresa{} al eliminar la direccion postal del empleado={} ", empleado.getEmpresa().getCif(),  empleado.getId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		try {
			
			//Eliminamos el empleado
			empleadoRepository.deleteById(empleado.getId());
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo crearGastoDesdeGastoDto() con la empresa={} ", empleado.getId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public EmpleadoDto obtenerEmpleadoDtoDesdeEmpleado(Long id, Long empresaId) {
		
		logger.debug("Entramos en el metodo crearGastoDesdeGastoDto() con la empresa={}", empresaId );
		
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
		
		logger.error("Error en el metodo crearGastoDesdeGastoDto() con la empresa{} ", empresaId );
		
		return empleadoDto;
	}

}
