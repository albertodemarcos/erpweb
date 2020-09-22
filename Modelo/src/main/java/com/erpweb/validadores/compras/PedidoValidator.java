package com.erpweb.validadores.compras;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
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
		
		//Evaluamos el mapa de los articulos escogidos
		if( CollectionUtils.isEmpty( pedidoDto.getArticulosCantidad() ) ) {
			
			errors.rejectValue("articulosCantidad", "", "Debe haber al menos un articulo en el pedido");
		}
	}

}
