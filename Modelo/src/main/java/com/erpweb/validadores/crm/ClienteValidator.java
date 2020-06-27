package com.erpweb.validadores.crm;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.erpweb.dto.ClienteDto;

@Component
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
		
		if( StringUtils.isBlank( clienteDto.getCodigo() )  ) {
			
			errors.rejectValue("codigo", "", "El campo codigo no puede estar vacio");
		}
		
		if( StringUtils.isBlank( clienteDto.getNombre() )  ) {
			
			errors.rejectValue("nombre", "", "El campo nombre no puede estar vacio");
		}
		
		/*if( StringUtils.isBlank( clienteDto.getApellidoPrimero() )  ) {
			
			errors.rejectValue("", "", "El campo apellido no puede estar vacio");
		}*/
		
		/*if( StringUtils.isBlank( clienteDto.getApellidoSegundo() )  ) {
			
			errors.rejectValue("", "", "El campo segundo apellido no puede estar vacio");
		}*/
		if( StringUtils.isBlank( clienteDto.getNif() )  ) {
			
			errors.rejectValue("nif", "", "El DNI no puede estar vacio");
		}
		
		if( StringUtils.isBlank( clienteDto.getCodigoPostal() )  ) {
			
			errors.rejectValue("codigoPostal", "", "El campo codigo postal no puede estar vacio");
		}
		
		if( StringUtils.isBlank( clienteDto.getDireccion() )  ) {
			
			errors.rejectValue("direccion", "", "El campo dirección no puede estar vacio");
		}
		
		/*if( StringUtils.isBlank( clienteDto.getEdificio() )  ) {
			
			errors.rejectValue("", "", "El campo edificio no puede estar vacio");
		}*/
		
		/*if( StringUtils.isBlank( clienteDto.getObservaciones() )  ) {
			
			errors.rejectValue("", "", "El campo observaciones no puede estar vacio");
		}*/
		
		if( StringUtils.isBlank( clienteDto.getTelefono() )  ) {
			
			errors.rejectValue("telefono", "", "El campo telefono no puede estar vacio");
		}
		
		if( StringUtils.isBlank( clienteDto.getPais()) ) {
			
			errors.rejectValue("pais", "", "Debe elegir la provincia");
		}
		
		if( StringUtils.isBlank( clienteDto.getProvincia() ) ) {
			
			errors.rejectValue("provincia", "", "Debe elegir la provincia");
		}
		
		if( StringUtils.isBlank( clienteDto.getPoblacion() ) ) {
			
			errors.rejectValue("poblacion", "", "Debe elegir la poblacion");
		}
		
		if( clienteDto.getTipoCliente() == null ) {
			
			errors.rejectValue("tipoCliente", "", "Debe elegir el tipo de cliente");
		}
		
	}

}
