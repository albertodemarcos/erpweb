package com.erpweb.validadores.bi;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.erpweb.dto.GastoDto;

public class GastoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return GastoDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		GastoDto gastoDto = (GastoDto) target;
	}

}
