package com.erpweb.servicios.ventas;

import org.springframework.stereotype.Service;

import com.erpweb.dto.FacturaDto;
import com.erpweb.entidades.ventas.Factura;
import com.erpweb.servicios.ventas.interfaces.FacturaServiceInterfaz;



@Service
public class FacturaService implements FacturaServiceInterfaz {

	@Override
	public Boolean creaFacturaDesdeFacturaDto(FacturaDto facturaDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FacturaDto obtieneFacturaDto(Long id, Long empresaId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean actualizaFactura(FacturaDto facturaDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean eliminaFactura(Factura factura) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Factura obtieneFactura(Long id, Long empresaId) {
		// TODO Auto-generated method stub
		return null;
	}


}
