package com.erpweb.servicios.ventas.interfaces;

import com.erpweb.entidades.ventas.Contrato;

public interface ContratoServiceInterfaz {

	public void obtieneContrato(Long id, Long empresaId); //Obtenemos el contato de BBDD
	
	public void obtieneContratoDto(Long id, Long empresaId); //Obtenemos el contato y lo llevamos a capa vista mediante dto
	
	public void actualizaContrato(Contrato contato); //Actualizamos el contato

	public void eliminaContrato(Contrato contato); //Borramos el contato 
	
	
}
