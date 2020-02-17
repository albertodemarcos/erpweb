package com.erpweb.validadores.inventario;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.erpweb.dto.ArticuloDto;

public class ArticuloValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return ArticuloDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		ArticuloDto articuloDto = (ArticuloDto) target;
	}

}
