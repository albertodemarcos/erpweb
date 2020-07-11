package com.erpweb.servicios.errores;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

@Service
public class ErroresService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public HashMap<String, Object> erroresValidacionEnDto(BindingResult result) {
		
		logger.debug("Entramos en el metodo erroresValidacionEnDto() ");
		
		HashMap<String, Object> errores = new HashMap<String, Object>();
		
		if( result != null && CollectionUtils.isNotEmpty(result.getAllErrors()) ) {
			
			logger.debug("Enviamos los errores que se cometieron al rellenar el formulario");
			
			errores.put("validacion", Boolean.FALSE);
			
			for(ObjectError objectError : result.getAllErrors()) {
				
				String[] campos = objectError.getCodes();
				
				errores.put(campos[1], objectError.getDefaultMessage());
			}
			
		}else {
			
			logger.error("ERROR! los errores que se cometieron al rellenar el formulario No han llegado.");
			
			errores.put("validacion", Boolean.FALSE);
			
			errores.put("errores", new ArrayList<ObjectError>() );
		}
		
		return errores;
	}
	
	
	
	
}
