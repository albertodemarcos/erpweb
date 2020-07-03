package com.erpweb.servicios.planificador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpweb.dto.EventoDto;
import com.erpweb.entidades.planificador.Evento;
import com.erpweb.entidades.usuarios.Usuario;
import com.erpweb.repositorios.planificador.EventoRepository;
import com.erpweb.utiles.AccionRespuesta;

@Service
public class EventoService {

	@Autowired
	private EventoRepository eventoRepository;
	
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public AccionRespuesta crearEventoDesdeEventoDto(EventoDto eventoDto) {
		
		logger.debug("Entramos en el metodo crearEventoDesdeEventoDto() " );

		Evento evento = new Evento();
		
		evento.setTitulo(eventoDto.getTitulo());
		evento.setFechaInicio(eventoDto.getFechaInicio());
		evento.setFechaFin(eventoDto.getFechaFin());
		evento.setDescripcion(eventoDto.getDescripcion());
		
		try {
			//Guardamos el proveedor en base de datos
			Evento eventoSave = eventoRepository.save(evento);
			
			return this.devolverDatosEventoDto(eventoDto, eventoSave);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo crearEventoDesdeEventoDto()" );
			
			e.printStackTrace();
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
	}
	
	public AccionRespuesta actualizarEventoDesdeEventoDto(EventoDto eventoDto) {
		
		logger.debug("Entramos en el metodo actualizarEventoDesdeEventoDto() con ID={}", eventoDto.getId() );

		Evento evento = new Evento();
		
		evento.setId(eventoDto.getId());
		evento.setTitulo(eventoDto.getTitulo());
		evento.setFechaInicio(eventoDto.getFechaInicio());
		evento.setFechaFin(eventoDto.getFechaFin());
		evento.setDescripcion(eventoDto.getDescripcion());
		
		try {
			//Actualizamos el proveedor en base de datos
			Evento eventoSave = eventoRepository.save(evento);

			return this.devolverDatosActualizadosEventoDto(eventoDto, eventoSave);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo actualizarEventoDesdeEventoDto() con ID={}", eventoDto.getId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
	}
	
	public AccionRespuesta eliminarEventoPorId(Long eventoId) {
		
		logger.error("Entramos en el metodo eliminarEventoPorId()" );
		
		if(eventoId == null) {
			
			logger.error("ERROR en el metodo eliminarEventoPorId()");
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
				
		try {
			//Elimnamos el proveedor
			eventoRepository.deleteById(eventoId);
			
			return new AccionRespuesta(-2L, "OK", Boolean.TRUE);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo eliminarEventoPorId() con id={}", eventoId );
			
			e.printStackTrace();
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
	}
	
	public EventoDto obtenerEventoDtoDesdeEvento(Long id) {
		
		logger.debug("Entramos en el metodo obtenerEventoDtoDesdeEvento() con ID={}", id );
		
		Optional<Evento> eventoOptional = eventoRepository.findById(id );
		
		Evento evento = eventoOptional.get();
		
		if(evento == null) {
			
			return new EventoDto();
		}
		
		EventoDto eventoDto = new EventoDto();
		
		try {
			
			eventoDto.setId(evento.getId());
			eventoDto.setTitulo(evento.getTitulo());
			eventoDto.setFechaInicio(evento.getFechaInicio());
			eventoDto.setFechaFin(evento.getFechaFin());
			eventoDto.setDescripcion(evento.getDescripcion());
			
		} catch(Exception e) {
			
			logger.error("Error en el metodo obtenerEventoDtoDesdeEvento() con ID={} ", id );
			
			e.printStackTrace();
		}
		
		return eventoDto;
	}
	
	public List<EventoDto> getListadoEventos() {
		
		logger.debug("Entramos en el metodo getListado()" );
		
		try {
			
			List<Evento> eventos = eventoRepository.findAll();
			
			return this.obtieneListadoEventoDtoDelRepository(eventos);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo getListado()" );
			
			e.printStackTrace();
		}
			
		return new ArrayList<EventoDto>();
	}
	
	public AccionRespuesta getEvento(Long eventoId, Usuario user) {
		
		logger.debug("Entramos en el metodo getproveedor()");
		
		if( eventoId == null) {
			
			return new AccionRespuesta(-1L, "Error, existe el proveedor", Boolean.FALSE);
		}
		
		EventoDto eventoDto = this.obtenerEventoDtoDesdeEvento(eventoId);
		
		AccionRespuesta AccionRespuesta = new AccionRespuesta();
		
		if( eventoDto != null ) {
			
			AccionRespuesta.setId( eventoDto.getId() );
			
			AccionRespuesta.setRespuesta("");
			
			AccionRespuesta.setResultado(Boolean.TRUE);
			
			HashMap<String, Object> mapa = new HashMap<String, Object>();
			
			mapa.put("eventoDto", eventoDto);
			
			AccionRespuesta.setData(new HashMap<String, Object>(mapa));
			
		}else {
			
			AccionRespuesta.setId( -1L );
			
			AccionRespuesta.setRespuesta("Error, no se ha podido recuperar el proveedor");
			
			AccionRespuesta.setResultado(Boolean.FALSE);
		}
		
		return AccionRespuesta;
	}
	
	public AccionRespuesta getCrearEditarEvento(EventoDto eventoDto, Usuario user) {
		
		logger.debug("Entramos en el metodo getCrearEditarProveedor() con usuario={}", user.getId() );
		
		if( eventoDto.getId() != null && eventoDto.getId().longValue() > 0) {
			
			logger.debug("Se va a realizar una actualizacion del Proveedor con usuario={}", user.getId() );
			
			return this.actualizarEventoDesdeEventoDto(eventoDto);
			
		} else {
			
			logger.debug("Se va a crear un Proveedor con usuario={}", user.getId() );
			
			return this.crearEventoDesdeEventoDto(eventoDto);
		}
	}
	
	private AccionRespuesta devolverDatosEventoDto(EventoDto eventoDto, Evento eventoSave) {
		
		AccionRespuesta respuesta = new AccionRespuesta();
		
		//Guardado el cliente se devuelve en su dto
		if(eventoSave != null && eventoSave.getId() != null) {
			
			eventoDto.setId(eventoSave.getId());
			
			respuesta.setId(eventoSave.getId());
			
			respuesta.setCodigo("OK");
						
			respuesta.setResultado(Boolean.TRUE);
			
			HashMap<String, Object> data= new HashMap<String, Object> ();
			
			data.put("eventoDto", eventoDto);
			
			respuesta.setData(data);
			
		}else {
			
			respuesta.setCodigo("NOK");
						
			respuesta.setResultado(Boolean.FALSE);
			
			HashMap<String, Object> data= new HashMap<String, Object> ();
			
			data.put("eventoDto", eventoDto);
			
			respuesta.setData(data);
		}
		
		return respuesta;
	}
	
	private AccionRespuesta devolverDatosActualizadosEventoDto(EventoDto eventoDto, Evento eventoSave) {
		
		AccionRespuesta respuesta = new AccionRespuesta();
		
		if(eventoDto != null && eventoSave != null) {
			
			eventoDto.setId(eventoSave.getId());
			
			respuesta.setId(eventoSave.getId());
			
			respuesta.setCodigo("OK");
						
			respuesta.setResultado(Boolean.TRUE);
			
			HashMap<String, Object> data= new HashMap<String, Object> ();
			
			data.put("eventoDto", eventoDto);
			
			respuesta.setData(data);
			
		}else {
			
			respuesta.setId(-1L);
			
			respuesta.setCodigo("NOK");
			
			respuesta.setResultado(Boolean.FALSE);
			
			HashMap<String, Object> data= new HashMap<String, Object> ();
			
			data.put("eventoDto", eventoDto);
			
			respuesta.setData(data);			
		}
		
		return respuesta;		
	}
	
	private List<EventoDto> obtieneListadoEventoDtoDelRepository(List<Evento> eventos){
		
		List<EventoDto> eventosDto = new ArrayList<EventoDto>();
		
		if(CollectionUtils.isNotEmpty(eventos) ) {
			
			for(Evento evento  : eventos) {
				
				EventoDto eventoDto = new EventoDto();
				
				eventoDto.setId(evento.getId());
				eventoDto.setTitulo(evento.getTitulo());
				eventoDto.setFechaInicio(evento.getFechaInicio());
				eventoDto.setFechaFin(evento.getFechaFin());
				eventoDto.setDescripcion(evento.getDescripcion());
				
				eventosDto.add(eventoDto);		
			}
		}
		
		return eventosDto;
	}
	
	
}
