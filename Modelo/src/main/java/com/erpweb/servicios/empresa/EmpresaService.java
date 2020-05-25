package com.erpweb.servicios.empresa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpweb.dto.EmpresaDto;
import com.erpweb.entidades.empresa.Empresa;
import com.erpweb.repositorios.empresa.EmpresaRepository;
import com.erpweb.servicios.empresa.interfaces.EmpresaServiceInterfaz;



@Service
public class EmpresaService implements EmpresaServiceInterfaz {

	@Autowired
	private EmpresaRepository empresaRepository;
	
	

}
