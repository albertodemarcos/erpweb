package com.erpweb.servicios.configuracion;

import org.springframework.stereotype.Service;

import com.erpweb.entidades.configuracionEmpresa.Empresa;
import com.erpweb.servicios.configuracion.interfaces.EmpresaServiceInterfaz;
import com.erpweb.utiles.dao.EmpresaDao;



@Service
public class EmpresaService implements EmpresaServiceInterfaz {

	@Override
	public Empresa obtieneEmpresaDeEmpresaDao(EmpresaDao empresaDao) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmpresaDao obtieneEmpresaDaoDeEmpresa(Empresa empresa) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void persisteEmpresa(Empresa empresa) {
		// TODO Auto-generated method stub
	}

	@Override
	public void destruyeEmpresa(Empresa empresa) {
		// TODO Auto-generated method stub
	}

	

}
