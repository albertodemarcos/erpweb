package com.erpweb.validadores.ventas;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.erpweb.dto.ContratoDto;

@Component
public class ContratoValidator implements Validator {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return ContratoDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		logger.debug("Se evaluan los datos del dto={} ", ContratoDto.class );

		ContratoDto contratoDto = (ContratoDto) target;
		
		if( StringUtils.isBlank( contratoDto.getCodigo() )  ) {
			
			errors.rejectValue("codigo", "", "El campo codigo no puede estar vacío");
		}
		
		if( contratoDto.getFechaCreacion() == null ) {
			
			errors.rejectValue("fechaCreacion", "", "El campo fecha de creacion no puede estar vacío");
		}
		
		if( contratoDto.getFechaInicio() == null  ) {
			
			errors.rejectValue("fechaInicio", "", "El campo fecha de inicio no puede estar vacío");
			
		} else if( (contratoDto.getFechaInicio() != null && contratoDto.getFechaFin() != null ) 
						&& contratoDto.getFechaInicio().after( contratoDto.getFechaFin() )  ) {
			
			errors.rejectValue("fechaInicio", "", "El campo fecha de inicio no puede ser superior al campo fecha de fin");
		}
		
		if( contratoDto.getFechaFin() == null ) {
			
			errors.rejectValue("fechaFin", "", "El campo fecha de fin no puede estar vacío");
			
		}else if( (contratoDto.getFechaInicio() != null && contratoDto.getFechaFin() != null ) 
				&& contratoDto.getFechaFin().before( contratoDto.getFechaInicio() )  ) {
			
			errors.rejectValue("fechaFin", "", "El campo fecha de fin no puede ser inferior al campo fecha de inicio");
		}
		
		if( contratoDto.getImpuesto() == null  ) {
			
			errors.rejectValue("impuesto", "", "El selector de impuestos no puede estar vacío");
		}
		
		if( contratoDto.getBaseImponibleTotal() == null  ) {
			
			errors.rejectValue("baseImponibleTotal", "", "El campo base imponible no puede estar vacío");
			
		}else if( contratoDto.getBaseImponibleTotal().intValue() < 0  ) {
			
			errors.rejectValue("baseImponibleTotal", "", "El campo base imponible no puede ser negativo");
			
		}else if( contratoDto.getBaseImponibleTotal().intValue() == 0  ) {
			
			errors.rejectValue("baseImponibleTotal", "", "El campo base imponible no puede ser cero");
		}
		
		if( contratoDto.getImporteTotal() == null  ) {
			
			errors.rejectValue("importeTotal", "", "El campo importe total no puede estar vacío");
			
		}else if( contratoDto.getImporteTotal().intValue() < 0  ) {
			
			errors.rejectValue("importeTotal", "", "El campo importe total no puede ser negativo");
			
		}else if( contratoDto.getImporteTotal().intValue() == 0  ) {
			
			errors.rejectValue("importeTotal", "", "El campo importe total no puede ser cero");
		}
		
	}

}
