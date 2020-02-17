package com.erpweb.servicios.recursoshumanos.interfaces;

import com.erpweb.entidades.recursoshumanos.Nomina;

public interface NominaServiceInterfaz {

	public void obtieneNomina(Long id, Long empresaId); //Obtenemos la nomina de BBDD
	
	public void obtieneNominaDto(Long id, Long empresaId); //Obtenemos la nomina y lo llevamos a capa vista mediante dto
	
	public void actualizaNomina(Nomina nomina); //Actualizamos la nomina 

	public void eliminaNomina(Nomina nomina); //Borramos la nomina
}
