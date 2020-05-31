package com.erpweb.controladores.empresa;

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

import com.erpweb.dto.EmpresaDto;
import com.erpweb.entidades.empresa.Empresa;
import com.erpweb.entidades.usuarios.Usuario;
import com.erpweb.servicios.empresa.EmpresaService;
import com.erpweb.utiles.AccionRespuesta;
import com.erpweb.validadores.empresa.EmpresaValidator;

@CrossOrigin(origins = {"http://localhost:4200"}) //Conexion con angular 
@RestController
@RequestMapping("/empresas")
public class EmpresaController {
	
	@Autowired 
	private EmpresaValidator empresaValidator;

	@Autowired
	private EmpresaService empresaService;
	
	@GetMapping("/empresa/{empresaId}")
	public @ResponseBody AccionRespuesta getEmpresa( @PathVariable Long empresaId, Usuario user) throws Exception {
		
		return this.empresaService.getEmpresa(empresaId, user);
	}
	
	@GetMapping("/listado")
	public String getEmpresas(  ) {
		
		
		return "";
	}
	
	@GetMapping( "/crearEmpresa" )
	public @ResponseBody AccionRespuesta getCrearEmpresa( Model model, Usuario user) throws Exception {
		
		return new AccionRespuesta();
	}
	
	@GetMapping( "/editarEmpresa/{empresaId}" )
	public @ResponseBody AccionRespuesta getEditarEmpresa( @PathVariable Long empresaId, Usuario user) throws Exception {
		
		return this.empresaService.getEmpresa(empresaId, user);
	}
	
	@PostMapping( { "/crearEmpresa/empresa/{empresaDto}.json", "/editarEmpresa/empresa/{empresaDto}.json" } )
	public @ResponseBody AccionRespuesta postCrearEmpresa( EmpresaDto empresaDto, Usuario user, BindingResult result ) {
		
		this.empresaValidator.validate(empresaDto, result);
		
		if(	result.hasErrors() ) {
			
			return this.empresaService.getEmpresa(empresaDto.getId(), user);
		}
		
		return new AccionRespuesta();
	}
	
	@PostMapping("/eliminarEmpresa/empresaId")
	public @ResponseBody AccionRespuesta postEliminarEmpresa( @PathVariable Long empresaId, Usuario user) throws Exception {
		
		if(empresaId == null || empresaId.longValue() < 1L ) {
			
			return new AccionRespuesta();
		}
		
		return this.empresaService.eliminarEmpresaPorId(empresaId);
	}
	
	
}
