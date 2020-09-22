package com.erpweb.controladores.empresa;

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

import com.erpweb.dto.EmpresaDto;
import com.erpweb.servicios.empresa.EmpresaService;
import com.erpweb.servicios.errores.ErroresService;
import com.erpweb.utiles.AccionRespuesta;
import com.erpweb.validadores.empresa.EmpresaValidator;

@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true") //Conexion con angular 
@RestController
@RequestMapping("/empresas")
public class EmpresaController {
	
	@Autowired 
	private EmpresaValidator empresaValidator;

	@Autowired
	private EmpresaService empresaService;
	
	@Autowired
	private ErroresService erroresService;
	
	
	
	@GetMapping("/empresa/{empresaId}")
	public @ResponseBody AccionRespuesta getEmpresa( @PathVariable Long empresaId) throws Exception {
		
		return this.empresaService.getEmpresa(empresaId);
	}
	
	@GetMapping( "/editarEmpresa/{empresaId}" )
	public @ResponseBody AccionRespuesta getEditarEmpresa( @PathVariable Long empresaId) throws Exception {
		
		return this.empresaService.getEmpresa(empresaId);
	}
	
	@PostMapping( "/editarEmpresa" )
	public @ResponseBody AccionRespuesta postEditarEmpresa( @RequestBody EmpresaDto empresaDto,  BindingResult result ) {
		
		this.empresaValidator.validate(empresaDto, result);
		
		if(	result.hasErrors() ) {
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE, this.erroresService.erroresValidacionEnDto(result) );
		}
		
		return this.empresaService.getCrearEditarEmpresa(empresaDto);
	}
	
	@GetMapping("/eliminarEmpresa/empresaId")
	public @ResponseBody AccionRespuesta getEliminarEmpresa( @PathVariable Long empresaId) throws Exception {
		
		if(empresaId == null || empresaId.longValue() < 1L ) {
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
		
		return this.empresaService.eliminarEmpresaPorId(empresaId);
	}
	
	
}
