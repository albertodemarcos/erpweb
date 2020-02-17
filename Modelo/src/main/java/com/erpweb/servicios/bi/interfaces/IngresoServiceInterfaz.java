package com.erpweb.servicios.bi.interfaces;

import com.erpweb.dto.IngresoDto;
import com.erpweb.entidades.bi.Ingreso;

public interface IngresoServiceInterfaz {

	public Boolean creaIngresoDesdeIngresoDto(IngresoDto ingresoDto); //Crea  mediante
	
	public IngresoDto obtieneIngresoDto(Long id, Long empresaId); //Obtenemos el informe y lo llevamos a capa vista mediante dto
	
	public Boolean actualizaIngreso(IngresoDto ingresoDto); //Actualizamos el informe
	
	public Boolean eliminaIngreso(Ingreso ingreso); //Borramos el informe
	
	public Ingreso obtieneIngreso(Long id, Long empresaId); //Obtenemos el informe de BBDD
	
}
