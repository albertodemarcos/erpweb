package com.erpweb.validadores.empresa;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.erpweb.dto.ConfiguracionDto;

public class ConfiguracionValidator implements Validator {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return ConfiguracionDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		logger.debug("Se evaluan los datos del dto={} ", ConfiguracionDto.class );
		
		ConfiguracionDto configuracionDto = (ConfiguracionDto) target;
		

		if( configuracionDto.getEmpresaId() == null || configuracionDto.getEmpresaId().intValue() < 1 ) {
			
			errors.reject("ERROR_EMPRESA", "La configuración de la empresa no esta asociado a una empresa");
		}
		
		if( StringUtils.isBlank( configuracionDto.getCodigo() )  ) {
			
			errors.rejectValue("", "", "El campo codigo no puede estar vacio");
		}
		
		if( StringUtils.isBlank( configuracionDto.getIdiomaApp() )  ) {
			
			errors.rejectValue("", "", "El idioma de la apliacion no puede estar vacio");
		}
		
		
	}

}
