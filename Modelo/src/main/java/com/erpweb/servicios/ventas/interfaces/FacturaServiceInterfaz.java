package com.erpweb.servicios.ventas.interfaces;

import com.erpweb.entidades.ventas.Factura;

public interface FacturaServiceInterfaz {

	public void obtieneFactura(Long id, Long empresaId); //Obtenemos la factura de BBDD
	
	public void obtieneFacturaDto(Long id, Long empresaId); //Obtenemos la factura y lo llevamos a capa vista mediante dto
	
	public void actualizaFactura(Factura factura); //Actualizamos la factura 

	public void eliminaFactura(Factura factura); //Borramos la factura 
	
	
}
