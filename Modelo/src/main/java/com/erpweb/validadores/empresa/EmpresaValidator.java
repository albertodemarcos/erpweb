package com.erpweb.validadores.empresa;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.erpweb.dto.EmpresaDto;

@Component
public class EmpresaValidator implements Validator {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return EmpresaDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		logger.debug("Se evaluan los datos del dto={} ", EmpresaDto.class);

		EmpresaDto empresaDto = (EmpresaDto) target;

		if (empresaDto.getId() == null || empresaDto.getId().intValue() < 1) {

			errors.reject("ERROR_EMPRESA", "La empresa no no existe");
		}

		if (StringUtils.isBlank(empresaDto.getCodigo())) {

			errors.rejectValue("codigo", "", "El campo codigo no puede estar vacío");
		}

		if( StringUtils.isBlank( empresaDto.getNombre() )  ) {
			
			errors.rejectValue("nombre", "", "El campo nombre no puede estar vacío");
		}
		
		if( StringUtils.isBlank( empresaDto.getCif() )  ) {
			
			errors.rejectValue("cif", "", "El campo cif no puede estar vacío");
		}
		
		if( empresaDto.getTipoSociedadJuridica() == null  ) {
			
			errors.rejectValue("tipoSociedadJuridica", "", "El campo sociedad juridica no puede estar vacío");
		}
		
		/*if( StringUtils.isBlank( empresaDto.getIdioma() )  ) {
			
			errors.rejectValue("idioma", "", "Debes elegir el idioma");
		}*/
		

	}

}
