package com.erpweb.servicios.recursoshumanos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpweb.dto.NominaDto;
import com.erpweb.entidades.empresa.Empresa;
import com.erpweb.entidades.recursoshumanos.Empleado;
import com.erpweb.entidades.recursoshumanos.Nomina;
import com.erpweb.repositorios.empresa.EmpresaRepository;
import com.erpweb.repositorios.recursoshumanos.EmpleadoRepository;
import com.erpweb.repositorios.recursoshumanos.NominaRepository;
import com.erpweb.utiles.AccionRespuesta;

@Service
public class NominaService {

	@Autowired
	private EmpleadoRepository empleadoRepository;
	
	@Autowired
	private NominaRepository nominaRepository;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	public AccionRespuesta crearNominaDesdeNominaDto(NominaDto nominaDto) {
		
		logger.debug("Entramos en el metodo crearNominaDesdeNominaDto() con la empresa={}", nominaDto.getEmpresaId() );
		
		Nomina nomina = new Nomina();

		if(nominaDto.getEmpresaId() == null) {
			
			return new AccionRespuesta();
		}
		
		Empresa empresa = empresaRepository.findById(nominaDto.getEmpresaId()).orElse(new Empresa());
		
		nomina.setCodigo(nominaDto.getCodigo());
		nomina.setEmpresa(empresa);
		nomina.setDescripcion(nominaDto.getDescripcion());
		nomina.setSueldo(nominaDto.getSueldo());
		nomina.setExtras(nominaDto.getExtras());
		nomina.setFechaNomina(nominaDto.getFechaNomina());
		
		//Recuperamos el empleado
		Empleado empleado = empleadoRepository.findByIdAndEmpresaId(nominaDto.getId(), nominaDto.getEmpresaId());
		
		nomina.setEmpleado(empleado);
		
		try {
			//Guardamos el gasto en base de datos
			nominaRepository.save(nomina);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo crearNominaDesdeNominaDto() con la empresa{} ", nominaDto.getEmpresaId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public AccionRespuesta actualizarNominaDesdeNominaDto(NominaDto nominaDto) {
		
		logger.debug("Entramos en el metodo actualizarNominaDesdeNominaDto() con la empresa={}", nominaDto.getEmpresaId() );
		
		Nomina nomina = new Nomina();

		if(nominaDto.getEmpresaId() == null) {
			
			return new AccionRespuesta();
		}
		
		Empresa empresa = empresaRepository.findById(nominaDto.getEmpresaId()).orElse(new Empresa());
		
		nomina.setId(nominaDto.getId());
		nomina.setCodigo(nominaDto.getCodigo());
		nomina.setEmpresa(empresa);
		nomina.setDescripcion(nominaDto.getDescripcion());
		nomina.setSueldo(nominaDto.getSueldo());
		nomina.setExtras(nominaDto.getExtras());
		nomina.setFechaNomina(nominaDto.getFechaNomina());
		
		//Recuperamos el empleado
		Empleado empleado = empleadoRepository.findByIdAndEmpresaId(nominaDto.getId(), nominaDto.getEmpresaId());
		
		nomina.setEmpleado(empleado);
		
		try {
			//Guardamos el gasto en base de datos
			nominaRepository.save(nomina);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo actualizarNominaDesdeNominaDto() con la empresa{} ", nominaDto.getEmpresaId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public AccionRespuesta eliminarNomina(Nomina nomina) {
		
		logger.debug("Entramos en el metodo eliminarNomina() con la empresa={}", nomina.getEmpresa().getId() );
		
		if(nomina == null || nomina.getId() == null) {
			
			return new AccionRespuesta();
		}
		
		try {
			//Elimnamos la nomina
			nominaRepository.deleteById(nomina.getId());
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo eliminarNomina() con la empresa{} ", nomina.getEmpresa().getId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public NominaDto obtenerNominaDtoDesdeNomina(Long id, Long empresaId) {
		
		logger.debug("Entramos en el metodo obtenerNominaDtoDesdeNomina() con la empresa={}", empresaId );

		Nomina nomina = nominaRepository.findByIdAndEmpresaId(id, empresaId);
		
		if(nomina == null) {
			return new NominaDto();
		}
		
		NominaDto nominaDto = new NominaDto();
		
		nominaDto.setId(nomina.getId());
		nominaDto.setCodigo(nomina.getCodigo());
		nominaDto.setEmpresaId(nomina.getEmpresa().getId());
		nominaDto.setDescripcion(nomina.getDescripcion());
		nominaDto.setSueldo(nomina.getSueldo());
		nominaDto.setExtras(nomina.getExtras());
		nominaDto.setFechaNomina(nomina.getFechaNomina());
		
		//Rellenamos los datos del empleado
		if(nomina.getEmpleado() != null) {
			nominaDto.setEmpleadoId(nomina.getEmpleado().getId());
			nominaDto.setCodigoEmpleado(nomina.getEmpleado().getCodigo());
			nominaDto.setNombreEmpleado(nomina.getEmpleado().getNombre());	
			nominaDto.setApellidoPrimeroEmpleado(nomina.getEmpleado().getApellidoPrimero());
			nominaDto.setApellidoSegundoEmpleado(nomina.getEmpleado().getApellidoSegundo());
			nominaDto.setNifEmpleado(nomina.getEmpleado().getNif());
		}
		
		logger.error("Error en el metodo crearGastoDesdeGastoDto() con la empresa{} ", empresaId );
		
		return nominaDto;
	}

}
