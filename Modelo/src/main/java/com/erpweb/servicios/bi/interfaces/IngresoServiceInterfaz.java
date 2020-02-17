package com.erpweb.servicios.bi.interfaces;

import com.erpweb.entidades.bi.Ingreso;

public interface IngresoServiceInterfaz {

	public void obtieneIngreso(Long id, Long empresaId); //Obtenemos el informe de BBDD
	
	public void obtieneIngresoDto(Long id, Long empresaId); //Obtenemos el informe y lo llevamos a capa vista mediante dto
	
	public void actualizaIngreso(Ingreso ingreso); //Actualizamos el informe
	
	public void eliminaIngreso(Ingreso ingreso); //Borramos el informe
	
}
