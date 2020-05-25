package com.erpweb.servicios.ventas;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.erpweb.dto.ContratoDto;
import com.erpweb.entidades.empresa.Empresa;
import com.erpweb.entidades.ventas.Contrato;
import com.erpweb.entidades.ventas.Factura;
import com.erpweb.repositorios.empresa.EmpresaRepository;
import com.erpweb.repositorios.ventas.ContratoRepository;
import com.erpweb.repositorios.ventas.FacturaRepository;
import com.erpweb.servicios.ventas.interfaces.ContratoServiceInterfaz;



@Service
public class ContratoService implements ContratoServiceInterfaz {

	@Autowired
	private ContratoRepository contratoRepository;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private FacturaRepository facturaRepository;
	
	


}
