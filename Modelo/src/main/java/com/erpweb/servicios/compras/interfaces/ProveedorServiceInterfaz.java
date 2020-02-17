package com.erpweb.servicios.compras.interfaces;

import com.erpweb.entidades.compras.Proveedor;

public interface ProveedorServiceInterfaz {

	public void obtieneProveedor(Long id, Long empresaId); //Obtenemos el proveedor  de BBDD
	
	public void obtieneProveedorDto(Long id, Long empresaId); //Obtenemos el proveedor y lo llevamos a capa vista mediante dto
	
	public void actualizaProveedor(Proveedor proveedor); //Actualizamos el proveedor

	public void eliminaProveedor(Proveedor proveedor); //Borramos el proveedor
}
