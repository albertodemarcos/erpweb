package com.erpweb.servicios.ventas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpweb.repositorios.empresa.EmpresaRepository;
import com.erpweb.repositorios.ventas.FacturaRepository;
import com.erpweb.repositorios.ventas.VentaRepository;
import com.erpweb.servicios.ventas.interfaces.VentaServiceInterfaz;



@Service
public class VentaService implements VentaServiceInterfaz {

	@Autowired
	private VentaRepository ventaRepository;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private FacturaRepository facturaRepository;
	
	

}
