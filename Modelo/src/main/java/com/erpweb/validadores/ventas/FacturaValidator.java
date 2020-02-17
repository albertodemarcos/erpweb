package com.erpweb.validadores.ventas;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.erpweb.dto.FacturaDto;

public class FacturaValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return FacturaDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		FacturaDto facturaDto = (FacturaDto) target;
	}

}
