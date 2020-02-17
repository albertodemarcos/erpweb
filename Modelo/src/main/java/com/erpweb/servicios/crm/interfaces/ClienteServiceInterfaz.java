package com.erpweb.servicios.crm.interfaces;

import com.erpweb.dto.ClienteDto;
import com.erpweb.entidades.crm.Cliente;

public interface ClienteServiceInterfaz {

	public Boolean creaClienteDesdeClienteDto(ClienteDto clienteDto); //Crea el cliente mediante dto
	
	public ClienteDto obtieneClienteDto(Long id, Long empresaId); //Visualizar el cliente
	
	public Boolean actualizaCliente(ClienteDto clienteDto); //Actualizamos el cliente
	
	public Boolean eliminaCliente(Cliente cliente); //Borramos el cliente

	public Cliente obtieneCliente(Long id, Long empresaId); //Obtenemos el cliente  de BBDD
}
