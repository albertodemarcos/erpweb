package com.erpweb.servicios.compras;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpweb.dto.ProveedorDto;
import com.erpweb.entidades.compras.Proveedor;
import com.erpweb.entidades.empresa.Empresa;
import com.erpweb.repositorios.compras.ProveedorRepository;
import com.erpweb.repositorios.empresa.EmpresaRepository;
import com.erpweb.servicios.compras.interfaces.ProveedorServiceInterfaz;



@Service
public class ProveedorService implements ProveedorServiceInterfaz {

	@Autowired
	private ProveedorRepository proveedorRepository;
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Override
	public Boolean creaProveedorDesdeProveedorDto(ProveedorDto proveedorDto) {
		
		Proveedor proveedor = new Proveedor();
		
		if(proveedorDto.getEmpresaId() == null) {
			return Boolean.FALSE;
		}
		
		Empresa empresa = empresaRepository.findOne(proveedorDto.getEmpresaId());
		
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
			
			System.out.println("Error: " + e.getLocalizedMessage() );
			
			return Boolean.FALSE;
		}
		
		return Boolean.TRUE;
	}

	@Override
	public ProveedorDto obtieneProveedorDto(Long id, Long empresaId) {
		
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
		
		return proveedorDto;
	}

	@Override
	public Boolean actualizaProveedor(ProveedorDto proveedorDto) {

		Proveedor proveedor = new Proveedor();
		
		if(proveedorDto.getEmpresaId() == null) {
			return Boolean.FALSE;
		}
		
		Empresa empresa = empresaRepository.findOne(proveedorDto.getEmpresaId());
		
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
			
			System.out.println("Error: " + e.getLocalizedMessage() );
			
			return Boolean.FALSE;
		}
		
		return Boolean.TRUE;
	}

	@Override
	public Boolean eliminaProveedor(Proveedor proveedor) {
		
		if(proveedor == null || proveedor.getId() == null) {
			return Boolean.FALSE;
		}
		
		try {
			//Elimnamos el proveedor
			proveedorRepository.deleteById(proveedor.getId());
			
		}catch(Exception e) {
			
			System.out.println("Error: " + e.getLocalizedMessage());
			
			return Boolean.FALSE;
		}
		
		return Boolean.TRUE;
	}

	@Override
	public Proveedor obtieneProveedor(Long id, Long empresaId) {
		
		Proveedor proveedor;
		
		try {
			//Recuperamos el proveedor
			proveedor = proveedorRepository.findByIdAndEmpresaId(id, empresaId);
			
		}catch(Exception e) {
			
			System.out.println("Error: " + e.getLocalizedMessage());
			
			return new Proveedor();
		}
		
		return proveedor;
	}
	
}
