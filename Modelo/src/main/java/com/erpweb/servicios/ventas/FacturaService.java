package com.erpweb.servicios.ventas;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.erpweb.dto.FacturaDto;
import com.erpweb.entidades.empresa.Empresa;
import com.erpweb.entidades.ventas.Factura;
import com.erpweb.repositorios.empresa.EmpresaRepository;
import com.erpweb.repositorios.ventas.FacturaRepository;
import com.erpweb.servicios.ventas.interfaces.FacturaServiceInterfaz;



@Service
public class FacturaService implements FacturaServiceInterfaz {

	@Autowired
	private FacturaRepository facturaRepository;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	
	


}
