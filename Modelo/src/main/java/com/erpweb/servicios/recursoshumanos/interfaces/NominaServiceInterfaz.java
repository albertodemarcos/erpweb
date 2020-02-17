package com.erpweb.servicios.recursoshumanos.interfaces;

import com.erpweb.dto.NominaDto;
import com.erpweb.entidades.recursoshumanos.Nomina;

public interface NominaServiceInterfaz {

	public Boolean creaNominaDesdeNominaDto(NominaDto nominaDto); //Crea la nomina mediante dto
	
	public NominaDto obtieneNominaDto(Long id, Long empresaId); //Visualizar la nomina

	public Boolean actualizaNomina(NominaDto nominaDto); //Actualizamos la nomina 
	
	public Boolean eliminaNomina(Nomina nomina); //Borramos la nomina

	public Nomina obtieneNomina(Long id, Long empresaId); //Obtenemos la nomina de BBDD
	
}
