package com.erpweb.servicios.generales;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpweb.dto.AlmacenDto;
import com.erpweb.dto.ArticuloDto;
import com.erpweb.entidades.inventario.Almacen;
import com.erpweb.entidades.inventario.Articulo;
import com.erpweb.repositorios.inventario.AlmacenRepository;
import com.erpweb.repositorios.inventario.ArticuloRepository;

@Service
public class AutocompletarService {

	//Repositorys
	@Autowired
	private AlmacenRepository almacenRepository;
	@Autowired
	private ArticuloRepository articuloRepository;
	
	//Otros
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//Almacenes
	public List<AlmacenDto> getListadoAlmacenesAutocompletar(String termino) {
		
		logger.debug("Entramos en el metodo getListadoAlmacenesAutocompletar()");
		
		try {
			
			List<Almacen> almacenes = almacenRepository.obtenerTodosLosAlmacenes("%"+termino.toLowerCase()+"%");
			
			return this.obtieneListadoAlmacenDtoDelRepository(almacenes);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo getListadoAlmacenes()" );
			
			e.printStackTrace();
		}
			
		return new ArrayList<AlmacenDto>();
	}
	
	//Articulos
	public List<ArticuloDto> getListadoArticulosAutocompletar(String termino) {

		logger.debug("Entramos en el metodo getListadoArticulosAutocompletar()" );
		
		try {
			
			List<Articulo> articulos = articuloRepository.obtenerTodosLosArticulos("%"+termino.toLowerCase()+"%");
			
			return this.obtieneListadoArticuloDtoDelRepository(articulos);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo getListadoArticulos()" );
			
			e.printStackTrace();
		}
			
		return new ArrayList<ArticuloDto>();
	}
	
	private List<AlmacenDto> obtieneListadoAlmacenDtoDelRepository(List<Almacen> almacenes){
		
		List<AlmacenDto> almacenesDto = new ArrayList<AlmacenDto>();
		
		if(CollectionUtils.isNotEmpty(almacenes) ) {
			
			for(Almacen almacen : almacenes) {
				
				AlmacenDto almacenDto = new AlmacenDto();
				
				almacenDto.setId(almacen.getId());
				almacenDto.setCodigo(almacen.getCodigo());
				almacenDto.setNombre(almacen.getNombre());
				
				almacenesDto.add(almacenDto);			
			}
		}
		
		return almacenesDto;
	}
	
	private List<ArticuloDto> obtieneListadoArticuloDtoDelRepository(List<Articulo> articulos){
		
		List<ArticuloDto> articulosDto = new ArrayList<ArticuloDto>();
		
		if(CollectionUtils.isNotEmpty(articulos) ) {
			
			for(Articulo articulo  : articulos) {
				
				ArticuloDto articuloDto = new ArticuloDto();
				
				articuloDto.setId(articulo.getId());
				articuloDto.setCodigo(articulo.getCodigo());
				articuloDto.setNombre(articulo.getNombre());
				
				articulosDto.add(articuloDto);				
			}
		}
		
		return articulosDto;
	}
	
	
}
