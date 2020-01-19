package com.erpweb.servicios.transportes.interfaces;

import com.erpweb.entidades.transportes.Coste;
import com.erpweb.utiles.dao.CosteDao;

public interface CosteServiceInterfaz {

	public Coste obtieneCosteDeCosteDao(CosteDao costeDao);
	
	public CosteDao obtieneCosteDaoDeCoste(Coste coste);
	
	public void persisteCoste(Coste coste);
	
	public void destruyeCoste(Coste coste);
}
