package com.erpweb.validadores.inventario;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.erpweb.dto.ArticuloDto;

public class ArticuloValidator implements Validator {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return ArticuloDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		logger.debug("Se evaluan los datos del dto={} ", ArticuloDto.class );
		
		ArticuloDto articuloDto = (ArticuloDto) target;
		
		if( articuloDto.getEmpresaId() == null || articuloDto.getEmpresaId().intValue() < 1 ) {
			
			errors.reject("ERROR_EMPRESA", "El artículo no esta asociado a una empresa");
		}
		
		if( StringUtils.isBlank( articuloDto.getCodigo() )  ) {
			
			errors.rejectValue("", "", "El campo codigo no puede estar vacío");
		}
		
		if( StringUtils.isBlank( articuloDto.getNombre() )  ) {
			
			errors.rejectValue("", "", "El campo nombre no puede estar vacío");
		}
		
		if( StringUtils.isBlank( articuloDto.getDescripcion() )  ) {
			
			errors.rejectValue("", "", "El campo descripcion no puede estar vacío");
		}
		
		if( articuloDto.getBaseImponible() == null  ) {
			
			errors.rejectValue("", "", "El campo base imponible no puede estar vacío");
			
		}else if( articuloDto.getBaseImponible().doubleValue() < 0) {
			
			errors.rejectValue("", "", "El campo base imponible no puede ser negativo");
			
		}else if( articuloDto.getBaseImponible().doubleValue() == 0 ) {
			
			errors.rejectValue("", "", "El campo base imponible no puede ser cero");
		}
		
		if( articuloDto.getImporteTotal() == null  ) {
			
			errors.rejectValue("", "", "El campo importe total no puede estar vacío");
			
		}else if( articuloDto.getImporteTotal().doubleValue() < 0) {
			
			errors.rejectValue("", "", "El campo importe total no puede ser negativo");
			
		}else if( articuloDto.getImporteTotal().doubleValue() == 0 ) {
			
			errors.rejectValue("", "", "El campo importe total no puede ser cero");
		}
		
		
		
	}

}
