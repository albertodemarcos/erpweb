package com.erpweb.validadores.bi;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.erpweb.dto.GastoDto;

public class GastoValidator implements Validator {
	
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return GastoDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		logger.debug("Se evaluan los datos del dto={} ", GastoDto.class );
		
		GastoDto gastoDto = (GastoDto) target;
		
		if( gastoDto.getEmpresaId() == null || gastoDto.getEmpresaId().intValue() < 1 ) {
			
			errors.reject("ERROR_EMPRESA", "El gasto no esta asociado a una empresa");
		}
		
		if( StringUtils.isBlank( gastoDto.getCodigo() )  ) {
			
			errors.rejectValue("", "", "El campo codigo no puede estar vacío");
		}
		
		if( StringUtils.isBlank( gastoDto.getProcedencia() )  ) {
			
			errors.rejectValue("", "", "El campo procedencia no puede estar vacío");
		}
		
		if( StringUtils.isBlank( gastoDto.getDescripcion() )  ) {
			
			errors.rejectValue("", "", "El campo descripción no puede estar vacío");
		}
		
		if( StringUtils.isBlank( gastoDto.getObservaciones() )  ) {
			
			errors.rejectValue("", "", "El campo observacíones no puede estar vacío");
		}
		
		if( gastoDto.getBaseImponible() == null ) {
			
			errors.rejectValue("", "", "El campo base imponible no puede estar vacío");
			
		} else if( gastoDto.getBaseImponible().doubleValue() < 0 ) {
			
			errors.rejectValue("", "", "El campo base imponible no puede ser negativo");
			
		} else if( gastoDto.getBaseImponible().intValue() == 0) {
			
			errors.rejectValue("", "", "El campo base imponible no puede ser cero");
		}
		
		if( gastoDto.getCuotaTributaria() == null ) {
			
			errors.rejectValue("", "", "El campo cuota tributaria no puede estar vacío");
			
		} else if( gastoDto.getCuotaTributaria().intValue() < 0) {
			
			errors.rejectValue("", "", "El campo cuota tributaria no puede ser negativo");
		}
		
		if( gastoDto.getImporteTotal() == null ) {
			
			errors.rejectValue("", "", "El campo importe total no puede estar vacío");
			
		} else if( gastoDto.getImporteTotal().intValue() < 0) {
			
			errors.rejectValue("", "", "El campo importe total no puede ser negativo");
		}
		
	}

}
