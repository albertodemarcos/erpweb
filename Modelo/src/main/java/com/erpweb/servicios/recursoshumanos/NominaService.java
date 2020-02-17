package com.erpweb.servicios.recursoshumanos;

import org.springframework.stereotype.Service;

import com.erpweb.dto.NominaDto;
import com.erpweb.entidades.recursoshumanos.Nomina;
import com.erpweb.servicios.recursoshumanos.interfaces.NominaServiceInterfaz;

@Service
public class NominaService implements NominaServiceInterfaz {

	@Override
	public Boolean creaNominaDesdeNominaDto(NominaDto nominaDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NominaDto obtieneNominaDto(Long id, Long empresaId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean actualizaNomina(NominaDto nominaDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean eliminaNomina(Nomina nomina) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Nomina obtieneNomina(Long id, Long empresaId) {
		// TODO Auto-generated method stub
		return null;
	}


}
