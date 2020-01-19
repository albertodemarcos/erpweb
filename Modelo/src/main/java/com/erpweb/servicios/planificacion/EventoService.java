package com.erpweb.servicios.planificacion;

import org.springframework.stereotype.Service;

import com.erpweb.entidades.planificacion.Evento;
import com.erpweb.servicios.planificacion.interfaces.EventoServiceInterfaz;
import com.erpweb.utiles.dao.EventoDao;



@Service
public class EventoService implements EventoServiceInterfaz {

	@Override
	public Evento obtieneEventoDeEventoDao(EventoDao eventoDao) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EventoDao obtieneEventoDaoDeEvento(Evento evento) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void persisteEvento(Evento evento) {
		// TODO Auto-generated method stub
	}

	@Override
	public void destruyeEvento(Evento evento) {
		// TODO Auto-generated method stub
	}

}
