package com.erpweb.servicios.facturacion;

import org.springframework.stereotype.Service;

import com.erpweb.entidades.facturacion.Venta;
import com.erpweb.servicios.facturacion.interfaces.VentaServiceInterfaz;
import com.erpweb.utiles.dao.VentaDao;



@Service
public class VentaService implements VentaServiceInterfaz {

	@Override
	public Venta obtieneVentaDeVentaDao(VentaDao ventaDao) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VentaDao obtieneVentaDaoDeVenta(Venta venta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void persisteVenta(Venta venta) {
		// TODO Auto-generated method stub
	}

	@Override
	public void destruyeVenta(Venta venta) {
		// TODO Auto-generated method stub
	}

}
