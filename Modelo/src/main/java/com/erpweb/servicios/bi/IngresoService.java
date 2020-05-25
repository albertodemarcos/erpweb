package com.erpweb.servicios.bi;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpweb.dto.IngresoDto;
import com.erpweb.entidades.bi.Ingreso;
import com.erpweb.entidades.empresa.Empresa;
import com.erpweb.repositorios.bi.IngresoRepository;
import com.erpweb.repositorios.empresa.EmpresaRepository;
import com.erpweb.servicios.bi.interfaces.IngresoServiceInterfaz;

@Service
public class IngresoService implements IngresoServiceInterfaz {

	@Autowired
	private IngresoRepository ingresoRepository;
	@Autowired
	private EmpresaRepository empresaRepository;
	
		

}
