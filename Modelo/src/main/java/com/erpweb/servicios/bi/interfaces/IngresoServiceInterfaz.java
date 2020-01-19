package com.erpweb.servicios.bi.interfaces;

import com.erpweb.entidades.bi.Ingreso;
import com.erpweb.utiles.dao.IngresoDao;

public interface IngresoServiceInterfaz {

	public Ingreso obtieneIngresoDeIngresoDao(Ingreso ingreso);
	
	public IngresoDao obtieneIngresoDaoDeIngreso(IngresoDao ingresoDao);
	
	public void persisteIngreso(Ingreso ingreso);
	
	public void destruyeIngreso(Ingreso ingreso);
}
