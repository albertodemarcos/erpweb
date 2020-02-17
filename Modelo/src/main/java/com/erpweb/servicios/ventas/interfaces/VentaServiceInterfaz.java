package com.erpweb.servicios.ventas.interfaces;

import com.erpweb.dto.VentaDto;
import com.erpweb.entidades.ventas.Venta;

public interface VentaServiceInterfaz {

	public Boolean creaVentaDesdeVentaDto(VentaDto ventaDto); //Crea la venta mediante dto
	
	public VentaDto obtieneVentaDto(Long id, Long empresaId); //Visualizar la venta
	
	public Boolean actualizaVenta(VentaDto ventaDto); //Actualizamos la venta 

	public Boolean eliminaVenta(Venta venta); //Borramos la venta

	public Venta obtieneVenta(Long id, Long empresaId); //Obtenemos la venta de BBDD
	
}
