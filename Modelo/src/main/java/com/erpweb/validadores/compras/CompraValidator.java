package com.erpweb.validadores.compras;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.erpweb.dto.CompraDto;

public class CompraValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return CompraDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		CompraDto compraDto = (CompraDto) target;
	}

}
