package com.erpweb.validadores.usuarios;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.erpweb.dto.UsuarioDto;

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

		if( usuarioDto.getEmpresaId() == null || usuarioDto.getEmpresaId().intValue() < 1 ) {
			
			errors.reject("ERROR_EMPRESA", "El usuario no esta asociado a una empresa");
		}
		
		if( StringUtils.isBlank( usuarioDto.getCodigo() )  ) {
			
			errors.rejectValue("", "", "El campo codigo no puede estar vacío");
		}
		
		if( StringUtils.isBlank( usuarioDto.getName() )  ) {
			
			errors.rejectValue("", "", "El campo usuario no puede estar vacío");
		}
		
		if( StringUtils.isBlank( usuarioDto.getPassword() )  ) {
			
			errors.rejectValue("", "", "El campo contraseña no puede estar vacío");
		}
		
		if( StringUtils.isBlank( usuarioDto.getIdentidad() )  ) {
			
			errors.rejectValue("", "", "El campo nombre de usuario no puede estar vacío");
		}
		
		if( StringUtils.isBlank( usuarioDto.getIdioma() )  ) {
			
			errors.rejectValue("", "", "El campo idiom no puede estar vacío");
		}
		
		
	}

}
