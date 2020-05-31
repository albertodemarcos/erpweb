package com.erpweb.controladores.inventario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.erpweb.entidades.inventario.Vehiculo;
import com.erpweb.entidades.usuarios.Usuario;
import com.erpweb.servicios.inventario.VehiculoService;
import com.erpweb.utiles.AccionRespuesta;
import com.erpweb.validadores.inventario.VehiculoValidator;

@CrossOrigin(origins = {"http://localhost:4200"}) //Conexion con angular 
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
	
	@GetMapping("/listado")
	public String getVehiculos(  ) {
		return "";
	}
	
	@GetMapping( { "/crearVehiculo", "/editarVehiculo" } )
	public @ResponseBody AccionRespuesta getCrearVehiculo( @PathVariable Long vehiculoId, Usuario user ) throws Exception {
		
		return new AccionRespuesta();
	}
	
	@PostMapping( { "/crearVehiculo", "/editarVehiculo" } )
	public @ResponseBody AccionRespuesta postCrearVehiculo( Vehiculo vehiculo, BindingResult result ) {
		
		this.vehiculoValidator.validate(vehiculo, result);
		
		if( result.hasErrors() ) {
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	@PostMapping("/eliminarVehiculo")
	public @ResponseBody AccionRespuesta postEliminarVehiculo(  ) {
		
		
		return new AccionRespuesta();
	}
	
	
}
