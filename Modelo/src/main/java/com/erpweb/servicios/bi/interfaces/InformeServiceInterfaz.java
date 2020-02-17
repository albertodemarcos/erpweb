package com.erpweb.servicios.bi.interfaces;

import com.erpweb.entidades.bi.Informe;

public interface InformeServiceInterfaz {

	public void obtieneInforme(Long id, Long empresaId); //Obtenemos el informe de BBDD
	
	public void obtieneInformeDto(Long id, Long empresaId); //Obtenemos el informe y lo llevamos a capa vista mediante dto
	
	public void actualizaInforme(Informe informe); //Actualizamos el informe
	
	public void eliminaInforme(Informe informe); //Borramos el informe
	
}
