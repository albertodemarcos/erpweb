package com.erpweb.patrones.builder.constructores.interfaz;

import com.erpweb.dto.FacturaDto;
import com.erpweb.entidades.ventas.Factura;

public interface IConstructorFactura {

	public Factura crearEntidadLineasEntidad(FacturaDto facturaDto);
	
}
