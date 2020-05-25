package com.erpweb.servicios.inventario;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpweb.dto.ArticuloDto;
import com.erpweb.entidades.compras.Proveedor;
import com.erpweb.entidades.comun.Impuesto;
import com.erpweb.entidades.empresa.Empresa;
import com.erpweb.entidades.inventario.Almacen;
import com.erpweb.entidades.inventario.Articulo;
import com.erpweb.repositorios.compras.ProveedorRepository;
import com.erpweb.repositorios.comun.ImpuestoRepository;
import com.erpweb.repositorios.empresa.EmpresaRepository;
import com.erpweb.repositorios.inventario.AlmacenRepository;
import com.erpweb.repositorios.inventario.ArticuloRepository;
import com.erpweb.servicios.inventario.interfaces.ArticuloServiceInterfaz;



@Service
public class ArticuloService implements ArticuloServiceInterfaz {

	@Autowired
	private ArticuloRepository articuloRepository;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private ProveedorRepository proveedorRepository;
	
	@Autowired
	private ImpuestoRepository impuestoRepository;
	
	@Autowired
	private AlmacenRepository almacenRepository;
	
	
	


}
