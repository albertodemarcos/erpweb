package com.erpweb.servicios.configuracion.interfaces;

import com.erpweb.entidades.configuracionEmpresa.Empresa;
import com.erpweb.utiles.dao.EmpresaDao;

public interface EmpresaServiceInterfaz {

	public Empresa obtieneEmpresaDeEmpresaDao(EmpresaDao empresaDao);
	
	public EmpresaDao obtieneEmpresaDaoDeEmpresa(Empresa empresa);
	
	public void persisteEmpresa(Empresa empresa);
	
	public void destruyeEmpresa(Empresa empresa);
}
