package com.erpweb.validadores.empresa;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.erpweb.dto.EmpresaDto;

public class EmpresaValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return EmpresaDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		EmpresaDto empresaDto = (EmpresaDto) target;
	}

}
