package com.erpweb.servicios.ventas.interfaces;

import com.erpweb.entidades.ventas.Venta;

public interface VentaServiceInterfaz {

	public void obtieneVenta(Long id, Long empresaId); //Obtenemos la venta de BBDD
	
	public void obtieneVentaDto(Long id, Long empresaId); //Obtenemos la venta y lo llevamos a capa vista mediante dto
	
	public void actualizaVenta(Venta venta); //Actualizamos la venta 

	public void eliminaVenta(Venta venta); //Borramos la venta
}
