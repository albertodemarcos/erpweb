package com.erpweb.patrones.builder.constructores;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erpweb.dto.CompraDto;
import com.erpweb.entidades.compras.Compra;
import com.erpweb.entidades.ventas.Factura;
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
			
			// Creamos la compra y su factura en los siguientes pasos
			Compra compra = null;
			Factura factura = null;
			
			//Paso Previo: Creamos una factura vacia para asociar a la compra previamente
			factura = factoriaCompra.preCrearFacturaEntidad();
			
			//Paso 1: Compra
			compra = factoriaCompra.crearEntidad(compraDto, factura);
			
			if( compra == null ) {
				
				logger.error("Error al crear la entidad de compra");
				
				return null;
			}
			
			//Paso 2: LineaCompra
			compra = factoriaCompra.crearLineasEntidad(compra, compraDto);
			
			//Paso 3: Crear factura
			factura = factoriaCompra.crearFacturaEntidad(compra);
			
			if( factura == null ) {
				
				logger.error("Error al crear la factura de la entidad de compra");
				
				return null;
			}

			
			//Paso 4: Crear lineaFactura
			factura = factoriaCompra.crearLineasFacturaEntidad(compra, factura);			
			
			return compra;
			
		} catch (Exception e) {
			
			logger.error("Error al crear la entidad y sus lineas de compra");
			
			e.printStackTrace();
			
			return null;
		}
	}


	


}
