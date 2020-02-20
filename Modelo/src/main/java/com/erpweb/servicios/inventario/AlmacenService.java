package com.erpweb.servicios.inventario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpweb.dto.AlmacenDto;
import com.erpweb.entidades.empresa.Empresa;
import com.erpweb.entidades.inventario.Almacen;
import com.erpweb.repositorios.empresa.EmpresaRepository;
import com.erpweb.repositorios.inventario.AlmacenRepository;
import com.erpweb.servicios.inventario.interfaces.AlmacenServiceInterfaz;



@Service
public class AlmacenService implements AlmacenServiceInterfaz {

	@Autowired
	private AlmacenRepository almacenRepository;
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Override
	public Boolean creaAlmacenDesdeAlmacenDto(AlmacenDto almacenDto) {
		
		Almacen almacen = new Almacen();
		
		if(almacenDto.getEmpresaId() == null) {
			return Boolean.FALSE;
		}
		
		Empresa empresa = empresaRepository.findById(almacenDto.getEmpresaId()).orElse(new Empresa());
		
		almacen.setCodigo(almacenDto.getCodigo());
		almacen.setEmpresa(empresa);
		almacen.setNombre(almacenDto.getNombre());
		
		try {
			//Guardamos el almacen en base de datos
			almacenRepository.save(almacen);
			
		}catch(Exception e) {
			
			System.out.println("Error al guardar el almacen: " + almacenDto.getNombre() + "con el error: " + e.getLocalizedMessage() );
			
			return Boolean.FALSE;
		}
		
		return Boolean.TRUE;
	}

	@Override
	public AlmacenDto obtieneAlmacenDto(Long id, Long empresaId) {

		Almacen almacen = almacenRepository.findByIdAndEmpresaId(id, empresaId);
		
		if(almacen == null) {
			return new AlmacenDto();
		}
		
		AlmacenDto almacenDto = new AlmacenDto();
		
		almacenDto.setId(almacen.getId());
		almacenDto.setCodigo(almacen.getCodigo());
		almacenDto.setEmpresaId(almacen.getEmpresa().getId());
		almacenDto.setNombre(almacen.getNombre());
		
		return almacenDto;
	}

	@Override
	public Boolean actualizaAlmacen(AlmacenDto almacenDto) {

		Almacen almacen = new Almacen();
		
		if(almacenDto.getEmpresaId() == null) {
			return Boolean.FALSE;
		}
		
		Empresa empresa = empresaRepository.findById(almacenDto.getEmpresaId()).orElse(new Empresa());
		
		almacen.setId(almacenDto.getId());
		almacen.setCodigo(almacenDto.getCodigo());
		almacen.setEmpresa(empresa);
		almacen.setNombre(almacenDto.getNombre());
		
		try {
			//Guardamos el almacen en base de datos
			almacenRepository.save(almacen);
			
		}catch(Exception e) {
			
			System.out.println("Error al actualizar el almacen: " + almacenDto.getNombre() + "con el error: " + e.getLocalizedMessage() );
			
			return Boolean.FALSE;
		}
		
		return Boolean.TRUE;
	}

	@Override
	public Boolean eliminaAlmacen(Almacen almacen) {
		
		if(almacen == null || almacen.getId() == null) {
			return Boolean.FALSE;
		}
		
		try {
			//Elimnamos el gasto
			almacenRepository.deleteById(almacen.getId());
			
		}catch(Exception e) {
			
			System.out.println("Error al eliminar el almacen con ID " + almacen.getId() + " con error: " + e.getLocalizedMessage());
			
			return Boolean.FALSE;
		}
		
		return Boolean.TRUE;
	}

	@Override
	public Almacen obtieneAlmacen(Long id, Long empresaId) {
		
		Almacen almacen;
		
		try {
			//Recuperamos el gasto
			almacen = almacenRepository.findByIdAndEmpresaId(id, empresaId);
			
		}catch(Exception e) {
			
			System.out.println("Error: " + e.getLocalizedMessage());
			
			return new Almacen();
		}
		
		return almacen;
	}


}
