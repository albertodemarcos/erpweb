package com.erpweb.validadores.ventas;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.erpweb.dto.FacturaDto;

@Component
public class FacturaValidator implements Validator {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return FacturaDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		logger.debug("Se evaluan los datos del dto={} ", FacturaDto.class );
		
		FacturaDto facturaDto = (FacturaDto) target;
		
		if( StringUtils.isBlank( facturaDto.getCodigo() )  ) {
			
			errors.rejectValue("codigo", "", "El campo codigo no puede estar vacío");
		}
		
		if( facturaDto.getFechaCreacion() == null ) {
			
			errors.rejectValue("fechaCreacion", "", "El campo fecha de creacion no puede estar vacío");
		}
		
		if( facturaDto.getFechaInicio() == null  ) {
			
			errors.rejectValue("fechaInicio", "", "El campo fecha de inicio no puede estar vacío");
			
		} else if( (facturaDto.getFechaInicio() != null && facturaDto.getFechaFin() != null ) 
						&& facturaDto.getFechaInicio().after( facturaDto.getFechaFin() )  ) {
			
			errors.rejectValue("fechaInicio", "", "El campo fecha de inicio no puede ser superior al campo fecha de fin");
		}
		
		if( facturaDto.getFechaFin() == null ) {
			
			errors.rejectValue("fechaFin", "", "El campo fecha de fin no puede estar vacío");
			
		}else if( (facturaDto.getFechaInicio() != null && facturaDto.getFechaFin() != null ) 
				&& facturaDto.getFechaInicio().before( facturaDto.getFechaFin() )  ) {
			
			errors.rejectValue("fechaFin", "", "El campo fecha de fin no puede ser inferior al campo fecha de inicio");
		}
		
		if( StringUtils.isBlank( facturaDto.getDescripcion() )  ) {
			
			errors.rejectValue("descripcion", "", "El campo descripcion no puede estar vacío");
		}
		
		if( facturaDto.getBaseImponible() == null  ) {
			
			errors.rejectValue("baseImponibleTotal", "", "El campo base imponible no puede estar vacío");
			
		}else if( facturaDto.getBaseImponible().intValue() < 0  ) {
			
			errors.rejectValue("baseImponibleTotal", "", "El campo base imponible no puede ser negativo");
			
		}else if( facturaDto.getBaseImponible().intValue() == 0  ) {
			
			errors.rejectValue("baseImponibleTotal", "", "El campo base imponible no puede ser cero");
		}
		
		if( facturaDto.getCuotaTributaria() == null  ) {
			
			errors.rejectValue("cuotaTributaria", "", "El campo couta tributaria no puede estar vacío");
			
		}else if( facturaDto.getCuotaTributaria().intValue() < 0  ) {
			
			errors.rejectValue("cuotaTributaria", "", "El campo couta tributaria no puede ser negativo");
			
		}else if( facturaDto.getCuotaTributaria().intValue() == 0  ) {
			
			errors.rejectValue("cuotaTributaria", "", "El campo couta tributaria no puede ser cero");
		}
		
		if( facturaDto.getImporteTotal() == null  ) {
			
			errors.rejectValue("importeTotal", "", "El campo importe total no puede estar vacío");
			
		}else if( facturaDto.getImporteTotal().intValue() < 0  ) {
			
			errors.rejectValue("importeTotal", "", "El campo importe total no puede ser negativo");
			
		}else if( facturaDto.getImporteTotal().intValue() == 0  ) {
			
			errors.rejectValue("importeTotal", "", "El campo importe total no puede ser cero");
		}
		
		
	}

}
