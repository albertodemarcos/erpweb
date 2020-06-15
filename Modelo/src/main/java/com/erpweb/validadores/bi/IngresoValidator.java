package com.erpweb.validadores.bi;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.erpweb.dto.IngresoDto;

@Component
public class IngresoValidator implements Validator {
	
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return IngresoDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		logger.debug("Se evaluan los datos del dto={} ", IngresoDto.class );
		
		IngresoDto ingresoDto = (IngresoDto) target;
		
		if( StringUtils.isBlank( ingresoDto.getCodigo() )  ) {
			
			errors.rejectValue("", "", "El campo codigo no puede estar vacio");
		}
		
		if( StringUtils.isBlank( ingresoDto.getProcedencia() )  ) {
			
			errors.rejectValue("", "", "El campo procedencia no puede estar vacio");
		}
		
		if( StringUtils.isBlank( ingresoDto.getDescripcion() )  ) {
			
			errors.rejectValue("", "", "El campo descripción no puede estar vacio");
		}
		
		if( StringUtils.isBlank( ingresoDto.getObservaciones() )  ) {
			
			errors.rejectValue("", "", "El campo observaciones no puede estar vacio");
		}
		
		if( ingresoDto.getBaseImponible() == null ) {
			
			errors.rejectValue("", "", "El campo base imponible no puede estar vacio");
			
		} else if( ingresoDto.getBaseImponible().doubleValue() < 0 ) {
			
			errors.rejectValue("", "", "El campo base imponible no puede ser negativo");
			
		} else if( ingresoDto.getBaseImponible().intValue() == 0) {
			
			errors.rejectValue("", "", "El campo base imponible no puede ser cero");
		}
		
		if( ingresoDto.getCuotaTributaria() == null ) {
			
			errors.rejectValue("", "", "El campo cuota tributaria no puede estar vacio");
			
		} else if( ingresoDto.getCuotaTributaria().doubleValue() < 0) {
			
			errors.rejectValue("", "", "El campo cuota tributaria no puede ser negativo");
		}
		
		if( ingresoDto.getImporteTotal() == null ) {
			
			errors.rejectValue("", "", "El campo importe total no puede estar vacio");
			
		} else if( ingresoDto.getImporteTotal().doubleValue() < 0) {
			
			errors.rejectValue("", "", "El campo importe total no puede ser negativo");
		}
		
	}

}
