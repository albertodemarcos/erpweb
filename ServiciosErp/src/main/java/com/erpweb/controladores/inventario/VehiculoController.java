package com.erpweb.controladores.inventario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.erpweb.dto.VehiculoDto;
import com.erpweb.entidades.usuarios.Usuario;
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

	@GetMapping("/vehiculo/{vehiculoId}")
	public @ResponseBody AccionRespuesta getVehiculo( @PathVariable Long vehiculoId, Usuario user ) throws Exception {
		
		return this.vehiculoService.getVehiculo(vehiculoId, user);
	}
	
	@GetMapping("/listado.json")
	public @ResponseBody List<VehiculoDto> getVehiculos( ) {
		
		return this.vehiculoService.getListadoVehiculos();
	}
	
	@GetMapping( "/crearVehiculo" )
	public @ResponseBody AccionRespuesta getCrearVehiculo( Model model, Usuario user ) throws Exception {
		
		return new AccionRespuesta();
	}
	
	@GetMapping( "/editarVehiculo/{vehiculoId}" )
	public @ResponseBody AccionRespuesta getEditarVehiculo( @PathVariable Long vehiculoId, Usuario user ) throws Exception {
		
		return this.vehiculoService.getVehiculo(vehiculoId, user);
	}
	
	@PostMapping( { "/crearVehiculo/vehiculo/{vehiculoDto}.json", "/editarVehiculo/vehiculo/{vehiculoDto}.json" } )
	public @ResponseBody AccionRespuesta postCrearVehiculo( VehiculoDto vehiculoDto, Usuario user, BindingResult result ) {
		
		this.vehiculoValidator.validate(vehiculoDto, result);
		
		if( result.hasErrors() ) {
			
			return this.vehiculoService.getVehiculo(vehiculoDto.getId(), user);
		}
		
		return this.vehiculoService.getCrearEditarVehiculo(vehiculoDto, user);
	}
	
	@PostMapping("/eliminarVehiculo/{vehiculoId}")
	public @ResponseBody AccionRespuesta postEliminarVehiculo( @PathVariable Long vehiculoId, Usuario user ) throws Exception {
		
		if(vehiculoId == null || vehiculoId.longValue() < 1L ) {
			
			return new AccionRespuesta();
		}
		
		return this.vehiculoService.eliminarVehiculoPorId(vehiculoId);
	}
	
	
}
