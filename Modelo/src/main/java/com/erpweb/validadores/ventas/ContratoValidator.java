package com.erpweb.validadores.ventas;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.erpweb.dto.ContratoDto;
import com.erpweb.repositorios.inventario.StockArticuloRepository;
import com.erpweb.utiles.Pareja;
import com.erpweb.utiles.Triple;

@Component
public class ContratoValidator implements Validator {
	
	@Autowired
	private StockArticuloRepository stockArticuloRepository;
	
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return ContratoDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		logger.debug("Se evaluan los datos del dto={} ", ContratoDto.class );

		ContratoDto contratoDto = (ContratoDto) target;
		
		this.comprobarStockArticuloDeArticulosSeleccionados(contratoDto, errors);
		
		if( StringUtils.isBlank( contratoDto.getCodigo() )  ) {
			
			errors.rejectValue("codigo", "", "El campo codigo no puede estar vacío");
		}
		
		if( contratoDto.getFechaInicio() == null  ) {
			
			errors.rejectValue("fechaInicio", "", "El campo fecha de inicio no puede estar vacío");
			
		} else if( (contratoDto.getFechaInicio() != null && contratoDto.getFechaFin() != null ) 
						&& contratoDto.getFechaInicio().after( contratoDto.getFechaFin() )  ) {
			
			errors.rejectValue("fechaInicio", "", "El campo fecha de inicio no puede ser superior al campo fecha de fin");
		}
		
		if( contratoDto.getFechaFin() == null ) {
			
			errors.rejectValue("fechaFin", "", "El campo fecha de fin no puede estar vacío");
			
		}else if( (contratoDto.getFechaInicio() != null && contratoDto.getFechaFin() != null ) 
				&& contratoDto.getFechaFin().before( contratoDto.getFechaInicio() )  ) {
			
			errors.rejectValue("fechaFin", "", "El campo fecha de fin no puede ser inferior al campo fecha de inicio");
		}
	}
	
	
	public void comprobarStockArticuloDeArticulosSeleccionados(ContratoDto contratoDto, Errors errors) {
		
		if( contratoDto.getArticulosCantidad() != null && contratoDto.getArticulosCantidad().isEmpty() ) {
			errors.rejectValue("articulosCantidad", "", "Error, no hay lineas del contrato");
			return;
		}
		
		if( contratoDto.getArticulosAlmacen() != null && contratoDto.getArticulosAlmacen().isEmpty() ) {
			errors.rejectValue("articulosAlmacen", "", "Error, no sabemos la procedencia del articulo/s");
			return;
		}
		
		Set<Long> articulosId = contratoDto.getArticulosAlmacen().entrySet().stream().map(art -> art.getKey() ).collect(Collectors.toSet());
		
		Set<Long> almacenesId = contratoDto.getArticulosAlmacen().entrySet().stream().map(al -> al.getValue() ).collect(Collectors.toSet());
		
		if( articulosId == null || articulosId.isEmpty() || almacenesId == null || almacenesId.isEmpty()) {
			errors.rejectValue("articulosAlmacen", "", "Error inesperado, intentar mas tarde.");
			return;
		}
		
		Set<Triple<Long, Long, Long>> articulosAlmacenesStock = stockArticuloRepository.obtieneStockArticuloIdAlmacenIdCantidad(almacenesId, articulosId);
		
		if( articulosAlmacenesStock == null || articulosAlmacenesStock.isEmpty() ) {
			errors.rejectValue("articulosAlmacen", "", "Error, no hay stock en ningun almacen");
			return;
		}
		
		HashMap<Pareja<Long, Long>, Long> mapaArticuloAlmacen = new HashMap<Pareja<Long, Long>, Long>(1);
		
		//Formamos el mapa
		for( Map.Entry<Long, Long> mapa : contratoDto.getArticulosAlmacen().entrySet() ) {
			
			Long articuloId = mapa.getKey();
			Long almacenId = mapa.getValue();
			Long cantidad = null;
			
			//Obtenemos la cantidad del articulo
			if( contratoDto.getArticulosCantidad().containsKey(articuloId) ) {
				cantidad = contratoDto.getArticulosCantidad().get(articuloId).longValue();
			}
			
			Pareja<Long, Long> keyMap = new Pareja<Long, Long>(articuloId, almacenId);
			
			mapaArticuloAlmacen.put(keyMap, cantidad);
		}
		
		//Inicializamos el Mapa
		HashMap<Pareja<Long, Long>, String> errorLineaContrato = new HashMap<Pareja<Long, Long>, String>();
		
		//Comparamos mapas..
		for(Triple<Long, Long, Long> stockArticulo : articulosAlmacenesStock) {
			
			Long almacenId = stockArticulo.getIzquierda();
			Long articuloId = stockArticulo.getCentro();
			Long cantidad = stockArticulo.getDerecha();
			
			Pareja<Long, Long > keyMap = new Pareja<Long, Long>(articuloId, almacenId);
			
			//Si esta en el mapa seguimos, sino paramos y mandamos error
			if( !mapaArticuloAlmacen.containsKey(keyMap) ) {
				errorLineaContrato.put(keyMap, "Error, no se ha encontrado stock para el almacen y articulo deseado");
				continue;
			}
			
			Long cantidadStock = mapaArticuloAlmacen.get( keyMap );
			
			if( cantidadStock.longValue() > cantidad.longValue() ) {
				errorLineaContrato.put(keyMap, "Error, no hay suficiente cantidad de este articulo en el almacen. Stock actual: "+cantidadStock);
			}
		}
		
		contratoDto.setLineasContratoDtoError(new HashMap<String, String>());
		
		if( !errorLineaContrato.isEmpty() ) {
			//Si no esta vacio, damos un error
			errorLineaContrato.entrySet().forEach(error ->{
				// Almacen Id
				Long almacenId = error.getKey().getDerecha();
				// Articulo Id
				Long articuloId = error.getKey().getIzquierda();
				
				//Key
				String keyMap = ""+articuloId.intValue()+"-"+almacenId.intValue();
				
				//Value
				error.getValue();
				
				//Lo metemos en el mapa
				contratoDto.getLineasContratoDtoError().put(keyMap, error.getValue());
			});		
			
			errors.rejectValue("lineasContratoDtoError", "", "");
		}
	}

}
