package com.erpweb.patrones.builder.constructores.interfaz;

import com.erpweb.dto.CompraDto;
import com.erpweb.entidades.compras.Compra;

public interface IConstructorCompra {
	
	public Compra crearEntidadLineasEntidad(CompraDto compraDto);
	
}
