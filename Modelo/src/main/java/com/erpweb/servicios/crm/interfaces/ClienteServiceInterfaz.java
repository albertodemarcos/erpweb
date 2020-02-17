package com.erpweb.servicios.crm.interfaces;

import com.erpweb.entidades.crm.Cliente;

public interface ClienteServiceInterfaz {

	public void obtieneCliente(Long id, Long empresaId); //Obtenemos el cliente  de BBDD
	
	public void obtieneClienteDto(Long id, Long empresaId); //Obtenemos el cliente  y lo llevamos a capa vista mediante dto
	
	public void actualizaCliente(Cliente cliente); //Actualizamos el cliente

	public void eliminaCliente(Cliente cliente); //Borramos el cliente
	
}
