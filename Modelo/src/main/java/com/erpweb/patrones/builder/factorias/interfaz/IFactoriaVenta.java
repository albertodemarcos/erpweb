package com.erpweb.patrones.builder.factorias.interfaz;

import java.math.BigDecimal;
import java.util.Set;

import com.erpweb.dto.VentaDto;
import com.erpweb.entidades.inventario.StockArticulo;
import com.erpweb.entidades.ventas.Factura;
import com.erpweb.entidades.ventas.Venta;

public interface IFactoriaVenta {

	/**
	 * Generamos la entidad principal
	 */
	public Venta crearEntidad(VentaDto ventaDto, Factura factura);
	
	/**
	 * Generamos las lineas de la entidad principal
	 */
	public Venta crearLineasEntidad(Venta Venta, VentaDto ventaDto);
	
	/**
	 * Generamos las facturas de la entidad principal
	 * */
	public Factura crearFacturaEntidad(Venta Venta, Factura facturaPre);
	
	/**
	 *	Generamos las lineas factura de la entidad principal 
	 */
	public Factura crearLineasFacturaEntidad(Venta Venta, Factura factura);
	
	/**
	 * Pre-Generamos una factura
	 */
	public Factura preCrearFacturaEntidad();
	
	//Metodo auxiliar
	
	/**
	 * Calcular totales
	 */
	public BigDecimal calcularImporte(BigDecimal importe, BigDecimal cantidad);
	
	/**
	 * 
	 * @param articulosId
	 * @param almacenesId
	 * @return
	 */
	public Set<StockArticulo> recuperarStockAlmacenes(Set<Long> articulosId, Set<Long> almacenesId);
	
	/**
	 * 
	 * @param stockAlmacen
	 * @param almacenId
	 * @param articuloId
	 * @param cantidad
	 */
	public void restarCantidadStockAlmacenes(Set<StockArticulo> stockAlmacen, Long almacenId, Long articuloId, BigDecimal cantidad);
}
