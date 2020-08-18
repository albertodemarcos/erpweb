package com.erpweb.validadores.compras;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
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
		
		/*if( StringUtils.isBlank( compraDto.getArticulo() )  ) {
					
			errors.rejectValue("articulo", "", "El campo codigo no puede estar vacio");
		}*/
		
		/*if( compraDto.getCantidad() == null  ) {
			
			errors.rejectValue("cantidad", "", "El campo cantidad no puede estar vacío");
			
		}else if( compraDto.getCantidad().intValue() < 0  ) {
			
			errors.rejectValue("cantidad", "", "El campo cantidad no puede ser negativo");
			
		}else if( compraDto.getCantidad().intValue() == 0  ) {
			
			errors.rejectValue("cantidad", "", "El campo cantidad no puede ser cero");
		}*/
		
		/*if( compraDto.getImpuesto() == null  ) {
			
			errors.rejectValue("impuesto", "", "El campo impuesto no puede estar vacio");
		}*/
		
		/*if( compraDto.getBaseImponibleTotal() == null  ) {
			
			errors.rejectValue("baseImponibleTotal", "", "El campo base imponible no puede estar vacío");
			
		}else if( compraDto.getBaseImponibleTotal().intValue() < 0  ) {
			
			errors.rejectValue("baseImponibleTotal", "", "El campo base imponible no puede ser negativo");
			
		}else if( compraDto.getBaseImponibleTotal().intValue() == 0  ) {
			
			errors.rejectValue("baseImponibleTotal", "", "El campo base imponible no puede ser cero");
		}*/
		
		/*if( compraDto.getImporteTotal() == null  ) {
			
			errors.rejectValue("importeTotal", "", "El campo importe total no puede estar vacío");
			
		}else if( compraDto.getImporteTotal().intValue() < 0  ) {
			
			errors.rejectValue("importeTotal", "", "El campo importe total no puede ser negativo");
			
		}else if( compraDto.getImporteTotal().intValue() == 0  ) {
			
			errors.rejectValue("importeTotal", "", "El campo importe total no puede ser cero");
		}*/
		
		
	}

}
