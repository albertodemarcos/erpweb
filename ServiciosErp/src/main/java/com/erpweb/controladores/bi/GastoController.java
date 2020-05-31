package com.erpweb.controladores.bi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.erpweb.dto.GastoDto;
import com.erpweb.entidades.bi.Gasto;
import com.erpweb.entidades.usuarios.Usuario;
import com.erpweb.servicios.bi.GastoService;
import com.erpweb.utiles.AccionRespuesta;
import com.erpweb.validadores.bi.GastoValidator;



@CrossOrigin(origins = {"http://localhost:4200"}) //Conexion con angular 
@RestController
@RequestMapping("/gastos")
public class GastoController {
	
	@Autowired 
	private GastoValidator gastoValidator;
	
	@Autowired
	private GastoService gastoService;
	
	@GetMapping("/gasto/{gastoId}")
	public @ResponseBody AccionRespuesta getGasto(@PathVariable Long gastoId, Usuario user) throws Exception {
		
		return this.gastoService.getGasto(gastoId, user);
	}
	
	@GetMapping("/listado")
	public String getGastos() {
		
		
		return "";
	}
	
	@GetMapping("/crearGasto")
	public @ResponseBody AccionRespuesta getCrearGasto() {

		
		return new AccionRespuesta();
	}
	
	@PostMapping("/crearGasto")
	public @ResponseBody AccionRespuesta postCrearGasto(GastoDto gasto, BindingResult result) {
		
		this.gastoValidator.validate(gasto, result);
		
		if( result.hasErrors() ) {
			
			return new AccionRespuesta();
		}
		
		
		return new AccionRespuesta();
	}
	
	@GetMapping("/editarGasto/{gastoId}")
	public @ResponseBody AccionRespuesta getEditarGasto(@PathVariable Long gastoId, Usuario user) {
		
		return new AccionRespuesta();
	}
	
	@PostMapping("/editarGasto")
	public @ResponseBody AccionRespuesta postEditarGasto(GastoDto gasto, BindingResult result) {
		
		this.gastoValidator.validate(gasto, result);
		
		if( result.hasErrors() ) {
			
			return new AccionRespuesta();
		}
		
		
		return new AccionRespuesta();
	}
	
	@PostMapping("/eliminarGasto")
	public @ResponseBody AccionRespuesta postEliminarGasto(GastoDto gasto) {
		
		if(gasto == null) {
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}

}
