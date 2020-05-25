package com.erpweb.servicios.compras;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpweb.repositorios.compras.ProveedorRepository;
import com.erpweb.repositorios.empresa.EmpresaRepository;
import com.erpweb.servicios.compras.interfaces.ProveedorServiceInterfaz;



@Service
public class ProveedorService implements ProveedorServiceInterfaz {

	@Autowired
	private ProveedorRepository proveedorRepository;
	@Autowired
	private EmpresaRepository empresaRepository;
	
	
}
