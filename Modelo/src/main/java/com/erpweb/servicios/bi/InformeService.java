package com.erpweb.servicios.bi;

import org.springframework.stereotype.Service;

import com.erpweb.dto.InformeDto;
import com.erpweb.entidades.bi.Informe;
import com.erpweb.servicios.bi.interfaces.InformeServiceInterfaz;

@Service
public class InformeService implements InformeServiceInterfaz {

	@Override
	public Boolean creaInformeDesdeInformeDto(InformeDto informeDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InformeDto obtieneInformeDto(Long id, Long empresaId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean actualizaInforme(InformeDto informeDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean eliminaInforme(Informe informe) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Informe obtieneInforme(Long id, Long empresaId) {
		// TODO Auto-generated method stub
		return null;
	}


	

}
