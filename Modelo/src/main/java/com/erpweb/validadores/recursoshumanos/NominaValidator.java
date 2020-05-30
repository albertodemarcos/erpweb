package com.erpweb.validadores.recursoshumanos;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.erpweb.dto.NominaDto;

public class NominaValidator implements Validator {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return NominaDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		logger.debug("Se evaluan los datos del dto={} ", NominaDto.class );
		
		NominaDto nominaDto = (NominaDto) target;
		
		if( nominaDto.getEmpresaId() == null || nominaDto.getEmpresaId().intValue() < 1 ) {
			
			errors.reject("ERROR_EMPRESA", "La nómina no esta asociado a una empresa");
		}
		
		if( StringUtils.isBlank( nominaDto.getCodigo() )  ) {
			
			errors.rejectValue("", "", "El campo codigo no puede estar vacío");
		}
		
		if( StringUtils.isBlank( nominaDto.getDescripcion() )  ) {
			
			errors.rejectValue("", "", "El campo descripción no puede estar vacío");
		}
		
		if( nominaDto.getSueldo() == null ) {
			
			errors.rejectValue("", "", "El campo sueldo no puede estar vacío");
			
		}else if( nominaDto.getSueldo().doubleValue() == 0 ) {
			
			errors.rejectValue("", "", "El campo sueldo no puede ser cero");
			
		}else if( nominaDto.getSueldo().doubleValue() < 0 ) {
			
			errors.rejectValue("", "", "El campo sueldo no puede ser negativo");
		}
		
		if( nominaDto.getExtras() != null && nominaDto.getExtras().doubleValue() == 0 ) {
			
			errors.rejectValue("", "", "El campo extras no puede ser cero");
			
		}else if( nominaDto.getExtras() != null && nominaDto.getExtras().doubleValue() < 0 ) {
			
			errors.rejectValue("", "", "El campo extras no puede ser negativo");
		}
		
		if( nominaDto.getFechaNomina() == null ) {
			
			errors.rejectValue("", "", "El campo fecha no puede estar vacío");
		}
		
		if( nominaDto.getEmpleadoId() == null  ) {
			
			errors.rejectValue("", "", "Debes elegir el empleado de la nomina");
		}
		
	}

}
