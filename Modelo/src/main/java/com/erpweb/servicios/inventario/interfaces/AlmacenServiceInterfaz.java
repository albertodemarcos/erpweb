package com.erpweb.servicios.inventario.interfaces;

import com.erpweb.dto.AlmacenDto;
import com.erpweb.entidades.inventario.Almacen;

public interface AlmacenServiceInterfaz {

	public Boolean creaAlmacenDesdeAlmacenDto(AlmacenDto almacenDto); //Crea el almacen mediante

	public AlmacenDto obtieneAlmacenDto(Long id, Long empresaId); //Visualizar el almacen
	
	public Boolean actualizaAlmacen(AlmacenDto almacenDto); //Actualizamos el almacen
	
	public Boolean eliminaAlmacen(Almacen almacen); //Borramos el almacen
	
	public Almacen obtieneAlmacen(Long id, Long empresaId); //Obtenemos el almacen de BBDD
	
}
