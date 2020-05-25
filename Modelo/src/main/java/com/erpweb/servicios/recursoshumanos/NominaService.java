package com.erpweb.servicios.recursoshumanos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpweb.repositorios.empresa.EmpresaRepository;
import com.erpweb.repositorios.recursoshumanos.EmpleadoRepository;
import com.erpweb.repositorios.recursoshumanos.NominaRepository;
import com.erpweb.servicios.recursoshumanos.interfaces.NominaServiceInterfaz;

@Service
public class NominaService implements NominaServiceInterfaz {

	@Autowired
	private EmpleadoRepository empleadoRepository;
	
	@Autowired
	private NominaRepository nominaRepository;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	


}
