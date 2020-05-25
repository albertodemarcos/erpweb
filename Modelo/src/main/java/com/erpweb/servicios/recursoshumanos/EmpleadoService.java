package com.erpweb.servicios.recursoshumanos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpweb.repositorios.comun.DireccionPostalRepository;
import com.erpweb.repositorios.comun.PoblacionRepository;
import com.erpweb.repositorios.comun.ProvinciaRepository;
import com.erpweb.repositorios.empresa.EmpresaRepository;
import com.erpweb.repositorios.recursoshumanos.EmpleadoRepository;
import com.erpweb.servicios.recursoshumanos.interfaces.EmpleadoServiceInterfaz;



@Service
public class EmpleadoService implements EmpleadoServiceInterfaz {

	@Autowired
	private EmpleadoRepository empleadoRepository;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private DireccionPostalRepository direccionPostalRepository;
	
	@Autowired
	private ProvinciaRepository provinciaRepository;
	
	@Autowired
	private PoblacionRepository poblacionRepository;
	
	
	

}
