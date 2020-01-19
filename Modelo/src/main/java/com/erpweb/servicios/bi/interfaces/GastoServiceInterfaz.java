package com.erpweb.servicios.bi.interfaces;

import com.erpweb.entidades.bi.Gasto;
import com.erpweb.utiles.dao.GastoDao;

public interface GastoServiceInterfaz {

	
	public Gasto obtieneGastoDeGastoDato( GastoDao gastoDao );
	
	public GastoDao obtieneGastoDaoDeGasto( Gasto gasto );
	
	public void persisteGasto(Gasto gasto);
	
	public void eliminaGasto(Gasto gasto);
	
		
}
