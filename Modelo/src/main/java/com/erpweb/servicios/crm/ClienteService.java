package com.erpweb.servicios.crm;

import java.util.HashMap;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpweb.dto.ClienteDto;
import com.erpweb.entidades.crm.Cliente;
import com.erpweb.entidades.usuarios.Usuario;
import com.erpweb.repositorios.crm.ClienteRepository;
import com.erpweb.utiles.AccionRespuesta;



@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	
	public AccionRespuesta crearClienteDesdeClienteDto(ClienteDto clienteDto) {
		
		logger.debug("Entramos en el metodo crearClienteDesdeClienteDto() con ID={}", clienteDto.getId() );
		
		Cliente cliente = new Cliente();
		
		cliente.setCodigo(clienteDto.getCodigo());
		cliente.setNombre(clienteDto.getNombre());
		cliente.setApellidoPrimero(clienteDto.getApellidoPrimero());
		cliente.setApellidoSegundo(clienteDto.getApellidoSegundo());
		cliente.setNif(clienteDto.getNif());
		cliente.setCodigoPostal(clienteDto.getCodigoPostal());
		cliente.setDireccion(clienteDto.getDireccion());
		cliente.setEdificio(clienteDto.getEdificio());
		cliente.setObservaciones(clienteDto.getObservaciones());
		cliente.setTelefono(clienteDto.getTelefono());
		cliente.setProvincia(clienteDto.getProvincia());
		cliente.setPoblacion(clienteDto.getPoblacion());
		cliente.setRegion(clienteDto.getRegion());
		cliente.setPais(clienteDto.getPais());
		cliente.setTipoCliente(clienteDto.getTipoCliente());
	
		try {
			//Guardamos el cliente
			clienteRepository.save(cliente);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo crearClienteDesdeClienteDto() con ID={}", clienteDto.getId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public AccionRespuesta actualizarClienteDesdeClienteDto(ClienteDto clienteDto) {
		
		logger.debug("Entramos en el metodo actualizarClienteDesdeClienteDto() con ID={}", clienteDto.getId() );
		
		Cliente cliente = new Cliente();
		
		
		cliente.setId(clienteDto.getId());
		cliente.setCodigo(clienteDto.getCodigo());
		cliente.setNombre(clienteDto.getNombre());
		cliente.setApellidoPrimero(clienteDto.getApellidoPrimero());
		cliente.setApellidoSegundo(clienteDto.getApellidoSegundo());
		cliente.setNif(clienteDto.getNif());
		cliente.setCodigoPostal(clienteDto.getCodigoPostal());
		cliente.setDireccion(clienteDto.getDireccion());
		cliente.setEdificio(clienteDto.getEdificio());
		cliente.setObservaciones(clienteDto.getObservaciones());
		cliente.setTelefono(clienteDto.getTelefono());
		cliente.setProvincia(clienteDto.getProvincia());
		cliente.setPoblacion(clienteDto.getPoblacion());
		cliente.setRegion(clienteDto.getRegion());
		cliente.setPais(clienteDto.getPais());
		cliente.setTipoCliente(clienteDto.getTipoCliente());
	
		try {
			//Guardamos el cliente
			clienteRepository.save(cliente);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo actualizarClienteDesdeClienteDto() con ID={}", clienteDto.getId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public AccionRespuesta eliminarCliente(Cliente cliente) {
		
		logger.debug("Entramos en el metodo eliminarCliente() con ID={}", cliente.getId() );
		
		if(cliente == null || cliente.getId() == null) {
			
			return new AccionRespuesta();
		}
		
		try {
			
			//Eliminamos el cliente
			clienteRepository.deleteById(cliente.getId());
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo eliminarCliente() con ID={}", cliente.getId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public AccionRespuesta eliminarClientePorId(Long clienteId) {
		
		logger.error("Entramos en el metodo eliminarClientePorId() con id={}", clienteId );
				
		try {
			
			//Elimnamos el cliente
			clienteRepository.deleteById(clienteId);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo eliminarClientePorId() con id={}", clienteId );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		
		return new AccionRespuesta();
	}
	
	public ClienteDto obtenerClienteDtoDesdeCliente(Long id) {
		
		logger.debug("Entramos en el metodo obtenerClienteDtoDesdeCliente() con ID={}", id );
		
		Optional<Cliente> clienteOptional = clienteRepository.findById(id);
		
		Cliente cliente = clienteOptional.get();
		
		if(cliente == null) {
			return new ClienteDto();
		}
		
		ClienteDto clienteDto = new ClienteDto();
		
		try {
			
			clienteDto.setId(cliente.getId());
			clienteDto.setCodigo(cliente.getCodigo());
			clienteDto.setNombre(cliente.getNombre());
			clienteDto.setApellidoPrimero(cliente.getApellidoPrimero());
			clienteDto.setApellidoSegundo(cliente.getApellidoSegundo());
			clienteDto.setNif(cliente.getNif());
			clienteDto.setCodigoPostal(cliente.getCodigoPostal());
			clienteDto.setDireccion(cliente.getDireccion());
			clienteDto.setEdificio(cliente.getEdificio());
			clienteDto.setTelefono(cliente.getTelefono());
			clienteDto.setObservaciones(cliente.getObservaciones());
			clienteDto.setProvincia(cliente.getProvincia());
			clienteDto.setPoblacion(cliente.getPoblacion());
			clienteDto.setRegion(cliente.getRegion());
			clienteDto.setPais(cliente.getPais());
			clienteDto.setTipoCliente(cliente.getTipoCliente());
			
		} catch(Exception e) {
			
			logger.error("Error en el metodo obtenerClienteDtoDesdeCliente() con ID={}", id );
			
			e.printStackTrace();
		}
		
		return clienteDto;
	}
	
	public AccionRespuesta getCliente(Long clienteId, Usuario user) {
		
		logger.debug("Entramos en el metodo getCliente()");
		
		if( clienteId == null) {
			
			return new AccionRespuesta(-1L, "Error, existe el cliente", Boolean.FALSE);
		}
		
		ClienteDto clienteDto = this.obtenerClienteDtoDesdeCliente(clienteId);
		
		AccionRespuesta AccionRespuesta = new AccionRespuesta();
		
		if( clienteDto != null ) {
			
			AccionRespuesta.setId( clienteDto.getId() );
			
			AccionRespuesta.setRespuesta("");
			
			AccionRespuesta.setResultado(Boolean.TRUE);
			
			HashMap<String, Object> mapa = new HashMap<String, Object>();
			
			mapa.put("clienteDto", clienteDto);
			
			AccionRespuesta.setData(new HashMap<String, Object>(mapa));
			
		}else {
			
			AccionRespuesta.setId( -1L );
			
			AccionRespuesta.setRespuesta("Error, no se ha podido recuperar el cliente");
			
			AccionRespuesta.setResultado(Boolean.FALSE);
		}
		
		return AccionRespuesta;
	}
	
	public AccionRespuesta getCrearEditarCliente(ClienteDto clienteDto, Usuario user) {
		
		logger.debug("Entramos en el metodo getCrearEditarCliente() con usuario={}", user.getId() );
		
		if( clienteDto.getId() != null && clienteDto.getId().longValue() > 0) {
			
			logger.debug("Se va a realizar una actualizacion del Cliente con usuario={}", user.getId() );
			
			return this.actualizarClienteDesdeClienteDto(clienteDto);
			
		} else {
			
			logger.debug("Se va a crear un Cliente con usuario={}", user.getId() );
			
			return this.crearClienteDesdeClienteDto(clienteDto);
		}
	}

}
