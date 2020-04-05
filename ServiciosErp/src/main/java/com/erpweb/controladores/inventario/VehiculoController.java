package com.erpweb.controladores.inventario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erpweb.entidades.inventario.Vehiculo;
import com.erpweb.validadores.inventario.VehiculoValidator;

@CrossOrigin(origins = {"http://localhost:4200"}) //Conexion con angular 
@RestController
@RequestMapping("/vehiculos")
public class VehiculoController {
	
	@Autowired private VehiculoValidator vehiculoValidator;

	@GetMapping("/vehiculo")
	public String getVehiculo(  ) {
		return "";
	}
	
	@GetMapping("/vehiculos")
	public String getVehiculos(  ) {
		return "";
	}
	
	@GetMapping("/crearVehiculo")
	public String getCrearVehiculo(  ) {
		return "";
	}
	
	@PostMapping("/crearVehiculo")
	public String postCrearVehiculo( Vehiculo vehiculo, BindingResult result ) {
		
		this.vehiculoValidator.validate(vehiculo, result);
		
		if( result.hasErrors() ) {
			
			return "";
		}
		
		return "";
	}
	
	@GetMapping("/editarVehiculo")
	public String getEditarVehiculo(  ) {
		return "";
	}
	
	@PostMapping("/editarVehiculo")
	public String postEditarVehiculo( Vehiculo vehiculo, BindingResult result ) {

		this.vehiculoValidator.validate(vehiculo, result);
		
		if( result.hasErrors() ) {
			
			return "";
		}
		
		return "";
	}
	
	@PostMapping("/eliminarVehiculo")
	public String postEliminarVehiculo(  ) {
		return "";
	}
	
	
}
