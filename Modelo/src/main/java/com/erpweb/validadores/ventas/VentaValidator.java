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
		
		if( ventaDto.getFechaCreacion() == null ) {
			
			errors.rejectValue("fechaCreacion", "", "El campo fecha de creacion no puede estar vacío");
		}
		
		if( ventaDto.getFechaInicio() == null  ) {
			
			errors.rejectValue("fechaInicio", "", "El campo fecha de inicio no puede estar vacío");
			
		} else if( (ventaDto.getFechaInicio() != null && ventaDto.getFechaFin() != null ) 
						&& ventaDto.getFechaInicio().after( ventaDto.getFechaFin() )  ) {
			
			errors.rejectValue("fechaInicio", "", "El campo fecha de inicio no puede ser superior al campo fecha de fin");
		}
		
		if( ventaDto.getFechaFin() == null ) {
			
			errors.rejectValue("fechaFin", "", "El campo fecha de fin no puede estar vacío");
			
		}else if( (ventaDto.getFechaInicio() != null && ventaDto.getFechaFin() != null ) 
				&& ventaDto.getFechaFin().before( ventaDto.getFechaInicio() )  ) {
			
			errors.rejectValue("fechaFin", "", "El campo fecha de fin no puede ser inferior al campo fecha de inicio");
		}
		
		if( StringUtils.isBlank( ventaDto.getDescripcion() )  ) {
			
			errors.rejectValue("descripcion", "", "El campo descripcion no puede estar vacío");
		}
		
		if( ventaDto.getBaseImponibleTotal() == null  ) {
			
			errors.rejectValue("baseImponibleTotal", "", "El campo base imponible no puede estar vacío");
			
		}else if( ventaDto.getBaseImponibleTotal().intValue() < 0  ) {
			
			errors.rejectValue("baseImponibleTotal", "", "El campo base imponible no puede ser negativo");
			
		}else if( ventaDto.getBaseImponibleTotal().intValue() == 0  ) {
			
			errors.rejectValue("baseImponibleTotal", "", "El campo base imponible no puede ser cero");
		}
		
		if( ventaDto.getImporteTotal() == null  ) {
			
			errors.rejectValue("importeTotal", "", "El campo importe total no puede estar vacío");
			
		}else if( ventaDto.getImporteTotal().intValue() < 0  ) {
			
			errors.rejectValue("importeTotal", "", "El campo importe total no puede ser negativo");
			
		}else if( ventaDto.getImporteTotal().intValue() == 0  ) {
			
			errors.rejectValue("importeTotal", "", "El campo importe total no puede ser cero");
		}
		
			
	}

}
