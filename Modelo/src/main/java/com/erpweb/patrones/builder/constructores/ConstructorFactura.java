package com.erpweb.patrones.builder.constructores;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erpweb.dto.FacturaDto;
import com.erpweb.entidades.ventas.Factura;
import com.erpweb.patrones.builder.constructores.claseBase.ConstructorEntidad;
import com.erpweb.patrones.builder.constructores.interfaz.IConstructorFactura;
import com.erpweb.patrones.builder.factorias.FactoriaFactura;

@Component
public class ConstructorFactura extends ConstructorEntidad implements IConstructorFactura {

	@Autowired
	private FactoriaFactura factoriaFactura;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public Factura crearEntidadLineasEntidad(FacturaDto facturaDto) {

		logger.trace("Entramos en crearEntidadLineasEntidad() para la factura"); 
		
		try {
			
			// Creamos la factura en dos pasos
			Factura factura = null;
			
			//Paso 1: Factura
			factura = factoriaFactura.crearEntidad(facturaDto);
			
			if( factura == null ) {
				
				logger.error("Error al crear la entidad de factura");
				
				return null;
			}
			
			//Paso 2: LineaFactura
			factura = factoriaFactura.crearLineasEntidad(factura, facturaDto);
			
			return factura;
			
		} catch(Exception e) {
			
			logger.error("Error al crear la entidad y sus lineas de factura");
			
			e.printStackTrace();
			
			return null;
		}
	}

}
