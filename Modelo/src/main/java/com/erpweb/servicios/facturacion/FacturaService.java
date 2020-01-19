package com.erpweb.servicios.facturacion;

import org.springframework.stereotype.Service;

import com.erpweb.entidades.facturacion.Factura;
import com.erpweb.servicios.facturacion.interfaces.FacturaServiceInterfaz;
import com.erpweb.utiles.dao.FacturaDao;



@Service
public class FacturaService implements FacturaServiceInterfaz {

	@Override
	public Factura obtieneFacturaDeFacturaDao(FacturaDao FacturaDao) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FacturaDao obtieneFacturaDaoDeFactura(Factura Factura) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void persisteFactura(Factura factura) {
		// TODO Auto-generated method stub
	}

	@Override
	public void destruyeFactura(Factura factura) {
		// TODO Auto-generated method stub
	}

}
