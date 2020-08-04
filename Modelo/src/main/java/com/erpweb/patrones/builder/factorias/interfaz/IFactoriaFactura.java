package com.erpweb.patrones.builder.factorias.interfaz;

import com.erpweb.dto.FacturaDto;
import com.erpweb.entidades.ventas.Factura;

public interface IFactoriaFactura {

	/**
	 * Generamos la entidad principal
	 */
	public Factura crearEntidad(FacturaDto facturaDto);
	
	/**
	 * Generamos las lineas de la entidad principal
	 */
	public Factura crearLineasEntidad(Factura factura, FacturaDto facturaDto);
	
}
