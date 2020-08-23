package com.erpweb.validadores.compras;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.erpweb.dto.PedidoDto;

@Component
public class PedidoValidator implements Validator {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return PedidoDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		logger.debug("Se evaluan los datos del dto={} ", PedidoDto.class );
		
		PedidoDto pedidoDto = (PedidoDto) target;
		
		if( StringUtils.isBlank( pedidoDto.getCodigo() )  ) {
			
			errors.rejectValue("codigo", "", "El campo codigo no puede estar vacio");
		}
		
		if(  pedidoDto.getFechaPedido() == null ) {
			
			errors.rejectValue("fechaPedido", "", "El campo fecha no puede estar vacio");
		} 
		
		/*if( StringUtils.isBlank( pedidoDto.getArticulo() )  ) {
					
			errors.rejectValue("articulo", "", "El campo codigo no puede estar vacio");
		}
		
		if( pedidoDto.getCantidad() == null  ) {
			
			errors.rejectValue("cantidad", "", "El campo cantidad no puede estar vacío");
			
		}else if( pedidoDto.getCantidad().intValue() < 0  ) {
			
			errors.rejectValue("cantidad", "", "El campo cantidad no puede ser negativo");
			
		}else if( pedidoDto.getCantidad().intValue() == 0  ) {
			
			errors.rejectValue("cantidad", "", "El campo cantidad no puede ser cero");
		}
		
		if( pedidoDto.getImpuesto() == null ) {
			
			errors.rejectValue("impuesto", "", "El campo impuesto no puede estar vacio");
		}
		
		if( pedidoDto.getBaseImponibleTotal() == null  ) {
			
			errors.rejectValue("baseImponibleTotal", "", "El campo base imponible no puede estar vacío");
			
		}else if( pedidoDto.getBaseImponibleTotal().intValue() < 0  ) {
			
			errors.rejectValue("baseImponibleTotal", "", "El campo base imponible no puede ser negativo");
			
		}else if( pedidoDto.getBaseImponibleTotal().intValue() == 0  ) {
			
			errors.rejectValue("baseImponibleTotal", "", "El campo base imponible no puede ser cero");
		}
		
		if( pedidoDto.getImporteTotal() == null  ) {
			
			errors.rejectValue("importeTotal", "", "El campo importe total no puede estar vacío");
			
		}else if( pedidoDto.getImporteTotal().intValue() < 0  ) {
			
			errors.rejectValue("importeTotal", "", "El campo importe total no puede ser negativo");
			
		}else if( pedidoDto.getImporteTotal().intValue() == 0  ) {
			
			errors.rejectValue("importeTotal", "", "El campo importe total no puede ser cero");
		}
		*/
	}

}
