package com.erpweb.servicios.recursoshumanos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpweb.dto.NominaDto;
import com.erpweb.entidades.empresa.Empresa;
import com.erpweb.entidades.recursoshumanos.Empleado;
import com.erpweb.entidades.recursoshumanos.Nomina;
import com.erpweb.repositorios.empresa.EmpresaRepository;
import com.erpweb.repositorios.recursoshumanos.EmpleadoRepository;
import com.erpweb.repositorios.recursoshumanos.NominaRepository;
import com.erpweb.servicios.recursoshumanos.interfaces.NominaServiceInterfaz;

@Service
public class NominaService implements NominaServiceInterfaz {

	@Autowired
	private EmpleadoRepository empleadoRepository;
	
	@Autowired
	private NominaRepository nominaRepository;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Override
	public Boolean creaNominaDesdeNominaDto(NominaDto nominaDto) {
		
		Nomina nomina = new Nomina();

		if(nominaDto.getEmpresaId() == null) {
			return Boolean.FALSE;
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
			
			System.out.println("Error: " + e.getLocalizedMessage() );
			
			return Boolean.FALSE;
		}
		
		return Boolean.TRUE;
	}

	@Override
	public NominaDto obtieneNominaDto(Long id, Long empresaId) {

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
		
		return nominaDto;
	}

	@Override
	public Boolean actualizaNomina(NominaDto nominaDto) {
		
		Nomina nomina = new Nomina();

		if(nominaDto.getEmpresaId() == null) {
			return Boolean.FALSE;
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
			
			System.out.println("Error: " + e.getLocalizedMessage() );
			
			return Boolean.FALSE;
		}
		
		return Boolean.TRUE;
	}

	@Override
	public Boolean eliminaNomina(Nomina nomina) {
		
		if(nomina == null || nomina.getId() == null) {
			return Boolean.FALSE;
		}
		
		try {
			//Elimnamos la nomina
			nominaRepository.deleteById(nomina.getId());
			
		}catch(Exception e) {
			
			System.out.println("Error: " + e.getLocalizedMessage());
			
			return Boolean.FALSE;
		}
		
		return Boolean.TRUE;
	}

	@Override
	public Nomina obtieneNomina(Long id, Long empresaId) {

		Nomina nomina;
		
		try {
			//Recuperamos el gasto
			nomina = nominaRepository.findByIdAndEmpresaId(id, empresaId);
			
		}catch(Exception e) {
			
			System.out.println("Error: " + e.getLocalizedMessage());
			
			return new Nomina();
		}
		
		return nomina;
	}


}
