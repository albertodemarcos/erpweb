package com.erpweb.servicios.bi;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpweb.repositorios.bi.GastoRepository;
import com.erpweb.entidades.bi.Gasto;
import com.erpweb.servicios.bi.interfaces.GastoServiceInterfaz;
import com.erpweb.utiles.dao.GastoDao;



@Service
public class GastoService implements GastoServiceInterfaz {
	
	@Autowired GastoRepository gastoRepository;

	@Override
	public Gasto obtieneGastoDeGastoDato(GastoDao gastoDao) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GastoDao obtieneGastoDaoDeGasto(Gasto gasto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void persisteGasto(Gasto gasto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminaGasto(Gasto gasto) {
		// TODO Auto-generated method stub
		
	}

	
}
