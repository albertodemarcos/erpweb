package com.erpweb.validadores.empresa;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.erpweb.dto.ConfiguracionDto;

public class ConfiguracionValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return ConfiguracionDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		ConfiguracionDto configuracionDto = (ConfiguracionDto) target;
		System.out.println(configuracionDto.toString());
	}

}
