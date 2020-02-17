package com.erpweb.servicios.compras.interfaces;

import com.erpweb.entidades.compras.Compra;

public interface CompraServiceInterfaz {

	public void obtieneCompra(Long id, Long empresaId); //Obtenemos Compra  de BBDD
	
	public void obtieneCompraDto(Long id, Long empresaId); //Obtenemos Compra y lo llevamos a capa vista mediante dto
	
	public void actualizaCompra(Compra compra); //Actualizamos Compra

	public void eliminaCompra(Compra compra); //Borramos Compra
	
}
