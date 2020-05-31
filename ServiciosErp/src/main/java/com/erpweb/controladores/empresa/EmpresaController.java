package com.erpweb.controladores.empresa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
	
	@GetMapping("/crearEmpresa")
	public @ResponseBody AccionRespuesta getCrearEmpresa(  ) {
		
		return new AccionRespuesta();
	}
	
	@PostMapping("/crearEmpresa")
	public @ResponseBody AccionRespuesta postCrearEmpresa( Empresa empresa, BindingResult result ) {
		
		this.empresaValidator.validate(empresa, result);
		
		if(	result.hasErrors() ) {
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	@GetMapping("/editarEmpresa")
	public @ResponseBody AccionRespuesta getEditarEmpresa(  ) {
		
		
		return new AccionRespuesta();
	}
	
	@PostMapping("/editarEmpresa")
	public @ResponseBody AccionRespuesta postEditarEmpresa( Empresa empresa, BindingResult result ) {
		
		this.empresaValidator.validate(empresa, result);
		
		if(	result.hasErrors() ) {
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	@PostMapping("/eliminarEmpresa")
	public @ResponseBody AccionRespuesta postEliminarEmpresa(  ) {
		
		
		return new AccionRespuesta();
	}
	
	
}
