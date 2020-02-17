package com.erpweb.servicios.ventas;

import org.springframework.stereotype.Service;

import com.erpweb.dto.ContratoDto;
import com.erpweb.entidades.ventas.Contrato;
import com.erpweb.servicios.ventas.interfaces.ContratoServiceInterfaz;



@Service
public class ContratoService implements ContratoServiceInterfaz {

	@Override
	public Boolean creaContratoDesdeContratoDto(ContratoDto contratoDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ContratoDto obtieneContratoDto(Long id, Long empresaId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean actualizaContrato(ContratoDto contatoDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean eliminaContrato(Contrato contato) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Contrato obtieneContrato(Long id, Long empresaId) {
		// TODO Auto-generated method stub
		return null;
	}


}
