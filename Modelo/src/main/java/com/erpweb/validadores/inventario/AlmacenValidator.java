package com.erpweb.validadores.inventario;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.erpweb.dto.AlmacenDto;

public class AlmacenValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return AlmacenDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		AlmacenDto almacenDto = (AlmacenDto) target;
		System.out.println(almacenDto.toString());
	}

}
