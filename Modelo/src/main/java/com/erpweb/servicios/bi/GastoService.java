package com.erpweb.servicios.bi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpweb.repositorios.bi.GastoRepository;
import com.erpweb.repositorios.empresa.EmpresaRepository;
import com.erpweb.servicios.bi.interfaces.GastoServiceInterfaz;

@Service
public class GastoService implements GastoServiceInterfaz {
	
	@Autowired
	private GastoRepository gastoRepository;
	@Autowired
	private EmpresaRepository empresaRepository;

	
}
