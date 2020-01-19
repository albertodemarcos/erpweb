package com.erpweb.servicios.facturacion.interfaces;

import com.erpweb.entidades.facturacion.Contrato;
import com.erpweb.utiles.dao.ContratoDao;

public interface ContratoServiceInterfaz {

	public Contrato obtieneContratoDeContratoDao(ContratoDao contratoDao);
	
	public ContratoDao obtieneContratoDaoDEContrato(Contrato contrato);
	
	public void persisteContrato(Contrato contrato);
	
	public void destruyeContrato(Contrato contrato);
}
