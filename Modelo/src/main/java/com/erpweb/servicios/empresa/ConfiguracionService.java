package com.erpweb.servicios.empresa;

import org.springframework.stereotype.Service;

import com.erpweb.dto.ConfiguracionDto;
import com.erpweb.entidades.empresa.Configuracion;
import com.erpweb.servicios.empresa.interfaces.ConfiguracionServiceInterfaz;

@Service
public class ConfiguracionService implements ConfiguracionServiceInterfaz {

	@Override
	public Boolean creaConfiguracionDesdeConfiguracionDto(ConfiguracionDto configuracionDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConfiguracionDto obtieneConfiguracionDto(Long id, Long empresaId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean actualizaConfiguracion(ConfiguracionDto configuracionDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean eliminaConfiguracion(Configuracion configuracion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Configuracion obtieneConfiguracion(Long id, Long empresaId) {
		// TODO Auto-generated method stub
		return null;
	}



}
