package com.erpweb.servicios.crm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpweb.dto.ClienteDto;
import com.erpweb.entidades.comun.DireccionPostal;
import com.erpweb.entidades.comun.Poblacion;
import com.erpweb.entidades.comun.Provincia;
import com.erpweb.entidades.crm.Cliente;
import com.erpweb.entidades.empresa.Empresa;
import com.erpweb.repositorios.comun.DireccionPostalRepository;
import com.erpweb.repositorios.comun.PoblacionRepository;
import com.erpweb.repositorios.comun.ProvinciaRepository;
import com.erpweb.repositorios.crm.ClienteRepository;
import com.erpweb.repositorios.empresa.EmpresaRepository;
import com.erpweb.servicios.crm.interfaces.ClienteServiceInterfaz;



@Service
public class ClienteService implements ClienteServiceInterfaz {

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
	
	
	@Override
	public Boolean creaClienteDesdeClienteDto(ClienteDto clienteDto) {
		
		Cliente cliente = new Cliente();
		
		if(clienteDto == null) {
			return Boolean.FALSE;
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
			System.out.println("Error: " + e.getLocalizedMessage() );
			cliente.setDireccionPostal(null);
		}
		
		cliente.setTipoCliente(clienteDto.getTipoCliente());
	
		try {
			//Guardamos el cliente
			clienteRepository.save(cliente);
			
		}catch(Exception e) {
			System.out.println("Error: " + e.getLocalizedMessage() );
			return Boolean.FALSE;
		}
		
		return Boolean.TRUE;
	}

	@Override
	public ClienteDto obtieneClienteDto(Long id, Long empresaId) {
		
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
		
		return clienteDto;
		
	}

	@Override
	public Boolean actualizaCliente(ClienteDto clienteDto) {

		Cliente cliente = new Cliente();
		
		if(clienteDto == null) {
			return Boolean.FALSE;
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
			System.out.println("Error: " + e.getLocalizedMessage() );
			cliente.setDireccionPostal(null);
		}
		
		cliente.setTipoCliente(clienteDto.getTipoCliente());
	
		try {
			//Guardamos el cliente
			clienteRepository.save(cliente);
			
		}catch(Exception e) {
			System.out.println("Error: " + e.getLocalizedMessage() );
			return Boolean.FALSE;
		}
		
		return Boolean.TRUE;
	}
	
	@Override
	public Boolean eliminarClientePorId(Long id, Long empresaId) {
		
		Cliente cliente = null;
		
		if(id == null || empresaId == null) {
			return Boolean.FALSE;
		}
		
		
		try {
			
			cliente = clienteRepository.findByIdAndEmpresaId(id, empresaId);
			
		}catch(Exception e) {
			
			System.out.println("Error al eliminar la direccion postal de un cliente: " + e.getLocalizedMessage());
			
			return Boolean.FALSE;
		}
		
		
		DireccionPostal direccionPostal = cliente.getDireccionPostal();
		
		if(direccionPostal == null) {
			System.out.println("Error al eliminar la direccion postal del cliente: " + cliente.getId() );
			return Boolean.FALSE;
		}
		
		try {
			//Elimnamos la direccion postal primero
			direccionPostalRepository.deleteById(direccionPostal.getId());
			
		}catch(Exception e) {
			
			System.out.println("Error al eliminar la direccion postal de un cliente: " + e.getLocalizedMessage());
			
			return Boolean.FALSE;
		}
		
		try {
			
			//Eliminamos el cliente
			clienteRepository.deleteById(cliente.getId());
			
		}catch(Exception e) {
			
			System.out.println("Error al eliminar el cliente: " + e.getLocalizedMessage());
			
			return Boolean.FALSE;
		}
		
		return Boolean.TRUE;
	}

	@Override
	public Boolean eliminaCliente(Cliente cliente) {
		
		if(cliente == null || cliente.getId() == null) {
			return Boolean.FALSE;
		}
		
		DireccionPostal direccionPostal = cliente.getDireccionPostal();
		
		if(direccionPostal == null) {
			System.out.println("Error al eliminar la direccion postal del cliente: " + cliente.getId() );
			return Boolean.FALSE;
		}
		
		try {
			//Elimnamos la direccion postal primero
			direccionPostalRepository.deleteById(direccionPostal.getId());
			
		}catch(Exception e) {
			
			System.out.println("Error al eliminar la direccion postal de un cliente: " + e.getLocalizedMessage());
			
			return Boolean.FALSE;
		}
		
		try {
			
			//Eliminamos el cliente
			clienteRepository.deleteById(cliente.getId());
			
		}catch(Exception e) {
			
			System.out.println("Error al eliminar el cliente: " + e.getLocalizedMessage());
			
			return Boolean.FALSE;
		}
		
		return Boolean.TRUE;
	}

	@Override
	public Cliente obtieneCliente(Long id, Long empresaId) {
		
		Cliente cliente;
		
		try {
			//Recuperamos el gasto
			cliente = clienteRepository.findByIdAndEmpresaId(id, empresaId);
			
		}catch(Exception e) {
			
			System.out.println("Error al recuperar el cliente con ID: " + id + "de la empresa: " + empresaId + " con error localizado: " + e.getLocalizedMessage());
			
			return new Cliente();
		}
		
		return cliente;
	}

	

}
