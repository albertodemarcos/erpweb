package com.erpweb.servicios.compras;

import org.springframework.stereotype.Service;

import com.erpweb.dto.CompraDto;
import com.erpweb.entidades.compras.Compra;
import com.erpweb.servicios.compras.interfaces.CompraServiceInterfaz;



@Service
public class CompraService implements CompraServiceInterfaz{

	@Override
	public Boolean creaCompraDesdeCompraDto(CompraDto compraDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CompraDto obtieneCompraDto(Long id, Long empresaId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean actualizaCompra(CompraDto compraDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean eliminaCompra(Compra compra) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Compra obtieneCompra(Long id, Long empresaId) {
		// TODO Auto-generated method stub
		return null;
	}

	

	

	

}
