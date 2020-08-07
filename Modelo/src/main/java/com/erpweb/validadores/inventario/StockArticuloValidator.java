package com.erpweb.validadores.inventario;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.erpweb.dto.StockArticuloDto;

@Component
public class StockArticuloValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		
		return StockArticuloDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		StockArticuloDto stockArticuloDto = (StockArticuloDto) target;
		
		//Almacen
		if( stockArticuloDto.getAlmacenId() == null ) {
			
			errors.rejectValue("almacenId", "", "Error, tienes que seleccionar un almacen");
			
		} else if( stockArticuloDto.getAlmacenId().doubleValue() <= 0 ) {
			
			errors.rejectValue("almacenId", "", "Error, el almacen seleccionado no puede seleccionarse");
		}
		
		
		// Articulo
		if( stockArticuloDto.getArticuloId() == null ) {
			
			errors.rejectValue("articuloId", "", "Error, tienes que seleccionar un articulo");
			
		} else if( stockArticuloDto.getArticuloId().doubleValue() <= 0 ) {
			
			errors.rejectValue("articuloId", "", "Error, el articulo no puede seleccionarse");
		}
		
		
		//Cantidad
		if( stockArticuloDto.getCantidad() == null ) {
			
			errors.rejectValue("cantidad", "", "Error, tienes que introducir una cantidad de stock para el articulo seleccionado");
			
		} else if( stockArticuloDto.getCantidad().doubleValue() <= 0 ) {
			
			errors.rejectValue("cantidad", "", "Error, la cantidad debe ser superior o igual a 1");
		}
		
	}

}
