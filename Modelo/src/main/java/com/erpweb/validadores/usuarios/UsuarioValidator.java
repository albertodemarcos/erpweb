package com.erpweb.validadores.usuarios;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.erpweb.dto.UsuarioDto;
import com.erpweb.repositorios.usuarios.UsuarioRepository;

@Component
public class UsuarioValidator implements Validator {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
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
			
			errors.rejectValue("username", "", "El campo usuario no puede estar vacío");
			
		}else { // Comprobamos que no se introduce el mismo username
			
			Boolean existeUsuario = this.usuarioRepository.compruebaSiExiteElUsuarioConUsername(usuarioDto.getUsername());
			
			if(BooleanUtils.isTrue(existeUsuario)) {
				
				errors.rejectValue("username", "", "El usuario ya existe, ponga otro username");
			}
		}
		
		if( StringUtils.isBlank( usuarioDto.getPassword() )  ) {
			
			errors.rejectValue("password", "", "El campo contraseña no puede estar vacío");
		}
		
		if( StringUtils.isBlank( usuarioDto.getNombreCompleto() )  ) {
			
			errors.rejectValue("nombreCompleto", "", "El campo contraseña no puede estar vacío");
		}
		
		if( StringUtils.isBlank( usuarioDto.getEmail() )  ) {
			
			errors.rejectValue("email", "", "El campo email no puede estar vacío");
		}
		
		
		
	}

}
