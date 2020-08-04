package com.erpweb.patrones.builder.factorias.interfaz;

import com.erpweb.dto.ContratoDto;
import com.erpweb.entidades.ventas.Contrato;

public interface IFactoriaContrato {

	/**
	 * Generamos la entidad principal
	 */
	public Contrato crearEntidad(ContratoDto contratoDto);
	
	/**
	 * Generamos las lineas de la entidad principal
	 */
	public Contrato crearLineasEntidad(Contrato contrato, ContratoDto contratoDto);
	
}
