package com.erpweb.servicios.crm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpweb.dto.ClienteDto;
import com.erpweb.entidades.crm.Cliente;
import com.erpweb.entidades.embebidos.DireccionPostal;
import com.erpweb.entidades.embebidos.OrigenPersona;
import com.erpweb.repositorios.crm.ClienteRepository;
import com.erpweb.utiles.AccionRespuesta;



@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	
	private static final Logger logger = LoggerFactory.getLogger(ClienteService.class.getName());

	
	public AccionRespuesta crearClienteDesdeClienteDto(ClienteDto clienteDto) {
		
		logger.debug("Entramos en el metodo crearClienteDesdeClienteDto()" );
		
		Cliente cliente = new Cliente();
		
		cliente.setCodigo(clienteDto.getCodigo());
		cliente.setNombre(clienteDto.getNombre());
		cliente.setApellidoPrimero(clienteDto.getApellidoPrimero());
		cliente.setApellidoSegundo(clienteDto.getApellidoSegundo());
		cliente.setNif(clienteDto.getNif());
		cliente.setTipoCliente(clienteDto.getTipoCliente());
		
		//Direccion Postal
		cliente.setDireccionPostal(new DireccionPostal() );
		
		cliente.getDireccionPostal().setCodigoPostal(clienteDto.getCodigoPostal());
		cliente.getDireccionPostal().setDireccion(clienteDto.getDireccion());
		cliente.getDireccionPostal().setEdificio(clienteDto.getEdificio());
		cliente.getDireccionPostal().setObservaciones(clienteDto.getObservaciones());
		cliente.getDireccionPostal().setTelefono(clienteDto.getTelefono());
		
		//Origen Cliente
		cliente.setOrigenPersona(new OrigenPersona() );
		
		cliente.getOrigenPersona().setProvincia(clienteDto.getProvincia());
		cliente.getOrigenPersona().setPoblacion(clienteDto.getPoblacion());
		cliente.getOrigenPersona().setRegion(clienteDto.getRegion());
		cliente.getOrigenPersona().setPais(clienteDto.getPais());
	
		try {
			//Guardamos el cliente
			Cliente clienteSave = clienteRepository.save(cliente);
			
			return this.devolverDatosClienteDto(clienteDto, clienteSave);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo crearClienteDesdeClienteDto() con ID={}", clienteDto.getId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
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
		cliente.setTipoCliente(clienteDto.getTipoCliente());
		
		//Direccion Postal
		cliente.setDireccionPostal(new DireccionPostal() );
		
		cliente.getDireccionPostal().setCodigoPostal(clienteDto.getCodigoPostal());
		cliente.getDireccionPostal().setDireccion(clienteDto.getDireccion());
		cliente.getDireccionPostal().setEdificio(clienteDto.getEdificio());
		cliente.getDireccionPostal().setObservaciones(clienteDto.getObservaciones());
		cliente.getDireccionPostal().setTelefono(clienteDto.getTelefono());
		
		//Origen Cliente
		cliente.setOrigenPersona(new OrigenPersona() );
		
		cliente.getOrigenPersona().setProvincia(clienteDto.getProvincia());
		cliente.getOrigenPersona().setPoblacion(clienteDto.getPoblacion());
		cliente.getOrigenPersona().setRegion(clienteDto.getRegion());
		cliente.getOrigenPersona().setPais(clienteDto.getPais());
	
		try {
			//Guardamos el cliente
			clienteRepository.save(cliente);
			
			return this.devolverDatosActualizadosClienteDto(clienteDto, cliente);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo actualizarClienteDesdeClienteDto() con ID={}", clienteDto.getId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
	}
	
	public AccionRespuesta eliminarCliente(Cliente cliente) {
		
		logger.debug("Entramos en el metodo eliminarCliente()" );
		
		if(cliente == null || cliente.getId() == null) {
			
			logger.error("ERROR en el metodo eliminarCliente()");
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
		
		try {
			
			//Eliminamos el cliente
			clienteRepository.deleteById(cliente.getId());
			
			return new AccionRespuesta(-2L, "OK", Boolean.TRUE);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo eliminarCliente() con ID={}", cliente.getId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
	}
	
	public AccionRespuesta eliminarClientePorId(Long clienteId) {
		
		logger.error("Entramos en el metodo eliminarClientePorId()" );
		
		if( clienteId == null) {
			
			logger.error("ERROR en el metodo eliminarClientePorId()");
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
				
		try {
			
			//Elimnamos el cliente
			clienteRepository.deleteById(clienteId);
			
			return new AccionRespuesta(-2L, "OK", Boolean.TRUE);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo eliminarClientePorId() con id={}", clienteId );
			
			e.printStackTrace();
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
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
			clienteDto.setCodigoPostal(cliente.getDireccionPostal().getCodigoPostal());
			clienteDto.setDireccion(cliente.getDireccionPostal().getDireccion());
			clienteDto.setEdificio(cliente.getDireccionPostal().getEdificio());
			clienteDto.setTelefono(cliente.getDireccionPostal().getTelefono());
			clienteDto.setObservaciones(cliente.getDireccionPostal().getObservaciones());
			clienteDto.setProvincia(cliente.getOrigenPersona().getProvincia());
			clienteDto.setPoblacion(cliente.getOrigenPersona().getPoblacion());
			clienteDto.setRegion(cliente.getOrigenPersona().getRegion());
			clienteDto.setPais(cliente.getOrigenPersona().getPais());
			clienteDto.setTipoCliente(cliente.getTipoCliente());
			
		} catch(Exception e) {
			
			logger.error("Error en el metodo obtenerClienteDtoDesdeCliente() con ID={}", id );
			
			e.printStackTrace();
		}
		
		return clienteDto;
	}
	
	public List<ClienteDto> getListadoClientes() {
		
		logger.debug("Entramos en el metodo getListadoClientes()" );
		
		try {
			
			List<Cliente> clientes = clienteRepository.findAll();
			
			return this.obtieneListadoClienteDtoDelRepository(clientes);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo getListadoClientes()" );
			
			e.printStackTrace();
		}
		
		return new ArrayList<ClienteDto>();
	}
	
	public AccionRespuesta getCliente(Long clienteId) {
		
		logger.debug("Entramos en el metodo getCliente()");
		
		if( clienteId == null) {
			
			return new AccionRespuesta(-1L, "Error, NO existe el cliente", Boolean.FALSE);
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
	
	public AccionRespuesta getCrearEditarCliente(ClienteDto clienteDto) {
		
		logger.debug("Entramos en el metodo getCrearEditarCliente()" );
		
		if( clienteDto.getId() != null && clienteDto.getId().longValue() > 0) {
			
			logger.debug("Se va a realizar una actualizacion del Cliente");
			
			return this.actualizarClienteDesdeClienteDto(clienteDto);
			
		} else {
			
			logger.debug("Se va a crear un Cliente");
			
			return this.crearClienteDesdeClienteDto(clienteDto);
		}
	}
	
	private AccionRespuesta devolverDatosClienteDto(ClienteDto clienteDto, Cliente clienteSave) {
		
		AccionRespuesta respuesta = new AccionRespuesta();
		
		//Guardado el cliente se devuelve en su dto
		if(clienteSave != null && clienteSave.getId() != null) {
			
			clienteDto.setId(clienteSave.getId());
			
			respuesta.setId(clienteSave.getId());
			
			respuesta.setCodigo("OK");
						
			respuesta.setResultado(Boolean.TRUE);
			
			HashMap<String, Object> data= new HashMap<String, Object> ();
			
			data.put("clienteDto", clienteDto);
			
			respuesta.setData(data);
			
		}else {
			
			respuesta.setCodigo("NOK");
						
			respuesta.setResultado(Boolean.FALSE);
			
			HashMap<String, Object> data= new HashMap<String, Object> ();
			
			data.put("clienteDto", clienteDto);
			
			respuesta.setData(data);
		}
		
		return respuesta;
	}
	
	private AccionRespuesta devolverDatosActualizadosClienteDto(ClienteDto clienteDto, Cliente clienteSave) {
		
		AccionRespuesta respuesta = new AccionRespuesta();
		
		if(clienteSave != null && clienteDto != null) {
			
			respuesta.setId(clienteSave.getId());
			
			respuesta.setCodigo("OK");
						
			respuesta.setResultado(Boolean.TRUE);
			
			HashMap<String, Object> data= new HashMap<String, Object> ();
			
			data.put("clienteDto", clienteDto);
			
			respuesta.setData(data);
			
		}else {
			
			respuesta.setId(-1L);
			
			respuesta.setCodigo("NOK");
			
			respuesta.setResultado(Boolean.FALSE);
			
			HashMap<String, Object> data= new HashMap<String, Object> ();
			
			data.put("clienteDto", clienteDto);
			
			respuesta.setData(data);			
		}
		
		return respuesta;		
	}
	
	private List<ClienteDto> obtieneListadoClienteDtoDelRepository(List<Cliente> clientes){
		
		List<ClienteDto> clientesDto = new ArrayList<ClienteDto>();
		
		if(CollectionUtils.isNotEmpty(clientes) ) {
			
			for(Cliente cliente : clientes) {
				
				ClienteDto clienteDto = new ClienteDto();

				clienteDto.setId(cliente.getId());
				clienteDto.setCodigo(cliente.getCodigo());
				clienteDto.setNombre(cliente.getNombre());
				clienteDto.setApellidoPrimero(cliente.getApellidoPrimero());
				clienteDto.setApellidoSegundo(cliente.getApellidoSegundo());
				clienteDto.setNif(cliente.getNif());
				clienteDto.setCodigoPostal(cliente.getDireccionPostal().getCodigoPostal());
				clienteDto.setDireccion(cliente.getDireccionPostal().getDireccion());
				clienteDto.setEdificio(cliente.getDireccionPostal().getEdificio());
				clienteDto.setTelefono(cliente.getDireccionPostal().getTelefono());
				clienteDto.setObservaciones(cliente.getDireccionPostal().getObservaciones());
				clienteDto.setProvincia(cliente.getOrigenPersona().getProvincia());
				clienteDto.setPoblacion(cliente.getOrigenPersona().getPoblacion());
				clienteDto.setRegion(cliente.getOrigenPersona().getRegion());
				clienteDto.setPais(cliente.getOrigenPersona().getPais());
				clienteDto.setTipoCliente(cliente.getTipoCliente());
				
				clientesDto.add(clienteDto);				
			}
		}
		
		return clientesDto;
	}
	
	
	
}
