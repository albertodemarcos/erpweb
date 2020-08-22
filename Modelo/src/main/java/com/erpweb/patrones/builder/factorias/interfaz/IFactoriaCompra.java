package com.erpweb.patrones.builder.factorias.interfaz;

import java.math.BigDecimal;

import com.erpweb.dto.CompraDto;
import com.erpweb.entidades.compras.Compra;
import com.erpweb.entidades.ventas.Factura;

public interface IFactoriaCompra {
	
	/**
	 * Generamos la entidad principal
	 */
	public Compra crearEntidad(CompraDto compraDto, Factura factura);
	
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
	
	/**
	 * Pre-Generamos una factura
	 */
	public Factura preCrearFacturaEntidad();
	
	//Metodo auxiliar
	
	/**
	 * Calcular totales
	 */
	public BigDecimal calcularImporte(BigDecimal importe, BigDecimal cantidad);
}
