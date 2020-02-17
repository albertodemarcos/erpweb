package com.erpweb.servicios.bi.interfaces;

import com.erpweb.dto.InformeDto;
import com.erpweb.entidades.bi.Informe;

public interface InformeServiceInterfaz {

	public Boolean creaInformeDesdeInformeDto(InformeDto informeDto); //Crea  mediante
	
	public InformeDto obtieneInformeDto(Long id, Long empresaId); //Visualizar el informe
	
	public Boolean actualizaInforme(InformeDto informeDto); //Actualizamos el informe
	
	public Boolean eliminaInforme(Informe informe); //Borramos el informe
	
	public Informe obtieneInforme(Long id, Long empresaId); //Obtenemos el informe de BBDD
}
