package com.erpweb.servicios.ventas;

import org.springframework.stereotype.Service;

import com.erpweb.entidades.ventas.Venta;
import com.erpweb.servicios.ventas.interfaces.VentaServiceInterfaz;



@Service
public class VentaService implements VentaServiceInterfaz {

	@Override
	public void obtieneVenta(Long id, Long empresaId) {
		// TODO Auto-generated method stub
	}

	@Override
	public void obtieneVentaDto(Long id, Long empresaId) {
		// TODO Auto-generated method stub
	}

	@Override
	public void actualizaVenta(Venta venta) {
		// TODO Auto-generated method stub
	}

	@Override
	public void eliminaVenta(Venta venta) {
		// TODO Auto-generated method stub
	}


}
