package com.erpweb.servicios.bi;

import org.springframework.stereotype.Service;

import com.erpweb.entidades.bi.Informe;
import com.erpweb.servicios.bi.interfaces.InformeServiceInterfaz;
import com.erpweb.utiles.dao.InformeDao;



@Service
public class InformeService implements InformeServiceInterfaz {

	@Override
	public Informe obtieneInformeDeInformeDao(InformeDao informeDao) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InformeDao obtieneInformeDaoDeInforme(Informe informe) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void persisteInforme(Informe informe) {
		// TODO Auto-generated method stub
	}

	@Override
	public void destruyeInforme(Informe informe) {
		// TODO Auto-generated method stub
	}

	

}
