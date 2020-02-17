package com.erpweb.validadores.recursoshumanos;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.erpweb.dto.NominaDto;

public class NominaValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return NominaDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		NominaDto nominaDto = (NominaDto) target;
	}

}
