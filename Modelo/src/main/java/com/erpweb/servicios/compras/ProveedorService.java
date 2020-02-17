package com.erpweb.servicios.compras;

import org.springframework.stereotype.Service;

import com.erpweb.dto.ProveedorDto;
import com.erpweb.entidades.compras.Proveedor;
import com.erpweb.servicios.compras.interfaces.ProveedorServiceInterfaz;



@Service
public class ProveedorService implements ProveedorServiceInterfaz {

	@Override
	public Boolean creaProveedorDesdeProveedorDto(ProveedorDto proveedorDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProveedorDto obtieneProveedorDto(Long id, Long empresaId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean actualizaProveedor(ProveedorDto proveedorDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean eliminaProveedor(Proveedor proveedor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Proveedor obtieneProveedor(Long id, Long empresaId) {
		// TODO Auto-generated method stub
		return null;
	}


	

	
	
}
