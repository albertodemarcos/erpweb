package com.erpweb.validadores.bi;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.erpweb.dto.IngresoDto;

public class IngresoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return IngresoDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		IngresoDto ingresoDto = (IngresoDto) target;
	}

}
