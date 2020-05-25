package com.erpweb.servicios.bi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpweb.dto.InformeDto;
import com.erpweb.entidades.bi.Informe;
import com.erpweb.entidades.empresa.Empresa;
import com.erpweb.repositorios.bi.InformeRepository;
import com.erpweb.repositorios.empresa.EmpresaRepository;
import com.erpweb.servicios.bi.interfaces.InformeServiceInterfaz;

@Service
public class InformeService implements InformeServiceInterfaz {

	@Autowired
	private InformeRepository informeRepository;
	@Autowired
	private EmpresaRepository empresaRepository;
	
	

}
