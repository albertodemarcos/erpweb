package com.erpweb.patrones.builder.factorias.interfaz;

import com.erpweb.dto.CompraDto;
import com.erpweb.entidades.compras.Compra;
import com.erpweb.entidades.ventas.Factura;

public interface IFactoriaCompra {
	
	/**
	 * Generamos la entidad principal
	 */
	public Compra crearEntidad(CompraDto compraDto);
	
	/**
	 * Generamos las lineas de la entidad principal
	 */
	public Compra crearLineasEntidad(Compra compra, CompraDto compraDto);
	
	/**
	 * Generamos las facturas de la entidad principal
	 * */
	public Factura crearFacturaEntidad(Compra compra);
	
	/**
	 *	Generamos las lineas factura de la entidad principal 
	 */
	public Factura crearLineasFacturaEntidad(Compra compra, Factura factura);
}
