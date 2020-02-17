package com.erpweb.servicios.bi;

import org.springframework.stereotype.Service;

import com.erpweb.dto.IngresoDto;
import com.erpweb.entidades.bi.Ingreso;
import com.erpweb.servicios.bi.interfaces.IngresoServiceInterfaz;

@Service
public class IngresoService implements IngresoServiceInterfaz {

	@Override
	public Boolean creaIngresoDesdeIngresoDto(IngresoDto ingresoDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IngresoDto obtieneIngresoDto(Long id, Long empresaId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean actualizaIngreso(IngresoDto ingresoDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean eliminaIngreso(Ingreso ingreso) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ingreso obtieneIngreso(Long id, Long empresaId) {
		// TODO Auto-generated method stub
		return null;
	}


	

}
