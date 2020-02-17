package com.erpweb.servicios.bi;

import org.springframework.stereotype.Service;

import com.erpweb.dto.GastoDto;
import com.erpweb.entidades.bi.Gasto;
import com.erpweb.repositorios.bi.GastoRepository;
import com.erpweb.servicios.bi.interfaces.GastoServiceInterfaz;

@Service
public class GastoService implements GastoServiceInterfaz {

	@Override
	public Boolean creaGastoDesdeGastoDto(GastoDto gastoDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GastoDto obtieneGastoDto(Long id, Long empresaId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean actualizaGasto(GastoDto gastoDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean eliminaGasto(Gasto gasto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Gasto obtieneGasto(Long id, Long empresaId) {
		// TODO Auto-generated method stub
		return null;
	}



	
	
}
