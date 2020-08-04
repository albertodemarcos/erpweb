package com.erpweb.patrones.builder.constructores;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erpweb.dto.VentaDto;
import com.erpweb.entidades.ventas.Venta;
import com.erpweb.patrones.builder.constructores.claseBase.ConstructorEntidad;
import com.erpweb.patrones.builder.constructores.interfaz.IConstructorVenta;
import com.erpweb.patrones.builder.factorias.FactoriaVenta;

@Component
public class ConstructorVenta extends ConstructorEntidad implements IConstructorVenta {

	@Autowired
	private FactoriaVenta factoriaVenta;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public Venta crearEntidadLineasEntidad(VentaDto ventaDto) {

		logger.trace("Entramos en crearEntidadLineasEntidad() para la venta"); 
		
		try {
			
			// Creamos la venta en dos pasos
			Venta venta = null;
			
			//Paso 1: Venta
			venta = factoriaVenta.crearEntidad(ventaDto);
			
			if( venta == null ) {
				
				logger.error("Error al crear la entidad de venta");
				
				return null;
			}
			
			//Paso 2: LineaVenta
			venta = factoriaVenta.crearLineasEntidad(venta, ventaDto);
			
			return venta;			
			
		} catch(Exception e) {
			
			logger.error("Error al crear la entidad y sus lineas de venta");
			
			e.printStackTrace();
			
			return null;
		}
	}

}
