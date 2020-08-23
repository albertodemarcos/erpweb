package com.erpweb.patrones.builder.factorias.interfaz;

import java.math.BigDecimal;

import com.erpweb.dto.PedidoDto;
import com.erpweb.entidades.compras.Pedido;
import com.erpweb.entidades.ventas.Factura;

public interface IFactoriaPedido {

	/**
	 * Generamos la entidad principal
	 */
	public Pedido crearEntidad(PedidoDto pedidoDto, Factura factura);
	
	/**
	 * Generamos las lineas de la entidad principal
	 */
	public Pedido crearLineasEntidad(Pedido pedido, PedidoDto pedidoDto);
	
	/**
	 * Generamos las facturas de la entidad principal
	 * */
	public Factura crearFacturaEntidad(Pedido pedido);
	
	/**
	 *	Generamos las lineas factura de la entidad principal 
	 */
	public Factura crearLineasFacturaEntidad(Pedido pedido, Factura factura);
	
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
