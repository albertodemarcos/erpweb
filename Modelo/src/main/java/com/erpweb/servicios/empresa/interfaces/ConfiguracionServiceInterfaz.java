package com.erpweb.servicios.empresa.interfaces;

import com.erpweb.entidades.empresa.Configuracion;

public interface ConfiguracionServiceInterfaz {

	public void obtieneConfiguracion(Long id, Long empresaId); //Obtenemos la configuracion de BBDD
	
	public void obtieneConfiguracionDto(Long id, Long empresaId); //Obtenemos la configuracion y lo llevamos a capa vista mediante dto
	
	public void actualizaConfiguracion(Configuracion configuracion); //Actualizamos la configuracion

	public void eliminaConfiguracion(Configuracion configuracion); //Borramos la configuracion
	
}
