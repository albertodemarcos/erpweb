package com.erpweb.validadores.usuarios;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.erpweb.dto.UsuarioDto;

@Component
public class UsuarioValidator implements Validator {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return UsuarioDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		logger.debug("Se evaluan los datos del dto={} ", UsuarioDto.class );
		
		UsuarioDto usuarioDto = (UsuarioDto) target;

		if( StringUtils.isBlank( usuarioDto.getCodigo() )  ) {
			
			errors.rejectValue("codigo", "", "El campo codigo no puede estar vacío");
		}
		
		if( StringUtils.isBlank( usuarioDto.getUsername() ) ) {
			
			errors.rejectValue("usuario", "", "El campo usuario no puede estar vacío");
		}
		
		if( StringUtils.isBlank( usuarioDto.getPassword() )  ) {
			
			errors.rejectValue("pass", "", "El campo contraseña no puede estar vacío");
		}
		
		if( StringUtils.isBlank( usuarioDto.getNombreCompleto() )  ) {
			
			errors.rejectValue("nombreCompleto", "", "El campo contraseña no puede estar vacío");
		}
		
	}

}
