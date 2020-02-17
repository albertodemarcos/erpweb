package com.erpweb.servicios.crm;

import org.springframework.stereotype.Service;

import com.erpweb.dto.ClienteDto;
import com.erpweb.entidades.crm.Cliente;
import com.erpweb.servicios.crm.interfaces.ClienteServiceInterfaz;



@Service
public class ClienteService implements ClienteServiceInterfaz {

	@Override
	public Boolean creaClienteDesdeClienteDto(ClienteDto clienteDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClienteDto obtieneClienteDto(Long id, Long empresaId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean actualizaCliente(ClienteDto clienteDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean eliminaCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cliente obtieneCliente(Long id, Long empresaId) {
		// TODO Auto-generated method stub
		return null;
	}


	

	

	

}
