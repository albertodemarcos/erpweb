package com.erpweb.validadores.compras;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.erpweb.dto.ProveedorDto;

@Component
public class ProveedorValidator implements Validator {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return ProveedorDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		logger.debug("Se evaluan los datos del dto={} ", ProveedorDto.class );
		
		ProveedorDto proveedorDto = (ProveedorDto) target;
		
		if( StringUtils.isBlank( proveedorDto.getCodigo() )  ) {
			
			errors.rejectValue("", "", "El campo codigo no puede estar vacío");
		}
		
		if( StringUtils.isBlank( proveedorDto.getNombre() )  ) {
			
			errors.rejectValue("", "", "El campo nombre no puede estar vacío");
		}
		
		
		if( StringUtils.isBlank( proveedorDto.getNombreEmpresa() )  ) {
			
			errors.rejectValue("", "", "El campo nombre empresa no puede estar vacío");
		}
		
		if( StringUtils.isBlank( proveedorDto.getTelefono() )  ) {
			
			errors.rejectValue("", "", "El campo telefono no puede esta vacío");
		}
		
		if( proveedorDto.getTipoProveedor() == null  ) {
			
			errors.rejectValue("", "", "El campo tipo de proveedor no puede estar vacío");
		}
		
		
	}

}
