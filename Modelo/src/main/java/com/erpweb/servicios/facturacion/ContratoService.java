package com.erpweb.servicios.facturacion;

import org.springframework.stereotype.Service;

import com.erpweb.entidades.facturacion.Contrato;
import com.erpweb.servicios.facturacion.interfaces.ContratoServiceInterfaz;
import com.erpweb.utiles.dao.ContratoDao;



@Service
public class ContratoService implements ContratoServiceInterfaz {

	@Override
	public Contrato obtieneContratoDeContratoDao(ContratoDao contratoDao) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ContratoDao obtieneContratoDaoDEContrato(Contrato contrato) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void persisteContrato(Contrato contrato) {
		// TODO Auto-generated method stub
	}

	@Override
	public void destruyeContrato(Contrato contrato) {
		// TODO Auto-generated method stub
	}

}
