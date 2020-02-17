package com.erpweb.validadores.ventas;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.erpweb.dto.VentaDto;

public class VentaValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return VentaDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		VentaDto ventaDto = (VentaDto) target;
	}

}
