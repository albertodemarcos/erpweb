package com.erpweb.servicios.inventario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpweb.repositorios.empresa.EmpresaRepository;
import com.erpweb.repositorios.inventario.AlmacenRepository;
import com.erpweb.servicios.inventario.interfaces.AlmacenServiceInterfaz;



@Service
public class AlmacenService implements AlmacenServiceInterfaz {

	@Autowired
	private AlmacenRepository almacenRepository;
	@Autowired
	private EmpresaRepository empresaRepository;
	
	
	
	

}
