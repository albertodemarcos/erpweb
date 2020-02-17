package com.erpweb.servicios.empresa;

import org.springframework.stereotype.Service;

import com.erpweb.dto.EmpresaDto;
import com.erpweb.entidades.empresa.Empresa;
import com.erpweb.servicios.empresa.interfaces.EmpresaServiceInterfaz;



@Service
public class EmpresaService implements EmpresaServiceInterfaz {

	@Override
	public Boolean creaEmpresaDesdeEmpresaDto(EmpresaDto empresaDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmpresaDto obtieneEmpresaDto(Long id, Long empresaId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean actualizaEmpresa(EmpresaDto empresaDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean eliminaEmpresa(Empresa empresa) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Empresa obtieneEmpresa(Long id, Long empresaId) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
