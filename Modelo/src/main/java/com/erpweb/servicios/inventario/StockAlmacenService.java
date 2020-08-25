package com.erpweb.servicios.inventario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erpweb.dto.AlmacenDto;
import com.erpweb.dto.ArticuloDto;
import com.erpweb.dto.StockArticuloDto;
import com.erpweb.entidades.inventario.Almacen;
import com.erpweb.entidades.inventario.Articulo;
import com.erpweb.entidades.inventario.StockArticulo;
import com.erpweb.repositorios.inventario.AlmacenRepository;
import com.erpweb.repositorios.inventario.ArticuloRepository;
import com.erpweb.repositorios.inventario.StockArticuloRepository;
import com.erpweb.utiles.AccionRespuesta;

@Service
public class StockAlmacenService {

	@Autowired
	private AlmacenRepository almacenRepository;
	@Autowired
	private ArticuloRepository articuloRepository;
	@Autowired
	private StockArticuloRepository stockArticuloRepository;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	
	/* METODOS Stock General Almacen */
	
	public AccionRespuesta getStockArticulo(Long stockAlmacenId) {
		
		logger.debug("Entramos en el metodo getStockArticulo()");
		
		if( stockAlmacenId == null) {
			
			return new AccionRespuesta(-1L, "Error, existe el stock del almacen", Boolean.FALSE);
		}
		
		StockArticulo stockArticulo = this.obtieneStockArticulo(stockAlmacenId);
		
		StockArticuloDto stockArticuloDto = this.obtieneStockArticuloDtoDeStockArticulo(stockArticulo);
		
		AccionRespuesta AccionRespuesta = new AccionRespuesta();
		
		if( stockArticuloDto != null ) {
			
			AccionRespuesta.setId( stockArticuloDto.getId() );
			
			AccionRespuesta.setRespuesta("");
			
			AccionRespuesta.setResultado(Boolean.TRUE);
			
			HashMap<String, Object> mapa = new HashMap<String, Object>();
			
			mapa.put("stockArticuloDto", stockArticuloDto);
			
			AccionRespuesta.setData(new HashMap<String, Object>(mapa));
			
		}else {
			
			AccionRespuesta.setId( -1L );
			
			AccionRespuesta.setRespuesta("Error, no se ha podido recuperar el stock del almacen");
			
			AccionRespuesta.setResultado(Boolean.FALSE);
		}
		
		return AccionRespuesta;
	}
	
	
	public List<StockArticuloDto> getListadoStockArticulos() {
		
		logger.debug("Entramos en el metodo getListadoStockArticulos()" );
		
		try {
			
			List<StockArticulo> stockArticulos = stockArticuloRepository.findAll();
			
			return this.obtieneListadoStockArticuloDtoDelRepository(stockArticulos);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo getListadoStockArticulos()" );
			
			e.printStackTrace();
		}
			
		return new ArrayList<StockArticuloDto>();
	}
	
	/*public AccionRespuesta crerStockAlmacen(StockArticuloDto stockArticuloDto) {
		
		return null;
	}
	
	public AccionRespuesta actualizarStockAlmacen(StockArticuloDto stockArticuloDto) {
		
		return null;
	}*/
	
	public AccionRespuesta eliminarStockAlmacen(StockArticuloDto stockArticuloDto) {
		
		return null;
	}
	
	
	/* METODOS Stock Articulo en Almacen */
	
