package com.erpweb.validadores.recursoshumanos;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.erpweb.dto.EmpleadoDto;

public class EmpleadoValidator implements Validator {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return EmpleadoDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		logger.debug("Se evaluan los datos del dto={} ", EmpleadoDto.class );
		
		EmpleadoDto empleadoDto = (EmpleadoDto) target;
		
		
		if( empleadoDto.getEmpresaId() == null || empleadoDto.getEmpresaId().intValue() < 1 ) {
			
			errors.reject("ERROR_EMPRESA", "El empleado no esta asociado a una empresa");
		}
		
		if( StringUtils.isBlank( empleadoDto.getCodigo() )  ) {
			
			errors.rejectValue("", "", "El campo codigo no puede estar vacío");
		}
		
		if( StringUtils.isBlank( empleadoDto.getNombre() )  ) {
			
			errors.rejectValue("", "", "El campo nombre no puede estar vacío");
		}
		
		if( StringUtils.isBlank( empleadoDto.getApellidoPrimero() )  ) {
			
			errors.rejectValue("", "", "El campo apellido no puede estar vacío");
		}
		
		if( StringUtils.isBlank( empleadoDto.getApellidoSegundo() )  ) {
				
			errors.rejectValue("", "", "El campo segundo apellido no puede estar vacío");
		}

		if( StringUtils.isBlank( empleadoDto.getNif() )  ) {
			
			errors.rejectValue("", "", "El campo DNI no puede estar vacío");
		}
		
		if( StringUtils.isBlank( empleadoDto.getCodigoDireccionPostal() )  ) {
			
			errors.rejectValue("", "", "El campo codigo de la dirección postal no puede estar vacío");
		}
		
		if( StringUtils.isBlank( empleadoDto.getCodigoPostal() )  ) {
			
			errors.rejectValue("", "", "El campo codigo postal no puede estar vacío");
		}
		
		if( StringUtils.isBlank( empleadoDto.getDireccion() )  ) {
			
			errors.rejectValue("", "", "La campo direccion no puede estar vacío");
		}
		
		if( StringUtils.isBlank( empleadoDto.getEdificio() )  ) {
			
			errors.rejectValue("", "", "El campo edificio no puede estar vacío");
		}
		
		if( StringUtils.isBlank( empleadoDto.getObservaciones() )  ) {
			
			errors.rejectValue("", "", "El campo observacíones no puede estar vacío");
		}
		
		if( StringUtils.isBlank( empleadoDto.getTelefono() )  ) {
			
			errors.rejectValue("", "", "El campo telefono no puede estar vacío");
		}
		
		if( empleadoDto.getProvinciaId() == null  ) {
			
			errors.rejectValue("", "", "Debe elegir provincia");
		}
		
		if( empleadoDto.getPoblacionId() == null  ) {
			
			errors.rejectValue("", "", "Debe elegir poblacion");
		}
		
		
	}

}
