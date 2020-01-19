package com.erpweb.servicios.crm;

import org.springframework.stereotype.Service;

import com.erpweb.entidades.crm.Cliente;
import com.erpweb.servicios.crm.interfaces.ClienteServiceInterfaz;
import com.erpweb.utiles.dao.ClienteDao;



@Service
public class ClienteService implements ClienteServiceInterfaz {

	@Override
	public Cliente obtieneClienteDeClienteDao(ClienteDao clienteDao) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClienteDao obtieneClienteDaoDeCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void persisteCliente(Cliente cliente) {
		// TODO Auto-generated method stub
	}

	@Override
	public void destruyeCliente(Cliente cliente) {
		// TODO Auto-generated method stub
	}

	

}
