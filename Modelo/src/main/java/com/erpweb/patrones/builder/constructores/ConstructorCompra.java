package com.erpweb.patrones.builder.constructores;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erpweb.dto.CompraDto;
import com.erpweb.entidades.compras.Compra;
import com.erpweb.patrones.builder.constructores.claseBase.ConstructorEntidad;
import com.erpweb.patrones.builder.constructores.interfaz.IConstructorCompra;
import com.erpweb.patrones.builder.factorias.FactoriaCompra;

@Component
public class ConstructorCompra extends ConstructorEntidad implements IConstructorCompra {
	
	@Autowired
	private FactoriaCompra factoriaCompra; 
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	
	@Override
	public Compra crearEntidadLineasEntidad(CompraDto compraDto) {
		
		logger.trace("Entramos en crearEntidadLineasEntidad() para la compra"); 
		
		try {
			
			// Creamos la compra en dos pasos
			Compra compra = null;
			
			//Paso 1: Compra
			compra = factoriaCompra.crearEntidad(compraDto);
			
			if( compra == null ) {
				
				logger.error("Error al crear la entidad de compra");
				
				return null;
			}
			
			//Paso 2: LineaCompra
			compra = factoriaCompra.crearLineasEntidad(compra, compraDto);
			
			return compra;
			
		} catch (Exception e) {
			
			logger.error("Error al crear la entidad y sus lineas de compra");
			
			e.printStackTrace();
			
			return null;
		}
	}


	


}
