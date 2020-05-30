package com.erpweb.servicios.compras;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpweb.dto.ProveedorDto;
import com.erpweb.entidades.compras.Proveedor;
import com.erpweb.entidades.empresa.Empresa;
import com.erpweb.repositorios.compras.ProveedorRepository;
import com.erpweb.repositorios.empresa.EmpresaRepository;
import com.erpweb.utiles.AccionRespuesta;



@Service
public class ProveedorService {

	@Autowired
	private ProveedorRepository proveedorRepository;
	@Autowired
	private EmpresaRepository empresaRepository;
	
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public AccionRespuesta crearProveedorDesdeProveedorDto(ProveedorDto proveedorDto) {
		
		logger.debug("Entramos en el metodo crearProveedorDesdeProveedorDto() con la empresa={}", proveedorDto.getEmpresaId() );

		Proveedor proveedor = new Proveedor();
		
		if(proveedorDto.getEmpresaId() == null) {
			
			return new AccionRespuesta();
		}
		
		Empresa empresa = empresaRepository.findById(proveedorDto.getEmpresaId()).orElse(new Empresa());
		
		proveedor.setCodigo(proveedorDto.getCodigo());
		proveedor.setEmpresa(empresa);
		proveedor.setNombre(proveedorDto.getNombre());
		proveedor.setNombreEmpresa(proveedorDto.getNombreEmpresa());
		proveedor.setTelefono(proveedorDto.getTelefono());
		proveedor.setArticulo(proveedorDto.getArticulo());
		proveedor.setCantidad(proveedorDto.getCantidad());
		proveedor.setTipoProveedor(proveedorDto.getTipoProveedor());
		
		try {
			//Guardamos el proveedor en base de datos
			proveedorRepository.save(proveedor);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo crearProveedorDesdeProveedorDto() con la empresa{} ", proveedorDto.getEmpresaId() );
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public AccionRespuesta actualizarProveedorDesdeProveedorDto(ProveedorDto proveedorDto) {
		
		logger.debug("Entramos en el metodo actualizarProveedorDesdeProveedorDto() con la empresa={}", proveedorDto.getEmpresaId() );

		Proveedor proveedor = new Proveedor();
		
		if(proveedorDto.getEmpresaId() == null) {
			
			return new AccionRespuesta();
		}
		
		Empresa empresa = empresaRepository.findById(proveedorDto.getEmpresaId()).orElse(new Empresa());
		
		proveedor.setId(proveedorDto.getId());
		proveedor.setCodigo(proveedorDto.getCodigo());
		proveedor.setEmpresa(empresa);
		proveedor.setNombre(proveedorDto.getNombre());
		proveedor.setNombreEmpresa(proveedorDto.getNombreEmpresa());
		proveedor.setTelefono(proveedorDto.getTelefono());
		proveedor.setArticulo(proveedorDto.getArticulo());
		proveedor.setCantidad(proveedorDto.getCantidad());
		proveedor.setTipoProveedor(proveedorDto.getTipoProveedor());
		
		try {
			//Actualizamos el proveedor en base de datos
			proveedorRepository.save(proveedor);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo actualizarProveedorDesdeProveedorDto() con la empresa{} ", proveedorDto.getEmpresaId() );
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public AccionRespuesta eliminarProveedor(Proveedor proveedor) {
		
		logger.debug("Entramos en el metodo eliminarProveedor() con la empresa={}", proveedor.getId() );
		
		if(proveedor == null || proveedor.getId() == null) {
			
			return new AccionRespuesta();
		}
		
		try {
			//Elimnamos el proveedor
			proveedorRepository.deleteById(proveedor.getId());
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo eliminarProveedor() con la empresa{} ", proveedor.getId() );
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	
	public ProveedorDto obtenerProveedorDtoDesdeProveedor(Long id, Long empresaId) {
		
		logger.debug("Entramos en el metodo obtenerProveedorDtoDesdeProveedor() con la empresa={}", empresaId );
		
		Proveedor proveedor = proveedorRepository.findByIdAndEmpresaId(id, empresaId);
		
		if(proveedor == null) {
			return new ProveedorDto();
		}
		
		ProveedorDto proveedorDto = new ProveedorDto();
		
		proveedorDto.setCodigo(proveedor.getCodigo());
		proveedorDto.setEmpresaId(empresaId);
		proveedorDto.setNombre(proveedor.getNombre());
		proveedorDto.setNombreEmpresa(proveedor.getNombreEmpresa());
		proveedorDto.setTelefono(proveedor.getTelefono());
		proveedorDto.setArticulo(proveedor.getArticulo());
		proveedorDto.setCantidad(proveedor.getCantidad());
		proveedorDto.setTipoProveedor(proveedor.getTipoProveedor());
		
		logger.error("Error en el metodo obtenerProveedorDtoDesdeProveedor() con la empresa{} ", empresaId );
		
		return proveedorDto;
	}
}
