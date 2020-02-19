package com.erpweb.validadores.ventas;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.erpweb.dto.ContratoDto;

public class ContratoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return ContratoDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		ContratoDto contratoDto = (ContratoDto) target;
		System.out.println(contratoDto.toString());
	}

}
