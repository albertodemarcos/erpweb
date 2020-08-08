package com.erpweb.patrones.builder.factorias.interfaz;

import com.erpweb.dto.VentaDto;
import com.erpweb.entidades.ventas.Factura;
import com.erpweb.entidades.ventas.Venta;

public interface IFactoriaVenta {

	/**
	 * Generamos la entidad principal
	 */
	public Venta crearEntidad(VentaDto ventaDto);
	
	/**
	 * Generamos las lineas de la entidad principal
	 */
	public Venta crearLineasEntidad(Venta Venta, VentaDto ventaDto);
	
	/**
	 * Generamos las facturas de la entidad principal
	 * */
	public Factura crearFacturaEntidad(Venta Venta);
	
	/**
	 *	Generamos las lineas factura de la entidad principal 
	 */
	public Factura crearLineasFacturaEntidad(Venta Venta, Factura factura);
	
}
