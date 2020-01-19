package com.erpweb.servicios.crm.interfaces;

import com.erpweb.entidades.crm.Cliente;
import com.erpweb.utiles.dao.ClienteDao;

public interface ClienteServiceInterfaz {

	public Cliente obtieneClienteDeClienteDao(ClienteDao clienteDao);
	
	public ClienteDao obtieneClienteDaoDeCliente(Cliente cliente);
	
	public void persisteCliente(Cliente cliente);
	
	public void destruyeCliente(Cliente cliente);
}
