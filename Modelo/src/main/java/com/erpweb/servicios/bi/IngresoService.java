package com.erpweb.servicios.bi;

import org.springframework.stereotype.Service;

import com.erpweb.entidades.bi.Ingreso;
import com.erpweb.servicios.bi.interfaces.IngresoServiceInterfaz;
import com.erpweb.utiles.dao.IngresoDao;



@Service
public class IngresoService implements IngresoServiceInterfaz {

	@Override
	public Ingreso obtieneIngresoDeIngresoDao(Ingreso ingreso) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IngresoDao obtieneIngresoDaoDeIngreso(IngresoDao ingresoDao) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void persisteIngreso(Ingreso ingreso) {
		// TODO Auto-generated method stub
	}

	@Override
	public void destruyeIngreso(Ingreso ingreso) {
		// TODO Auto-generated method stub
	}

}
