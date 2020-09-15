package com.erpweb.validadores.ventas;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.erpweb.dto.VentaDto;

@Component
public class VentaValidator implements Validator {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return VentaDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		logger.debug("Se evaluan los datos del dto={} ", VentaDto.class );
		
		VentaDto ventaDto = (VentaDto) target;
		
		if( StringUtils.isBlank( ventaDto.getCodigo() )  ) {
			
			errors.rejectValue("codigo", "", "El campo codigo no puede estar vacío");
		}
		
		if( ventaDto.getFechaInicio() == null  ) {
			
			errors.rejectValue("fechaInicio", "", "El campo fecha de inicio no puede estar vacío");
			
		} else if( (ventaDto.getFechaInicio() != null && ventaDto.getFechaFin() != null ) 
						&& ventaDto.getFechaInicio().after( ventaDto.getFechaFin() )  ) {
			
			errors.rejectValue("fechaInicio", "", "El campo fecha de inicio no puede ser superior al campo fecha de fin");
		}
			
	}

}