	@Transactional
	public AccionRespuesta crearActualizaStockArticuloAlmacen(StockArticuloDto stockArticuloDto, Boolean actualizarStockArticulo) {
		
		logger.trace("Entramos en el metodo crearActualizaStockArticuloAlmacen()");
		
		Almacen almacen = null;
		Articulo articulo = null;
		
		//1º Buscamos el almacen
		
		try {
			
			Long almacenId = stockArticuloDto.getAlmacenId();
			
			logger.trace("Buscamos el almacen con id={}", almacenId );
			
			Optional<Almacen> almacenOptional = almacenRepository.findById(almacenId);
			
			almacen = almacenOptional.orElse(null);
			
		} catch(Exception e) {
			
			logger.error("Error, el almacen solicitado no existe");
			
			e.printStackTrace();
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
		
		//2º Buscamos el articulo
		
		try {
			
			Long articuloId = stockArticuloDto.getArticuloId();
			
			logger.trace("Buscamos el articulo con id={}", articuloId );
			
			Optional<Articulo> articuloOptional = articuloRepository.findById(articuloId);
			
			articulo = articuloOptional.orElse(null);
			
		} catch(Exception e) {
			
			logger.error("Error, el articulo solicitado no existe");
			
			e.printStackTrace();
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
		
		//3º Realizamos la asociacion
		if( almacen != null && articulo != null ) {
			
			//1º Primero asociamos el articulo al almacen
			logger.trace("Primero asociamos el almacen con el articulo" );
			
			try {
				
				almacen.getArticulos().add(articulo);
				
				almacen = almacenRepository.save(almacen);
				
			} catch(Exception e) {
				
				logger.error("Error, al asociar el articulo al almacen con id={} con el articulo con id={}",almacen.getId(), articulo.getId());
				
				e.printStackTrace();
				
				return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
			}
			
			//2º Segundo asociamos el almacen al articulo
			logger.trace("Segundo asociamos el articulo con el almacen" );
			
			try {
				
				articulo.getAlmacenes().add(almacen);
				
				articulo = articuloRepository.save(articulo);
				
			} catch(Exception e) {
				
				logger.error("Error, al asociar el articulo al el articulo con id={} con almacen con id={}", articulo.getId(), almacen.getId());
				
				e.printStackTrace();
				
				return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
			}
			
			//True -> actualizar, Null/False -> crear
			if( BooleanUtils.isTrue(actualizarStockArticulo) ) {
				
				logger.trace("Entramos a actualizar el stock del articulo con su almacen" );
				
				return this.actualizarStockArticuloAlmacen(stockArticuloDto, almacen, articulo);
			}
			
			logger.trace("Entramos a crear el stock del articulo con su almacen" );
			
			return this.crearStockArticuloAlmacen(stockArticuloDto, almacen, articulo);
		}
		
		return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
	}
	
	public AccionRespuesta eliminarStockArticuloAlmacenId(Long stockArticuloId) {
		
		logger.trace("Entramos en el metodo eliminarStockArticuloAlmacen()" );
		
		if( stockArticuloId == null) {
			
			logger.error("ERROR en el metodo eliminarAlmacenPorId()");
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
				
		try {
			
			//Elimnamos el stock de un articulo de un almacen
			stockArticuloRepository.deleteById( stockArticuloId );
			
			return new AccionRespuesta(-2L, "OK", Boolean.TRUE);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo eliminarAlmacenPorId() con id={}", stockArticuloId );
			
			e.printStackTrace();
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
	}
	
	private AccionRespuesta crearStockArticuloAlmacen(StockArticuloDto stockArticuloDto, Almacen almacen, Articulo articulo) {
		
		//3º Creamos el stock del articulo con su almacen correspondiente
		logger.trace("Creamos el stock del articulo con su almacen correspondiente" );

		try {
			//Creamos el stock para persistirlo en base de datos
			StockArticulo stockArticulo = new StockArticulo();
			
			//Rellenamos
			stockArticulo.setAlmacen( almacen );
			stockArticulo.setArticulo( articulo );
			stockArticulo.setCodigo( stockArticuloDto.getCodigo() );
			stockArticulo.setCantidad( stockArticuloDto.getCantidad() );
			
			//Persistimos el stock
			StockArticulo stockArticuloSave = stockArticuloRepository.save(stockArticulo);
			
			stockArticuloDto.setId(stockArticuloSave.getId());
			stockArticuloDto.setArticuloDto(this.obtieneArticuloDtoDeArticulo(articulo));
			stockArticuloDto.setAlmacenDto(this.obtieneAlmacenDtoDeAlmacen(almacen));
			
			return this.devolverDatosStockArticuloDto(stockArticuloDto, stockArticuloSave);
			
		} catch(Exception e) {
			
			logger.error("Error, no se ha podido registrar el stock del articulo");
			
			e.printStackTrace();
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
	}
	
	private AccionRespuesta actualizarStockArticuloAlmacen(StockArticuloDto stockArticuloDto, Almacen almacen, Articulo articulo) {
		
		//3º Actualizamos el stock del articulo con su almacen correspondiente
		logger.trace("Actualizamos el stock del articulo con su almacen correspondiente");
		
		try {
			
			StockArticulo stockArticuloSave = this.obtieneStockArticulo( stockArticuloDto.getId() );
			
			stockArticuloDto.setId(stockArticuloSave.getId());
			stockArticuloDto.setCodigo(stockArticuloSave.getCodigo());
			stockArticuloDto.setArticuloDto(this.obtieneArticuloDtoDeArticulo(articulo));
			stockArticuloDto.setAlmacenDto(this.obtieneAlmacenDtoDeAlmacen(almacen));
			stockArticuloDto.setCantidad(stockArticuloSave.getCantidad());
			
			return this.devolverDatosStockArticuloDto(stockArticuloDto, stockArticuloSave);
			
		} catch(Exception e) {
			
			logger.error("Error, no se ha podido registrar el stock del articulo");
			
			e.printStackTrace();
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
	}
	
	
	//Cantidad de stock para un articulo de un almacen
	
	public AccionRespuesta cantidadStockArticuloPorAlmacenIdAndArticuloId( Long almacenId, Long articuloId ) {
		
		logger.trace("Entramos en el metodo cantidadStockArticuloPorAlmacenIdAndArticuloId()");
		
		if( ( almacenId == null || almacenId.longValue() <= 0 ) || ( articuloId == null || articuloId.longValue() <= 0 ) ) {
			
			logger.error("Error, no se han enviado correctamente los datos para buscar la cantidad");
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
		
		try {
			
			Long cantidad = stockArticuloRepository.obtieneCantidadArticuloAlmacen(almacenId, articuloId);
			
			if( cantidad == null || cantidad.longValue() <= 0) {
				
				return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
			}
			
			HashMap<String, Object> data = new HashMap<String, Object>();
			
			data.put("cantidad", cantidad);
			
			return new AccionRespuesta(1L, "OK", Boolean.TRUE, data);
			
		}catch(Exception e) {
			
			logger.error("Error, no se ha podido encontrar la cantidad del stock del articulo");
			
			e.printStackTrace();
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
	}
	
	
	/* METODOS AUXILIARES */
	
	private StockArticulo obtieneStockArticulo(Long id ) {
			
		Optional<StockArticulo> stockArticulo = stockArticuloRepository.findById( id );
		
		return stockArticulo.orElse(null);
	}
	
	private AccionRespuesta devolverDatosStockArticuloDto(StockArticuloDto stockArticuloDto, StockArticulo stockArticuloSave) {
		
		AccionRespuesta respuesta = new AccionRespuesta();
		
		//Guardado el cliente se devuelve en su dto
		if(stockArticuloSave != null && stockArticuloSave.getId() != null) {
			
			stockArticuloDto.setId(stockArticuloSave.getId());
			
			respuesta.setId(stockArticuloSave.getId());
			
			respuesta.setCodigo("OK");
						
			respuesta.setResultado(Boolean.TRUE);
			
			HashMap<String, Object> data = new HashMap<String, Object> ();
			
			data.put("stockArticuloDto", stockArticuloDto);
			
			respuesta.setData(data);
			
		}else {
			
			respuesta.setCodigo("NOK");
						
			respuesta.setResultado(Boolean.FALSE);
			
			HashMap<String, Object> data = new HashMap<String, Object>();
			
			data.put("stockArticuloDto", stockArticuloDto);
			
			respuesta.setData(data);
		}
		
		return respuesta;
	}
	
	private List<StockArticuloDto> obtieneListadoStockArticuloDtoDelRepository(List<StockArticulo> stockArticulos){
		
		logger.debug("Entramos en el metodo obtieneListadoStockArticuloDtoDelRepository()" );
		
		List<StockArticuloDto> stockArticulosDto = new ArrayList<StockArticuloDto>();
		
		if(CollectionUtils.isNotEmpty(stockArticulos) ) {
			
			for(StockArticulo stockArticulo  : stockArticulos) {
				
				StockArticuloDto stockArticuloDto = new StockArticuloDto();
				
				stockArticuloDto.setId(stockArticulo.getId());
				stockArticuloDto.setCodigo(stockArticulo.getCodigo());
				stockArticuloDto.setArticuloId(stockArticulo.getArticulo().getId());
				stockArticuloDto.setAlmacenId(stockArticulo.getAlmacen().getId());
				stockArticuloDto.setArticuloDto(this.obtieneArticuloDtoDeArticulo(stockArticulo.getArticulo()));
				stockArticuloDto.setAlmacenDto(this.obtieneAlmacenDtoDeAlmacen(stockArticulo.getAlmacen()));
				stockArticuloDto.setCantidad(stockArticulo.getCantidad());
				
				stockArticulosDto.add(stockArticuloDto);				
			}
		}
		
		return stockArticulosDto;
	}
	
	private StockArticuloDto obtieneStockArticuloDtoDeStockArticulo(StockArticulo stockArticulo) {
		
		StockArticuloDto stockArticuloDto = new StockArticuloDto();
		
		stockArticuloDto.setId(stockArticulo.getId());
		stockArticuloDto.setCodigo(stockArticulo.getCodigo());
		stockArticuloDto.setCantidad(stockArticulo.getCantidad());
		stockArticuloDto.setAlmacenDto(this.obtieneAlmacenDtoDeAlmacen(stockArticulo.getAlmacen()));
		stockArticuloDto.setArticuloDto(this.obtieneArticuloDtoDeArticulo(stockArticulo.getArticulo()));		
		
		return stockArticuloDto;
	}
	
	private AlmacenDto obtieneAlmacenDtoDeAlmacen(Almacen almacen) {
		
		AlmacenDto almacenDto = new AlmacenDto();
		
		almacenDto.setId(almacen.getId());
		almacenDto.setCodigo(almacen.getCodigo());
		almacenDto.setNombre(almacen.getNombre());
		almacenDto.setCodigoPostal(almacen.getCodigoPostal());
		almacenDto.setDireccion(almacen.getDireccion());
		almacenDto.setEdificio(almacen.getEdificio());
		almacenDto.setObservaciones(almacen.getObservaciones());
		almacenDto.setTelefono(almacen.getTelefono());
		almacenDto.setPoblacion(almacen.getPoblacion());
		almacenDto.setProvincia(almacen.getProvincia());
		almacenDto.setRegion(almacen.getRegion());
		almacenDto.setPais(almacen.getPais());
		
		return almacenDto;
	}
	
	private ArticuloDto obtieneArticuloDtoDeArticulo(Articulo articulo) {
		
		ArticuloDto articuloDto = new ArticuloDto();
		
		articuloDto.setId(articulo.getId());
		articuloDto.setCodigo(articulo.getCodigo());
		articuloDto.setNombre(articulo.getNombre());
		articuloDto.setDescripcion(articulo.getDescripcion());
		articuloDto.setBaseImponible(articulo.getBaseImponible());
		articuloDto.setImpuesto(articulo.getImpuesto());
		articuloDto.setImporteTotal(articulo.getImporteTotal());
		
		return articuloDto;
	}
	
}
