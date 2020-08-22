package com.erpweb.controladores.inventario;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.erpweb.dto.StockArticuloDto;
import com.erpweb.entidades.usuarios.Usuario;
import com.erpweb.servicios.errores.ErroresService;
import com.erpweb.servicios.inventario.StockAlmacenService;
import com.erpweb.utiles.AccionRespuesta;
import com.erpweb.validadores.inventario.StockArticuloValidator;

@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true") //Conexion con angular 
@RestController
@RequestMapping("/stock")
public class StockArticuloController {

	
	@Autowired
	private StockAlmacenService stockAlmacenService;
	
	@Autowired
	private StockArticuloValidator stockArticuloValidator;
	
	@Autowired
	private ErroresService erroresService;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	
	
	@GetMapping("/stockArticulo/{stockArticuloId}")
	public @ResponseBody AccionRespuesta getStockAlmacen( @PathVariable Long stockArticuloId) throws Exception {
		
		logger.info("Entramos en el controlador de getStockAlmacen()");
		
		return this.stockAlmacenService.getStockArticulo(stockArticuloId);
	}
	
	@GetMapping("/listado.json")
	public @ResponseBody List<StockArticuloDto> getAlmacenes( ) {
		
		logger.info("Entramos en el controlador del listado del Stock");
		
		return stockAlmacenService.getListadoStockArticulos();
	}
	
	@GetMapping( "/editarStockArticulo/{stockArticuloId}" )
	public @ResponseBody AccionRespuesta getEditarAlmacen( @PathVariable Long stockArticuloId) throws Exception {
		
		logger.info("Entramos en el controlador de getEditarAlmacen()");
		
		return this.stockAlmacenService.getStockArticulo(stockArticuloId);
	}
	
	@PostMapping( "/crearStockArticulo" )
	public @ResponseBody AccionRespuesta postCrearAlmacen( @RequestBody StockArticuloDto stockArticuloDto, BindingResult result ) {
		
		this.stockArticuloValidator.validate(stockArticuloDto, result);
		
		if( result.hasErrors() ) {
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE, this.erroresService.erroresValidacionEnDto(result));
		}
		
		return this.stockAlmacenService.crearActualizaStockArticuloAlmacen(stockArticuloDto, Boolean.FALSE);
	}
	
	@PostMapping( "/editarStockArticulo" )
	public @ResponseBody AccionRespuesta postEditarAlmacen( @RequestBody StockArticuloDto stockArticuloDto, BindingResult result ) {
		
		this.stockArticuloValidator.validate(stockArticuloDto, result);
		
		if( result.hasErrors() ) {
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE, this.erroresService.erroresValidacionEnDto(result));
		}
		
		return this.stockAlmacenService.crearActualizaStockArticuloAlmacen(stockArticuloDto, Boolean.TRUE);
	}
	
	@GetMapping("/eliminarStockArticulo/{stockArticuloId}")
	public @ResponseBody AccionRespuesta getEliminarAlmacen( @PathVariable Long stockArticuloId, Usuario user) throws Exception {
		
		if(stockArticuloId == null || stockArticuloId.longValue() < 1L ) {
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
		
		return this.stockAlmacenService.eliminarStockArticuloAlmacenId(stockArticuloId);
	}
	
}
