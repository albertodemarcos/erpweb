package com.erpweb.validadores.bi;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.erpweb.dto.InformeDto;

public class InformeValidator implements Validator {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return InformeDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
	
		logger.debug("Se evaluan los datos del dto={} ", InformeDto.class );
		
		
		InformeDto informeDto = (InformeDto) target;
		
		if( informeDto.getEmpresaId() == null || informeDto.getEmpresaId().intValue() < 1 ) {
			
			errors.reject("ERROR_EMPRESA", "El informe no esta asociado a una empresa");
		}
	
		
		if( StringUtils.isBlank( informeDto.getCodigo() )  ) {
			
			errors.rejectValue("", "", "El campo codigo no puede estar vacio");
		}
	
	}

}
