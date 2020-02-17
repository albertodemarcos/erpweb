package com.erpweb.validadores.recursoshumanos;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.erpweb.dto.EmpleadoDto;

public class EmpleadoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return EmpleadoDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		EmpleadoDto empleadoDto = (EmpleadoDto) target;
	}

}
