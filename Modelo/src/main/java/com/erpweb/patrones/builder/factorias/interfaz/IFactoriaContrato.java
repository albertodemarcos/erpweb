package com.erpweb.patrones.builder.factorias.interfaz;

import com.erpweb.dto.ContratoDto;
import com.erpweb.entidades.ventas.Contrato;
import com.erpweb.entidades.ventas.Factura;

public interface IFactoriaContrato {

	/**
	 * Generamos la entidad principal
	 */
	public Contrato crearEntidad(ContratoDto contratoDto);
	
	/**
	 * Generamos las lineas de la entidad principal
	 */
	public Contrato crearLineasEntidad(Contrato contrato, ContratoDto contratoDto);
	
	/**
	 * Generamos las facturas de la entidad principal
	 * */
	public Factura crearFacturaEntidad(Contrato contrato);
	
	/**
	 *	Generamos las lineas factura de la entidad principal 
	 */
	public Factura crearLineasFacturaEntidad(Contrato contrato, Factura factura);
	
}
