package com.erpweb.servicios.empresa.interfaces;

import com.erpweb.dto.ConfiguracionDto;
import com.erpweb.entidades.empresa.Configuracion;

public interface ConfiguracionServiceInterfaz {
	
	public Boolean creaConfiguracionDesdeConfiguracionDto(ConfiguracionDto configuracionDto); //Crea  mediante

	public ConfiguracionDto obtieneConfiguracionDto(Long id, Long empresaId); //Visualizar la configuracion
	
	public Boolean actualizaConfiguracion(ConfiguracionDto configuracionDto); //Actualizamos la configuracion
	
	public Boolean eliminaConfiguracion(Configuracion configuracion); //Borramos la configuracion

	public Configuracion obtieneConfiguracion(Long id, Long empresaId); //Obtenemos la configuracion de BBDD
}
