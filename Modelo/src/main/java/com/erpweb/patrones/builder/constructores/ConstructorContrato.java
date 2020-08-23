package com.erpweb.patrones.builder.constructores;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erpweb.dto.ContratoDto;
import com.erpweb.entidades.ventas.Contrato;
import com.erpweb.entidades.ventas.Factura;
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
			
			// Creamos el contrato y su factura en los siguientes pasos
			Contrato contrato = null;
			Factura factura = null;
			
			//Paso Previo: Creamos una factura vacia para asociar al contrato previamente
			factura = factoriaContrato.preCrearFacturaEntidad();
			
			//Paso 1: Contrato
			contrato = factoriaContrato.crearEntidad(contratoDto, factura);
			
			if( contrato == null ) {
				
				logger.error("Error al crear la entidad de contrato");
				
				return null;
			}
			
			//Paso 2: LineaContrato
			contrato = factoriaContrato.crearLineasEntidad(contrato, contratoDto);
			
			//Paso 3: Crear factura
			factura = factoriaContrato.crearFacturaEntidad(contrato);
			
			if( factura == null ) {
				
				logger.error("Error al crear la factura de la entidad de contrato");
				
				return null;
			}

			
			//Paso 4: Crear lineaFactura
			factura = factoriaContrato.crearLineasFacturaEntidad(contrato, factura);
					
			return contrato;
			
		} catch(Exception e) {
			
			logger.error("Error al crear la entidad y sus lineas de contrato");
			
			e.printStackTrace();
			
			return null;
		}
	}

}
