package com.erpweb.patrones.builder.factorias.interfaz;

import java.math.BigDecimal;
import java.util.Set;

import com.erpweb.dto.ContratoDto;
import com.erpweb.entidades.inventario.StockArticulo;
import com.erpweb.entidades.ventas.Contrato;
import com.erpweb.entidades.ventas.Factura;

public interface IFactoriaContrato {

	/**
	 * Generamos la entidad principal
	 */
	public Contrato crearEntidad(ContratoDto contratoDto, Factura factura);
	
	/**
	 * Generamos las lineas de la entidad principal
	 */
	public Contrato crearLineasEntidad(Contrato contrato, ContratoDto contratoDto);
	
	/**
	 * Generamos las facturas de la entidad principal
	 * */
	public Factura crearFacturaEntidad(Contrato contrato, Factura factura);
	
	/**
	 *	Generamos las lineas factura de la entidad principal 
	 */
	public Factura crearLineasFacturaEntidad(Contrato contrato, Factura factura);
	
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
	 * Recuperar stock articulos
	 * @param articulosId
	 * @param almacenesId
	 * @return
	 */
	public Set<StockArticulo> recuperarStockAlmacenes(Set<Long> articulosId, Set<Long> almacenesId);
	
	/**
	 * Restamos al stock del almacen
	 * @param stockAlmacen
	 * @param almacenId
	 * @param articuloId
	 * @param cantidad
	 */
	public void restarCantidadStockAlmacenes(Set<StockArticulo> stockAlmacen, Long almacenId, Long articuloId, BigDecimal cantidad);
}
