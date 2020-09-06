package com.erpweb.controladores.autocompletar;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.erpweb.dto.AlmacenDto;
import com.erpweb.dto.ArticuloDto;
import com.erpweb.servicios.generales.AutocompletarService;

@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true") //Conexion con angular 
@RestController
@RequestMapping("/autocompletar")
public class AutocompletarController {

	
	@Autowired
	private AutocompletarService autocompletarService;
	
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@GetMapping("/articulos/listado.json")
	public @ResponseBody List<ArticuloDto> getArticulos(
			@RequestParam(value = "term", required = true) String term,
			@RequestParam(value = "completo", required = false) Boolean completo) {
		
		logger.info("Entramos en el controlador del listado del Stock");
		
		return autocompletarService.getListadoArticulosAutocompletar(term, completo);
	}
	
	@GetMapping("/almacenes/listado.json")
	public @ResponseBody List<AlmacenDto> getAlmacenes(
			@RequestParam(value = "term", required = true) String term ) {
		
		logger.info("Entramos en el controlador del listado del Stock");
		
		return autocompletarService.getListadoAlmacenesAutocompletar(term);
	}
	
	
	
}
