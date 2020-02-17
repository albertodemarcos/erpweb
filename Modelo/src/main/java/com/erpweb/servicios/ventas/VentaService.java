package com.erpweb.servicios.ventas;

import org.springframework.stereotype.Service;

import com.erpweb.dto.VentaDto;
import com.erpweb.entidades.ventas.Venta;
import com.erpweb.servicios.ventas.interfaces.VentaServiceInterfaz;



@Service
public class VentaService implements VentaServiceInterfaz {

	@Override
	public Boolean creaVentaDesdeVentaDto(VentaDto ventaDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VentaDto obtieneVentaDto(Long id, Long empresaId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean actualizaVenta(VentaDto ventaDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean eliminaVenta(Venta venta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Venta obtieneVenta(Long id, Long empresaId) {
		// TODO Auto-generated method stub
		return null;
	}


}
