package com.erpweb.servicios.inventario;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpweb.dto.AlmacenDto;
import com.erpweb.entidades.empresa.Empresa;
import com.erpweb.entidades.inventario.Almacen;
import com.erpweb.repositorios.empresa.EmpresaRepository;
import com.erpweb.repositorios.inventario.AlmacenRepository;
import com.erpweb.utiles.AccionRespuesta;



@Service
public class AlmacenService {

	@Autowired
	private AlmacenRepository almacenRepository;
	@Autowired
	private EmpresaRepository empresaRepository;
	
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	public AccionRespuesta crearAlmacenDesdeAlmacenDto(AlmacenDto almacenDto) {
		
		Almacen almacen = new Almacen();
		
		if(almacenDto.getEmpresaId() == null) {
			
			return new AccionRespuesta();
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
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public AccionRespuesta actualizarAlmacenDesdeAlmacenDto(AlmacenDto almacenDto) {
		
		Almacen almacen = new Almacen();
		
		if(almacenDto.getEmpresaId() == null) {
			
			return new AccionRespuesta();
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
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public AccionRespuesta eliminarAlmacen(Almacen almacen) {
		
		if(almacen == null || almacen.getId() == null) {
			
			return new AccionRespuesta();
		}
		
		try {
			//Elimnamos el gasto
			almacenRepository.deleteById(almacen.getId());
			
		}catch(Exception e) {
			
			System.out.println("Error al eliminar el almacen con ID " + almacen.getId() + " con error: " + e.getLocalizedMessage());
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public AlmacenDto obtenerAlmacenDtoDesdeAlmacen(Long id, Long empresaId) {
		
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

}
