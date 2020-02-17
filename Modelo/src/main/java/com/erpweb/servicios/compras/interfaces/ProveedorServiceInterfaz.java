package com.erpweb.servicios.compras.interfaces;

import com.erpweb.dto.ProveedorDto;
import com.erpweb.entidades.compras.Proveedor;

public interface ProveedorServiceInterfaz {

	public Boolean creaProveedorDesdeProveedorDto(ProveedorDto proveedorDto); //Crea  mediante

	public ProveedorDto obtieneProveedorDto(Long id, Long empresaId); //Obtenemos el proveedor y lo llevamos a capa vista mediante dto
	
	public Boolean actualizaProveedor(ProveedorDto proveedorDto); //Actualizamos el proveedor
	
	public Boolean eliminaProveedor(Proveedor proveedor); //Borramos el proveedor

	public Proveedor obtieneProveedor(Long id, Long empresaId); //Obtenemos el proveedor  de BBDD

}
