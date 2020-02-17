package com.erpweb.validadores.usuarios;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.erpweb.dto.UsuarioDto;

public class UsuarioValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return UsuarioDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		UsuarioDto usuarioDto = (UsuarioDto) target;
	}

}
