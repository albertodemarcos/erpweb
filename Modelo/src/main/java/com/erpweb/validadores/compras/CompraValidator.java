package com.erpweb.validadores.compras;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.erpweb.dto.CompraDto;

public class CompraValidator implements Validator {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return CompraDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		logger.debug("Se evaluan los datos del dto={} ", CompraDto.class );
		
		CompraDto compraDto = (CompraDto) target;
		
		if( compraDto.getEmpresaId() == null || compraDto.getEmpresaId().intValue() < 1 ) {
			
			errors.reject("ERROR_EMPRESA", "La compra no esta asociado a una empresa");
		}
		
		if( StringUtils.isBlank( compraDto.getCodigo() )  ) {
			
			errors.rejectValue("", "", "El campo codigo no puede estar vacio");
		}
		
		if(  compraDto.getFechaCompra() == null ) {
			
			errors.rejectValue("", "", "El campo fecha no puede estar vacio");
			
		} 
		
		//Crear validator especifico a las lineas de compras y al proveedor
		
		
	}

}
