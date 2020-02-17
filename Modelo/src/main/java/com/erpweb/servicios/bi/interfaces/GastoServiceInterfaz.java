package com.erpweb.servicios.bi.interfaces;

import com.erpweb.entidades.bi.Gasto;

public interface GastoServiceInterfaz {

	public void obtieneGasto(Long id, Long empresaId); //Obtenemos el gasto de BBDD
	
	public void obtieneGastoDto(Long id, Long empresaId); //Obtenemos el gasto y lo llevamos a capa vista mediante dto
	
	public void actualizaGasto(Gasto gasto); //Actualizamos el gasto
	
	public void eliminaGasto(Gasto gasto); //Borramos el gasto
	
}
