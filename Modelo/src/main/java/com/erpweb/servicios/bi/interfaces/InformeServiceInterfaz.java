package com.erpweb.servicios.bi.interfaces;

import com.erpweb.entidades.bi.Informe;
import com.erpweb.utiles.dao.InformeDao;

public interface InformeServiceInterfaz {

	public Informe obtieneInformeDeInformeDao( InformeDao informeDao );
	
	public InformeDao obtieneInformeDaoDeInforme( Informe informe );
	
	public void persisteInforme(Informe informe);
	
	public void destruyeInforme(Informe informe);
}
