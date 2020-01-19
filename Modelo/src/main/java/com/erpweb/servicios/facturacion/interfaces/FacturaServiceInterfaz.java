package com.erpweb.servicios.facturacion.interfaces;

import com.erpweb.entidades.facturacion.Factura;
import com.erpweb.utiles.dao.FacturaDao;

public interface FacturaServiceInterfaz {

	public Factura obtieneFacturaDeFacturaDao(FacturaDao FacturaDao);
	
	public FacturaDao obtieneFacturaDaoDeFactura(Factura Factura);
	
	public void persisteFactura(Factura factura); 
	
	public void destruyeFactura(Factura factura);
}
