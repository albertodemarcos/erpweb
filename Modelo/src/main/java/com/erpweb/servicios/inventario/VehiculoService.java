package com.erpweb.servicios.inventario;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpweb.repositorios.empresa.EmpresaRepository;
import com.erpweb.repositorios.inventario.VehiculoRepository;
import com.erpweb.servicios.inventario.interfaces.VehiculoServiceInterfaz;



@Service
public class VehiculoService implements VehiculoServiceInterfaz {

	@Autowired
	private VehiculoRepository vehiculoRepository;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	

}
