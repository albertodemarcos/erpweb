package com.erpweb.patrones.builder.factorias.interfaz;

import com.erpweb.dto.CompraDto;
import com.erpweb.entidades.compras.Compra;

public interface IFactoriaCompra {
	
	/**
	 * Generamos la entidad principal
	 */
	public Compra crearEntidad(CompraDto compraDto);
	
	/**
	 * Generamos las lineas de la entidad principal
	 */
	public Compra crearLineasEntidad(Compra compra, CompraDto compraDto);
	
}
