package com.erpweb.patrones.builder.constructores;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erpweb.dto.VentaDto;
import com.erpweb.entidades.ventas.Factura;
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
			
			// Creamos la venta y su factura en los siguientes pasos
			Venta venta = null;
			Factura factura = null;
			
			//Paso Previo: Creamos una factura vacia para asociar a la compra previamente
			factura = factoriaVenta.preCrearFacturaEntidad();
			
			//Paso 1: Venta
			venta = factoriaVenta.crearEntidad(ventaDto, factura);
			
			if( venta == null ) {
				
				logger.error("Error al crear la entidad de venta");
				
				return null;
			}
			
			//Paso 2: LineaVenta
			venta = factoriaVenta.crearLineasEntidad(venta, ventaDto);
			
			//Paso 3: Crear factura
			factura = factoriaVenta.crearFacturaEntidad(venta, factura);
			
			if( factura == null ) {
				
				logger.error("Error al crear la factura de la entidad de venta");
				
				return null;
			}
			
			//Paso 4: Crear lineaFactura
			factura = factoriaVenta.crearLineasFacturaEntidad(venta, factura);
			
			return venta;			
			
		} catch(Exception e) {
			
			logger.error("Error al crear la entidad y sus lineas de venta");
			
			e.printStackTrace();
			
			return null;
		}
	}

}
