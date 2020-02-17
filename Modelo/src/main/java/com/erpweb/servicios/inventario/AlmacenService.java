package com.erpweb.servicios.inventario;

import org.springframework.stereotype.Service;

import com.erpweb.dto.AlmacenDto;
import com.erpweb.entidades.inventario.Almacen;
import com.erpweb.servicios.inventario.interfaces.AlmacenServiceInterfaz;



@Service
public class AlmacenService implements AlmacenServiceInterfaz {

	@Override
	public Boolean creaAlmacenDesdeAlmacenDto(AlmacenDto almacenDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AlmacenDto obtieneAlmacenDto(Long id, Long empresaId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean actualizaAlmacen(AlmacenDto almacenDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean eliminaAlmacen(Almacen almacen) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Almacen obtieneAlmacen(Long id, Long empresaId) {
		// TODO Auto-generated method stub
		return null;
	}


}
