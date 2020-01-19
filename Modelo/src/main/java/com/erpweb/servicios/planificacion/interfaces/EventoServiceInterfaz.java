package com.erpweb.servicios.planificacion.interfaces;

import com.erpweb.entidades.planificacion.Evento;
import com.erpweb.utiles.dao.EventoDao;

public interface EventoServiceInterfaz {

	public Evento obtieneEventoDeEventoDao(EventoDao eventoDao);
	
	public EventoDao obtieneEventoDaoDeEvento(Evento evento);
	
	public void persisteEvento(Evento evento);
	
	public void destruyeEvento(Evento evento);
}
