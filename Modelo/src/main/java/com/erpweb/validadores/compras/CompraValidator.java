package com.erpweb.validadores.compras;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.erpweb.dto.CompraDto;

@Component
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
		
		if( StringUtils.isBlank( compraDto.getCodigo() )  ) {
			
			errors.rejectValue("codigo", "", "El campo codigo no puede estar vacio");
		}
		
		if(  compraDto.getFechaCompra() == null ) {
			
			errors.rejectValue("fechaCompra", "", "El campo fecha no puede estar vacio");
		} 
		
		//Evaluamos el mapa de los articulos escogidos
		if( CollectionUtils.isEmpty( compraDto.getArticulosCantidad() ) ) {
			
			errors.rejectValue("articulosCantidad", "", "Debe haber al menos un articulo en la compra");
		}
		
		
	}

}
