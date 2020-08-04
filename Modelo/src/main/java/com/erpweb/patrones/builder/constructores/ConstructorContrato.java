package com.erpweb.patrones.builder.constructores;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erpweb.dto.ContratoDto;
import com.erpweb.entidades.ventas.Contrato;
import com.erpweb.patrones.builder.constructores.claseBase.ConstructorEntidad;
import com.erpweb.patrones.builder.constructores.interfaz.IConstructorContrato;
import com.erpweb.patrones.builder.factorias.FactoriaContrato;

@Component
public class ConstructorContrato extends ConstructorEntidad implements IConstructorContrato {

	@Autowired
	private FactoriaContrato factoriaContrato;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public Contrato crearEntidadLineasEntidad(ContratoDto contratoDto) {

		logger.trace("Entramos en crearEntidadLineasEntidad() para el contrato"); 
		
		try {
			
			// Creamos el contrato en dos pasos
			Contrato contrato = null;
			
			//Paso 1: Contrato
			contrato = factoriaContrato.crearEntidad(contratoDto);
			
			if( contrato == null ) {
				
				logger.error("Error al crear la entidad de contrato");
				
				return null;
			}
			
			//Paso 2: LineaContrato
			contrato = factoriaContrato.crearLineasEntidad(contrato, contratoDto);
					
			return contrato;
			
		} catch(Exception e) {
			
			logger.error("Error al crear la entidad y sus lineas de contrato");
			
			e.printStackTrace();
			
			return null;
		}
	}

}
