package com.erpweb.servicios.crm;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpweb.dto.ClienteDto;
import com.erpweb.entidades.comun.DireccionPostal;
import com.erpweb.entidades.comun.Poblacion;
import com.erpweb.entidades.comun.Provincia;
import com.erpweb.entidades.crm.Cliente;
import com.erpweb.entidades.empresa.Empresa;
import com.erpweb.entidades.usuarios.Usuario;
import com.erpweb.repositorios.comun.DireccionPostalRepository;
import com.erpweb.repositorios.comun.PoblacionRepository;
import com.erpweb.repositorios.comun.ProvinciaRepository;
import com.erpweb.repositorios.crm.ClienteRepository;
import com.erpweb.repositorios.empresa.EmpresaRepository;
import com.erpweb.utiles.AccionRespuesta;



@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private DireccionPostalRepository direccionPostalRepository;
	
	@Autowired
	private ProvinciaRepository provinciaRepository;
	
	@Autowired
	private PoblacionRepository poblacionRepository;
	
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public AccionRespuesta crearClienteDesdeClienteDto(ClienteDto clienteDto) {
		
		logger.debug("Entramos en el metodo crearClienteDesdeClienteDto() con la empresa={}", clienteDto.getEmpresaId() );
		
		Cliente cliente = new Cliente();
		
		if(clienteDto == null || clienteDto.getEmpresaId() == null ) {
			
			return new AccionRespuesta();
		}
		
		Empresa empresa = empresaRepository.findById(clienteDto.getEmpresaId()).orElse(new Empresa());
		
		cliente.setCodigo(clienteDto.getCodigo());
		cliente.setEmpresa(empresa);
		cliente.setNombre(clienteDto.getNombre());
		cliente.setApellidoPrimero(clienteDto.getApellidoPrimero());
		cliente.setApellidoSegundo(clienteDto.getApellidoSegundo());
		cliente.setNif(clienteDto.getNif());
		
		//Buscamos la provinicia
		Provincia provincia = provinciaRepository.findById(clienteDto.getProvinciaId()).orElse(new Provincia());
		
		//Buscamos la poblacion
		Poblacion poblacion = poblacionRepository.findById(clienteDto.getPoblacionId()).orElse(new Poblacion());
		
		//Rellenamos la direccion postal
		DireccionPostal direccionPostal = new DireccionPostal();
		
		direccionPostal.setCodigo(clienteDto.getCodigoDireccionPostal());
		direccionPostal.setCodigoPostal(clienteDto.getCodigoPostal());
		direccionPostal.setDireccion(clienteDto.getDireccion());
		direccionPostal.setEdificio(clienteDto.getEdificio());
		direccionPostal.setObservaciones(clienteDto.getObservaciones());
		direccionPostal.setTelefono(clienteDto.getTelefono());
		
		direccionPostal.setProvincia(provincia);
		direccionPostal.setPoblacion(poblacion);
		
		try {
			//Guardamos la direccion postal del cliente
			direccionPostalRepository.save(direccionPostal);
			
		}catch(Exception e) {
			//Error, el cliente se queda sin direccion postal por un error
			//pero permitimos que continue la ejecucion
			logger.error("Error en el metodo crearClienteDesdeClienteDto() con la empresa{} ", clienteDto.getEmpresaId() );
			
			e.printStackTrace();
			
			cliente.setDireccionPostal(null);			
		}
		
		cliente.setTipoCliente(clienteDto.getTipoCliente());
	
		try {
			//Guardamos el cliente
			clienteRepository.save(cliente);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo crearClienteDesdeClienteDto() con la empresa{} ", clienteDto.getEmpresaId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public AccionRespuesta actualizarClienteDesdeClienteDto(ClienteDto clienteDto) {
		
		logger.debug("Entramos en el metodo actualizarClienteDesdeClienteDto() con la empresa={}", clienteDto.getEmpresaId() );
		
		Cliente cliente = new Cliente();
		
		if( clienteDto == null  || clienteDto.getEmpresaId() == null ) {
			
			return new AccionRespuesta();
		}
		
		Empresa empresa = empresaRepository.findById(clienteDto.getEmpresaId()).orElse(new Empresa());
		
		cliente.setId(clienteDto.getId());
		cliente.setCodigo(clienteDto.getCodigo());
		cliente.setEmpresa(empresa);
		cliente.setNombre(clienteDto.getNombre());
		cliente.setApellidoPrimero(clienteDto.getApellidoPrimero());
		cliente.setApellidoSegundo(clienteDto.getApellidoSegundo());
		cliente.setNif(clienteDto.getNif());
		
		//Buscamos la provinicia
		Provincia provincia = provinciaRepository.findById(clienteDto.getProvinciaId()).orElse(new Provincia());
		
		//Buscamos la poblacion
		Poblacion poblacion = poblacionRepository.findById(clienteDto.getPoblacionId()).orElse(new Poblacion());
		
		//Rellenamos la direccion postal
		DireccionPostal direccionPostal = new DireccionPostal();
		
		direccionPostal.setId(clienteDto.getDireccionPostalId());
		direccionPostal.setCodigo(clienteDto.getCodigoDireccionPostal());
		direccionPostal.setCodigoPostal(clienteDto.getCodigoPostal());
		direccionPostal.setDireccion(clienteDto.getDireccion());
		direccionPostal.setEdificio(clienteDto.getEdificio());
		direccionPostal.setObservaciones(clienteDto.getObservaciones());
		direccionPostal.setTelefono(clienteDto.getTelefono());
		
		direccionPostal.setProvincia(provincia);
		direccionPostal.setPoblacion(poblacion);
		
		try {
			//Guardamos la direccion postal del cliente
			direccionPostalRepository.save(direccionPostal);
			
		}catch(Exception e) {
			//Error, el cliente se queda sin direccion postal por un error
			//pero permitimos que continue la ejecucion
			logger.error("Error en el metodo actualizarClienteDesdeClienteDto() con la empresa{} ", clienteDto.getEmpresaId() );
			
			e.printStackTrace();
			
			cliente.setDireccionPostal(null);
		}
		
		cliente.setTipoCliente(clienteDto.getTipoCliente());
	
		try {
			//Guardamos el cliente
			clienteRepository.save(cliente);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo actualizarClienteDesdeClienteDto() con la empresa{} ", clienteDto.getEmpresaId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public AccionRespuesta eliminarCliente(Cliente cliente) {
		
		logger.debug("Entramos en el metodo eliminarCliente() con la empresa={}", cliente.getEmpresa().getId() );
		
		if(cliente == null || cliente.getId() == null) {
			
			return new AccionRespuesta();
		}
		
		DireccionPostal direccionPostal = cliente.getDireccionPostal();
		
		if(direccionPostal == null) {
			
			logger.error("Error en el metodo eliminarCliente() con la empresa{} ", cliente.getEmpresa().getId() );
			
			return new AccionRespuesta();
		}
		
		try {
			//Elimnamos la direccion postal primero
			direccionPostalRepository.deleteById(direccionPostal.getId());
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo eliminarCliente() con la empresa{} ", cliente.getEmpresa().getId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		try {
			
			//Eliminamos el cliente
			clienteRepository.deleteById(cliente.getId());
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo eliminarCliente() con la empresa{} ", cliente.getEmpresa().getId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public ClienteDto obtenerClienteDtoDesdeCliente(Long id, Long empresaId) {
		
		logger.debug("Entramos en el metodo obtenerClienteDtoDesdeCliente() con la empresa={}", empresaId );
		
		Cliente cliente = clienteRepository.findByIdAndEmpresaId(id, empresaId);
		
		//Rellenamos la direccion postal
		DireccionPostal direccionPostal = direccionPostalRepository.findByIdAndEmpresaId(id, empresaId);
		
		//Buscamos la provinicia
		Provincia provincia = direccionPostal.getProvincia() != null ? direccionPostal.getProvincia() : new Provincia();
		
		//Buscamos la poblacion
		Poblacion poblacion = direccionPostal.getPoblacion()!= null ? direccionPostal.getPoblacion() : new Poblacion();
		
		if(cliente == null) {
			return new ClienteDto();
		}
		
		ClienteDto clienteDto = new ClienteDto();
		
		try {
			
			clienteDto.setId(cliente.getId());
			clienteDto.setCodigo(cliente.getCodigo());
			clienteDto.setEmpresaId(cliente.getEmpresa().getId());
			clienteDto.setNombre(cliente.getNombre());
			clienteDto.setApellidoPrimero(cliente.getApellidoPrimero());
			clienteDto.setApellidoSegundo(cliente.getApellidoSegundo());
			clienteDto.setNif(cliente.getNif());
			
			clienteDto.setDireccionPostalId(direccionPostal.getId());
			clienteDto.setCodigoDireccionPostal(direccionPostal.getCodigo());
			clienteDto.setCodigoPostal(direccionPostal.getCodigoPostal());
			clienteDto.setDireccion(direccionPostal.getDireccion());
			clienteDto.setEdificio(direccionPostal.getEdificio());
			clienteDto.setTelefono(direccionPostal.getTelefono());
			clienteDto.setObservaciones(direccionPostal.getObservaciones());
			clienteDto.setTipoCliente(cliente.getTipoCliente());
			
			clienteDto.setPoblacionId(provincia.getId() !=null ? provincia.getId() : 0L );
			clienteDto.setNombrePoblacion(provincia.getNombre() != null ? provincia.getNombre() : "SIN_PROVINCIA");
			
			clienteDto.setProvinciaId(poblacion.getId() !=null ? poblacion.getId() : 0L);
			clienteDto.setNombreProvincia(poblacion.getNombre() !=null ? poblacion.getNombre() : "SIN_POBLACION");
			
		} catch(Exception e) {
			
			logger.error("Error en el metodo obtenerClienteDtoDesdeCliente() con la empresa{} ", empresaId );
			
			e.printStackTrace();
		}
		
		return clienteDto;
	}
	
	public AccionRespuesta getCliente(Long clienteId, Usuario user) {
		
		logger.debug("Entramos en el metodo getCliente()");
		
		if( clienteId == null) {
			
			return new AccionRespuesta(-1L, "Error, existe el cliente", Boolean.FALSE);
		}
		
		ClienteDto clienteDto = this.obtenerClienteDtoDesdeCliente(clienteId, user.getEmpresa().getId());
		
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

}
