package com.erpweb.servicios.facturacion.interfaces;

import com.erpweb.entidades.facturacion.Venta;
import com.erpweb.utiles.dao.VentaDao;

public interface VentaServiceInterfaz {

	public Venta obtieneVentaDeVentaDao(VentaDao ventaDao);
	
	public VentaDao obtieneVentaDaoDeVenta(Venta venta);
	
	public void persisteVenta(Venta venta);
	
	public void destruyeVenta(Venta venta);
}
