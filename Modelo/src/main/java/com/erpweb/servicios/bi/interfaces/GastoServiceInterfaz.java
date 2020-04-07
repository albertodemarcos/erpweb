package com.erpweb.servicios.bi.interfaces;

import com.erpweb.dto.GastoDto;
import com.erpweb.entidades.bi.Gasto;

public interface GastoServiceInterfaz {

	public Boolean creaGastoDesdeGastoDto(GastoDto gastoDto); //Crea  mediante
	
	public GastoDto obtieneGastoDto(Long id, Long empresaId); //Visualizar el gasto
	
	public Boolean actualizaGasto(GastoDto gastoDto); //Actualizamos el gasto
	
	public Boolean eliminaGasto(Gasto gasto); //Borramos el gasto
	
	public Boolean eliminarGastoPorId(Long gastoId, Long empresaId); //Borramos el gasto por su ID e empresaID
	
	public Gasto obtieneGasto(Long id, Long empresaId); //Obtenemos el gasto de BBDD
	
}
