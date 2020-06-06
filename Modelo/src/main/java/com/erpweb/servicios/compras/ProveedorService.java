package com.erpweb.servicios.compras;

import java.util.HashMap;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpweb.dto.ProveedorDto;
import com.erpweb.entidades.compras.Proveedor;
import com.erpweb.entidades.usuarios.Usuario;
import com.erpweb.repositorios.compras.ProveedorRepository;
import com.erpweb.utiles.AccionRespuesta;



@Service
public class ProveedorService {

	@Autowired
	private ProveedorRepository proveedorRepository;
	
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public AccionRespuesta crearProveedorDesdeProveedorDto(ProveedorDto proveedorDto) {
		
		logger.debug("Entramos en el metodo crearProveedorDesdeProveedorDto() con ID={}", proveedorDto.getId() );

		Proveedor proveedor = new Proveedor();
		
		proveedor.setCodigo(proveedorDto.getCodigo());
		proveedor.setNombre(proveedorDto.getNombre());
		proveedor.setNombreEmpresa(proveedorDto.getNombreEmpresa());
		proveedor.setTelefono(proveedorDto.getTelefono());
		proveedor.setTipoProveedor(proveedorDto.getTipoProveedor());
		
		try {
			//Guardamos el proveedor en base de datos
			proveedorRepository.save(proveedor);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo crearProveedorDesdeProveedorDto() con ID={}", proveedorDto.getId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public AccionRespuesta actualizarProveedorDesdeProveedorDto(ProveedorDto proveedorDto) {
		
		logger.debug("Entramos en el metodo actualizarProveedorDesdeProveedorDto() con ID={}", proveedorDto.getId() );

		Proveedor proveedor = new Proveedor();
		
		proveedor.setId(proveedorDto.getId());
		proveedor.setCodigo(proveedorDto.getCodigo());
		proveedor.setNombre(proveedorDto.getNombre());
		proveedor.setNombreEmpresa(proveedorDto.getNombreEmpresa());
		proveedor.setTelefono(proveedorDto.getTelefono());
		proveedor.setTipoProveedor(proveedorDto.getTipoProveedor());
		
		try {
			//Actualizamos el proveedor en base de datos
			proveedorRepository.save(proveedor);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo actualizarProveedorDesdeProveedorDto() con ID={}", proveedorDto.getId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public AccionRespuesta eliminarProveedor(Proveedor proveedor) {
		
		logger.debug("Entramos en el metodo eliminarProveedor() con ID={}", proveedor.getId() );
		
		if(proveedor == null || proveedor.getId() == null) {
			
			return new AccionRespuesta();
		}
		
		try {
			//Elimnamos el proveedor
			proveedorRepository.deleteById(proveedor.getId());
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo eliminarProveedor() con ID={}", proveedor.getId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public AccionRespuesta eliminarProveedorPorId(Long proveedorId) {
		
		logger.error("Entramos en el metodo eliminarProveedorPorId() con id={}", proveedorId );
				
		try {
			//Elimnamos el proveedor
			proveedorRepository.deleteById(proveedorId);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo eliminarProveedorPorId() con id={}", proveedorId );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public ProveedorDto obtenerProveedorDtoDesdeProveedor(Long id) {
		
		logger.debug("Entramos en el metodo obtenerProveedorDtoDesdeProveedor() con ID={}", id );
		
		Optional<Proveedor> proveedorOptional = proveedorRepository.findById(id );
		
		Proveedor proveedor = proveedorOptional.get();
		
		if(proveedor == null) {
			return new ProveedorDto();
		}
		
		ProveedorDto proveedorDto = new ProveedorDto();
		
		try {
			
			proveedorDto.setCodigo(proveedor.getCodigo());
			proveedorDto.setNombre(proveedor.getNombre());
			proveedorDto.setNombreEmpresa(proveedor.getNombreEmpresa());
			proveedorDto.setTelefono(proveedor.getTelefono());
			proveedorDto.setTipoProveedor(proveedor.getTipoProveedor());
			
		} catch(Exception e) {
			
			logger.error("Error en el metodo obtenerProveedorDtoDesdeProveedor() con ID={} ", id );
			
			e.printStackTrace();
		}
		
		return proveedorDto;
	}
	
	public AccionRespuesta getProveedor(Long proveedorId, Usuario user) {
		
		logger.debug("Entramos en el metodo getproveedor()");
		
		if( proveedorId == null) {
			
			return new AccionRespuesta(-1L, "Error, existe el proveedor", Boolean.FALSE);
		}
		
		ProveedorDto proveedorDto = this.obtenerProveedorDtoDesdeProveedor(proveedorId);
		
		AccionRespuesta AccionRespuesta = new AccionRespuesta();
		
		if( proveedorDto != null ) {
			
			AccionRespuesta.setId( proveedorDto.getId() );
			
			AccionRespuesta.setRespuesta("");
			
			AccionRespuesta.setResultado(Boolean.TRUE);
			
			HashMap<String, Object> mapa = new HashMap<String, Object>();
			
			mapa.put("proveedorDto", proveedorDto);
			
			AccionRespuesta.setData(new HashMap<String, Object>(mapa));
			
		}else {
			
			AccionRespuesta.setId( -1L );
			
			AccionRespuesta.setRespuesta("Error, no se ha podido recuperar el proveedor");
			
			AccionRespuesta.setResultado(Boolean.FALSE);
		}
		
		return AccionRespuesta;
	}
	
	public AccionRespuesta getCrearEditarProveedor(ProveedorDto proveedorDto, Usuario user) {
		
		logger.debug("Entramos en el metodo getCrearEditarProveedor() con usuario={}", user.getId() );
		
		if( proveedorDto.getId() != null && proveedorDto.getId().longValue() > 0) {
			
			logger.debug("Se va a realizar una actualizacion del Proveedor con usuario={}", user.getId() );
			
			return this.actualizarProveedorDesdeProveedorDto(proveedorDto);
			
		} else {
			
			logger.debug("Se va a crear un Proveedor con usuario={}", user.getId() );
			
			return this.crearProveedorDesdeProveedorDto(proveedorDto);
		}
	}
	
}
