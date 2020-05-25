package com.erpweb.servicios.empresa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpweb.repositorios.empresa.ConfiguracionRepository;
import com.erpweb.repositorios.empresa.EmpresaRepository;
import com.erpweb.servicios.empresa.interfaces.ConfiguracionServiceInterfaz;

@Service
public class ConfiguracionService implements ConfiguracionServiceInterfaz {

	@Autowired
	private ConfiguracionRepository configuracionRepository;
	@Autowired
	private EmpresaRepository empresaRepository;
	
	
	



}
