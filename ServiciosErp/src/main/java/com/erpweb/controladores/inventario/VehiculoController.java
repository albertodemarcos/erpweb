package com.erpweb.controladores.inventario;

import java.util.List;

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

import com.erpweb.dto.VehiculoDto;
import com.erpweb.servicios.errores.ErroresService;
import com.erpweb.servicios.inventario.VehiculoService;
import com.erpweb.utiles.AccionRespuesta;
import com.erpweb.validadores.inventario.VehiculoValidator;

@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true") //Conexion con angular 
@RestController
@RequestMapping("/vehiculos")
public class VehiculoController {
	
	@Autowired 
	private VehiculoValidator vehiculoValidator;
	
	@Autowired
	private VehiculoService vehiculoService;
	
	@Autowired
	private ErroresService erroresService;

	@GetMapping("/vehiculo/{vehiculoId}")
	public @ResponseBody AccionRespuesta getVehiculo( @PathVariable Long vehiculoId) throws Exception {
		
		return this.vehiculoService.getVehiculo(vehiculoId);
	}
	
	@GetMapping("/listado.json")
	public @ResponseBody List<VehiculoDto> getVehiculos( ) {
		
		return this.vehiculoService.getListadoVehiculos();
	}
	
	@GetMapping( "/editarVehiculo/{vehiculoId}" )
	public @ResponseBody AccionRespuesta getEditarVehiculo( @PathVariable Long vehiculoId) throws Exception {
		
		return this.vehiculoService.getVehiculo(vehiculoId);
	}
	
	@PostMapping( "/crearVehiculo" )
	public @ResponseBody AccionRespuesta postCrearVehiculo( @RequestBody VehiculoDto vehiculoDto, BindingResult result ) {
		
		this.vehiculoValidator.validate(vehiculoDto, result);
		
		if( result.hasErrors() ) {
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE, this.erroresService.erroresValidacionEnDto(result) );
		}
		
		return this.vehiculoService.getCrearEditarVehiculo(vehiculoDto);
	}
	
	@PostMapping( "/editarVehiculo"  )
	public @ResponseBody AccionRespuesta postEditarVehiculo( @RequestBody VehiculoDto vehiculoDto, BindingResult result ) {
		
		this.vehiculoValidator.validate(vehiculoDto, result);
		
		if( result.hasErrors() ) {
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE, this.erroresService.erroresValidacionEnDto(result) );
		}
		
		return this.vehiculoService.getCrearEditarVehiculo(vehiculoDto);
	}
	
	@GetMapping("/eliminarVehiculo/{vehiculoId}")
	public @ResponseBody AccionRespuesta getEliminarVehiculo( @PathVariable Long vehiculoId) throws Exception {
		
		if(vehiculoId == null || vehiculoId.longValue() < 1L ) {
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
		
		return this.vehiculoService.eliminarVehiculoPorId(vehiculoId);
	}
	
	
}
