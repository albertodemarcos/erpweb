package com.erpweb.servicios.transportes;

import org.springframework.stereotype.Service;

import com.erpweb.entidades.transportes.Coste;
import com.erpweb.servicios.transportes.interfaces.CosteServiceInterfaz;
import com.erpweb.utiles.dao.CosteDao;



@Service
public class CosteService implements CosteServiceInterfaz {

	@Override
	public Coste obtieneCosteDeCosteDao(CosteDao costeDao) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CosteDao obtieneCosteDaoDeCoste(Coste coste) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void persisteCoste(Coste coste) {
		// TODO Auto-generated method stub
	}

	@Override
	public void destruyeCoste(Coste coste) {
		// TODO Auto-generated method stub
	}

}
