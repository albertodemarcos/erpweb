package com.erpweb.servicios.inventario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpweb.dto.ArticuloDto;
import com.erpweb.entidades.inventario.Articulo;
import com.erpweb.entidades.usuarios.Usuario;
import com.erpweb.repositorios.inventario.ArticuloRepository;
import com.erpweb.utiles.AccionRespuesta;



@Service
public class ArticuloService {

	@Autowired
	private ArticuloRepository articuloRepository;
	
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	public AccionRespuesta crearArticuloDesdeArticuloDto(ArticuloDto articuloDto) {
		
		logger.debug("Entramos en el metodo crearArticuloDesdeArticuloDto() con ID={}", articuloDto.getId() );
		
		Articulo articulo = new Articulo();
		
		articulo.setCodigo(articuloDto.getCodigo());
		articulo.setNombre(articuloDto.getNombre());
		articulo.setDescripcion(articuloDto.getDescripcion());
		articulo.setBaseImponible(articuloDto.getBaseImponible());
		articulo.setImpuesto(articuloDto.getImpuesto());
		articulo.setImporteTotal(articuloDto.getImporteTotal());
		
		try {
			
			//Guardamos el articulo en base de datos
			articuloRepository.save(articulo);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo crearArticuloDesdeArticuloDto() con ID={}", articuloDto.getId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public AccionRespuesta actualizarArticuloDesdeArticuloDto(ArticuloDto articuloDto) {
		
		logger.debug("Entramos en el metodo actualizarArticuloDesdeArticuloDto() con ID={}", articuloDto.getId() );
		
		Articulo articulo = new Articulo();
		
		
		articulo.setId(articuloDto.getId());
		articulo.setCodigo(articuloDto.getCodigo());
		articulo.setNombre(articuloDto.getNombre());
		articulo.setDescripcion(articuloDto.getDescripcion());
		articulo.setBaseImponible(articuloDto.getBaseImponible());
		articulo.setImpuesto(articuloDto.getImpuesto());
		articulo.setImporteTotal(articuloDto.getImporteTotal());
		
		try {
			//Guardamos el articulo en base de datos
			articuloRepository.save(articulo);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo actualizarArticuloDesdeArticuloDto() con ID={}", articuloDto.getId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public AccionRespuesta eliminarArticulo(Articulo articulo) {
		
		logger.debug("Entramos en el metodo eliminarArticulo() con la empresa={}", articulo.getId() );
		
		if(articulo == null || articulo.getId() == null) {
			
			return new AccionRespuesta();
		}
		
		try {
			//Elimnamos el articulo
			articuloRepository.deleteById(articulo.getId());
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo eliminarArticulo() con la empresa{} ", articulo.getId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public AccionRespuesta eliminarArticuloPorId(Long articuloId) {
		
		logger.error("Entramos en el metodo eliminarArticuloPorId() con id={}", articuloId );
				
		try {
			
			//Elimnamos el articulo
			articuloRepository.deleteById(articuloId);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo eliminarArticuloPorId() con id={}", articuloId );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		
		return new AccionRespuesta();
	}
	
	public ArticuloDto obtenerArticuloDtoDesdeArticulo(Long id) {
		
		logger.debug("Entramos en el metodo obtenerArticuloDtoDesdeArticulo() con ID={}", id );
		
		Optional<Articulo> articuloOptional = articuloRepository.findById(id);
		
		Articulo articulo = articuloOptional.get();
		
		if(articulo == null) {
			return new ArticuloDto();
		}
		
		ArticuloDto articuloDto = new ArticuloDto();
		
		try {
			
			articuloDto.setId(articulo.getId());
			articuloDto.setCodigo(articulo.getCodigo());
			articuloDto.setNombre(articulo.getNombre());
			articuloDto.setDescripcion(articulo.getDescripcion());
			articuloDto.setBaseImponible(articulo.getBaseImponible());
			articuloDto.setImpuesto(articulo.getImpuesto());
			articuloDto.setImporteTotal(articulo.getImporteTotal());
			
		} catch(Exception e) {
			
			logger.error("Error en el metodo obtenerArticuloDtoDesdeArticulo() con ID={} ", id );
			
			e.printStackTrace();
		}
		
		return articuloDto;
	}
	
	public List<ArticuloDto> getListadoArticulos() {
		
		logger.debug("Entramos en el metodo getListadoArticulos()" );
		
		try {
			
			List<Articulo> articulos = articuloRepository.findAll();
			
			return this.obtieneListadoArticuloDtoDelRepository(articulos);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo getListadoArticulos()" );
			
			e.printStackTrace();
		}
			
		return new ArrayList<ArticuloDto>();
	}
	
	public AccionRespuesta getArticulo(Long articuloId, Usuario user) {
		
		logger.debug("Entramos en el metodo getArticulo()");
		
		if( articuloId == null) {
			
			return new AccionRespuesta(-1L, "Error, existe el articulo", Boolean.FALSE);
		}
		
		ArticuloDto articuloDto = this.obtenerArticuloDtoDesdeArticulo(articuloId);
		
		AccionRespuesta AccionRespuesta = new AccionRespuesta();
		
		if( articuloDto != null ) {
			
			AccionRespuesta.setId( articuloDto.getId() );
			
			AccionRespuesta.setRespuesta("");
			
			AccionRespuesta.setResultado(Boolean.TRUE);
			
			HashMap<String, Object> mapa = new HashMap<String, Object>();
			
			mapa.put("articuloDto", articuloDto);
			
			AccionRespuesta.setData(new HashMap<String, Object>(mapa));
			
		}else {
			
			AccionRespuesta.setId( -1L );
			
			AccionRespuesta.setRespuesta("Error, no se ha podido recuperar el articulo");
			
			AccionRespuesta.setResultado(Boolean.FALSE);
		}
		
		return AccionRespuesta;
	}
	
	public AccionRespuesta getCrearEditarArticulo(ArticuloDto articuloDto, Usuario user) {
		
		logger.debug("Entramos en el metodo getCrearEditarArticulo() con usuario={}", user.getId() );
		
		if( articuloDto.getId() != null && articuloDto.getId().longValue() > 0) {
			
			logger.debug("Se va a realizar una actualizacion del Articulo con usuario={}", user.getId() );
			
			return this.actualizarArticuloDesdeArticuloDto(articuloDto);
			
		} else {
			
			logger.debug("Se va a crear un Articulo con usuario={}", user.getId() );
			
			return this.crearArticuloDesdeArticuloDto(articuloDto);
		}
	}
	
	private List<ArticuloDto> obtieneListadoArticuloDtoDelRepository(List<Articulo> articulos){
		
		List<ArticuloDto> articulosDto = new ArrayList<ArticuloDto>();
		
		if(CollectionUtils.isNotEmpty(articulos) ) {
			
			for(Articulo articulo  : articulos) {
				
				ArticuloDto articuloDto = new ArticuloDto();
				
				articuloDto.setId(articulo.getId());
				articuloDto.setCodigo(articulo.getCodigo());
				articuloDto.setNombre(articulo.getNombre());
				articuloDto.setDescripcion(articulo.getDescripcion());
				articuloDto.setBaseImponible(articulo.getBaseImponible());
				articuloDto.setImpuesto(articulo.getImpuesto());
				articuloDto.setImporteTotal(articulo.getImporteTotal());
				
				articulosDto.add(articuloDto);				
			}
		}
		
		return articulosDto;
	}

}
