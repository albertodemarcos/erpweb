package com.erpweb.patrones.builder.constructores.interfaz;

import com.erpweb.dto.ContratoDto;
import com.erpweb.entidades.ventas.Contrato;

public interface IConstructorContrato {

	public Contrato crearEntidadLineasEntidad(ContratoDto contratoDto);
	
}
