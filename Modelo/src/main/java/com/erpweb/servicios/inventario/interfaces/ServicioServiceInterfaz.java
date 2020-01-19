package com.erpweb.servicios.inventario.interfaces;

import com.erpweb.entidades.compras.Servicio;
import com.erpweb.utiles.dao.ServicioDao;

public interface ServicioServiceInterfaz {

	public Servicio obtieneServicioDeServicioDao(ServicioDao servicioDao);
	
	public ServicioDao obtieneServicioDaoDeServicio(Servicio servicio);
	
	public void persisteServicio(Servicio servicio);
	
	public void destruyeServicio(Servicio servicio);
}
