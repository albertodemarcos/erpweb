package com.erpweb.servicios.compras.interfaces;

import com.erpweb.dto.CompraDto;
import com.erpweb.entidades.compras.Compra;

public interface CompraServiceInterfaz {

	public Boolean creaCompraDesdeCompraDto(CompraDto compraDto); //Crea la compra mediante dto
	
	public CompraDto obtieneCompraDto(Long id, Long empresaId); //Visualizar la compra
	
	public Boolean actualizaCompra(CompraDto compraDto); //Actualizamos la compra

	public Boolean eliminaCompra(Compra compra); //Borramos la compra
	
	public Compra obtieneCompra(Long id, Long empresaId); //Obtenemos la compra de BBDD
	
}
