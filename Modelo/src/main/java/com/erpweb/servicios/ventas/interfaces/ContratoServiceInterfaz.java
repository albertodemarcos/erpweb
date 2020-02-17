package com.erpweb.servicios.ventas.interfaces;

import com.erpweb.dto.ContratoDto;
import com.erpweb.entidades.ventas.Contrato;

public interface ContratoServiceInterfaz {

	public Boolean creaContratoDesdeContratoDto(ContratoDto contratoDto); //Crea el contrato mediante dto
	
	public ContratoDto obtieneContratoDto(Long id, Long empresaId); //Visualizar el contato
	
	public Boolean actualizaContrato(ContratoDto contatoDto); //Actualizamos el contato

	public Boolean eliminaContrato(Contrato contato); //Borramos el contato 

	public Contrato obtieneContrato(Long id, Long empresaId); //Obtenemos el contato de BBDD
	
}
