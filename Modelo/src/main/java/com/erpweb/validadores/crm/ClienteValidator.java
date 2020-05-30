package com.erpweb.validadores.crm;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.erpweb.dto.ClienteDto;

public class ClienteValidator implements Validator {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return ClienteDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		logger.debug("Se evaluan los datos del dto={} ", ClienteDto.class );
		
		ClienteDto clienteDto = (ClienteDto) target;
		
		if( clienteDto.getEmpresaId() == null || clienteDto.getEmpresaId().intValue() < 1 ) {
			
			errors.reject("ERROR_EMPRESA", "El cliente no esta asociado a una empresa");
		}
		
		if( StringUtils.isBlank( clienteDto.getCodigo() )  ) {
			
			errors.rejectValue("", "", "El campo codigo no puede estar vacio");
		}
		
		
		if( StringUtils.isBlank( clienteDto.getNombre() )  ) {
			
			errors.rejectValue("", "", "El campo nombre no puede estar vacio");
		}
		
		if( StringUtils.isBlank( clienteDto.getApellidoPrimero() )  ) {
			
			errors.rejectValue("", "", "El campo apellido no puede estar vacio");
		}
		
		if( StringUtils.isBlank( clienteDto.getApellidoSegundo() )  ) {
			
			errors.rejectValue("", "", "El campo segundo apellido no puede estar vacio");
		}
		if( StringUtils.isBlank( clienteDto.getNif() )  ) {
			
			errors.rejectValue("", "", "El DNI no puede estar vacio");
		}
		
		if( StringUtils.isBlank( clienteDto.getCodigoDireccionPostal() )  ) {
			
			errors.rejectValue("", "", "El campo codigo dirección postal no puede estar vacio");
		}
		
		
		if( StringUtils.isBlank( clienteDto.getCodigoPostal() )  ) {
			
			errors.rejectValue("", "", "El campo codigo postal no puede estar vacio");
		}
		
		if( StringUtils.isBlank( clienteDto.getDireccion() )  ) {
			
			errors.rejectValue("", "", "El campo dirección no puede estar vacio");
		}
		
		if( StringUtils.isBlank( clienteDto.getEdificio() )  ) {
			
			errors.rejectValue("", "", "El campo edificio no puede estar vacio");
		}
		
		if( StringUtils.isBlank( clienteDto.getObservaciones() )  ) {
			
			errors.rejectValue("", "", "El campo observaciones no puede estar vacio");
		}
		
		if( StringUtils.isBlank( clienteDto.getTelefono() )  ) {
			
			errors.rejectValue("", "", "El campo telefono no puede estar vacio");
		}
		
		if( clienteDto.getProvinciaId() == null || clienteDto.getProvinciaId().longValue() < 1 ) {
			
			errors.rejectValue("", "", "Debe elegir la provincia");
		}
		
		if( clienteDto.getPoblacionId() == null ||  clienteDto.getPoblacionId().longValue() < 1 ) {
			
			errors.rejectValue("", "", "Debe elegir la poblacion");
		}
		
		
		
	}

}
