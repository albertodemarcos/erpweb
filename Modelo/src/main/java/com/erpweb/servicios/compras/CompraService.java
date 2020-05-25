package com.erpweb.servicios.compras;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpweb.repositorios.compras.CompraRepository;
import com.erpweb.repositorios.empresa.EmpresaRepository;
import com.erpweb.servicios.compras.interfaces.CompraServiceInterfaz;



@Service
public class CompraService implements CompraServiceInterfaz{

	@Autowired
	private CompraRepository compraRepository;
	@Autowired
	private EmpresaRepository empresaRepository;
	
	

}
