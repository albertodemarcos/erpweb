package com.erpweb.patrones.builder.constructores.interfaz;

import com.erpweb.dto.VentaDto;
import com.erpweb.entidades.ventas.Venta;

public interface IConstructorVenta {

	public Venta crearEntidadLineasEntidad(VentaDto ventaDto);
	
}
