package com.erpweb.validadores.inventario;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.erpweb.dto.AlmacenDto;

@Component
public class AlmacenValidator implements Validator {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return AlmacenDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		logger.debug("Se evaluan los datos del dto={} ", AlmacenDto.class );
		
		AlmacenDto empleadoDto = (AlmacenDto) target;
		
		
		if( StringUtils.isBlank( empleadoDto.getCodigo() )  ) {
			
			errors.rejectValue("", "", "El campo codigo no puede estar vacío");
		}
		
		if( StringUtils.isBlank( empleadoDto.getNombre() )  ) {
			
			errors.rejectValue("", "", "El campo nombre no puede estar vacío");
		}
		
		if( StringUtils.isBlank( empleadoDto.getCodigoPostal() )  ) {
			
			errors.rejectValue("codigoPostal", "", "El campo codigo postal no puede estar vacío");
		}
		
		if( StringUtils.isBlank( empleadoDto.getDireccion() )  ) {
			
			errors.rejectValue("direccion", "", "La campo direccion no puede estar vacío");
		}
		
		/*if( StringUtils.isBlank( empleadoDto.getEdificio() )  ) {
			
			errors.rejectValue("edificio", "", "El campo edificio no puede estar vacío");
		}*/
		
		/*if( StringUtils.isBlank( empleadoDto.getObservaciones() )  ) {
			
			errors.rejectValue("", "", "El campo observacíones no puede estar vacío");
		}*/
		
		if( StringUtils.isBlank( empleadoDto.getTelefono() )  ) {
			
			errors.rejectValue("telefono", "", "El campo telefono no puede estar vacío");
		}
		
		if( StringUtils.isBlank( empleadoDto.getPais()) ) {
			
			errors.rejectValue("pais", "", "Debe elegir el pais");
		}
		
		if( StringUtils.isBlank( empleadoDto.getProvincia() ) ) {
			
			errors.rejectValue("provincia", "", "Debe elegir la provincia");
		}
		
		if( StringUtils.isBlank( empleadoDto.getPoblacion() ) ) {
			
			errors.rejectValue("poblacion", "", "Debe elegir la poblacion");
		}
	}

}
