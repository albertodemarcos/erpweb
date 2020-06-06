package com.erpweb.validadores.inventario;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.erpweb.dto.VehiculoDto;

public class VehiculoValidator implements Validator {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return VehiculoDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		logger.debug("Se evaluan los datos del dto={} ", VehiculoDto.class );
		
		VehiculoDto vehiculoDto = (VehiculoDto) target;

	
		if( StringUtils.isBlank( vehiculoDto.getCodigo() )  ) {
			
			errors.rejectValue("", "", "El campo codigo no puede estar vacío");
		}
	
		if( StringUtils.isBlank( vehiculoDto.getMatricula() )  ) {
			
			errors.rejectValue("", "", "El campo matricula no puede estar vacío");
		}
		
		if( StringUtils.isBlank( vehiculoDto.getMarca() )  ) {
			
			errors.rejectValue("", "", "El campo marca no puede estar vacío");
		}
		
		if( StringUtils.isBlank( vehiculoDto.getModelo() )  ) {
			
			errors.rejectValue("", "", "El campo modelo no puede estar vacío");
		}
		
		if( vehiculoDto.getTipoVehiculo() == null  ) {
			
			errors.rejectValue("", "", "El campo tipo de vehiculo no puede estar vacío");
		}
		
		if( vehiculoDto.getFechaMatriculacion() == null  ) {
			
			errors.rejectValue("", "", "El campo fecha de matriculación no puede estar vacío");
		}
	
	
	}

}
