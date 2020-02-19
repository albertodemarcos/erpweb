package com.erpweb.validadores.crm;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.erpweb.dto.ClienteDto;

public class ClienteValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return ClienteDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		ClienteDto clienteDto = (ClienteDto) target;
		System.out.println(clienteDto.toString());
	}

}
