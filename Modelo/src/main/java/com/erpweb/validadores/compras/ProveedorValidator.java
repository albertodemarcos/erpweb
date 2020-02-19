package com.erpweb.validadores.compras;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.erpweb.dto.ProveedorDto;

public class ProveedorValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return ProveedorDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		ProveedorDto proveedorDto = (ProveedorDto) target;
		System.out.println(proveedorDto.toString());
	}

}
