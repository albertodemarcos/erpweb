package com.erpweb.controladores.compras;

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

import com.erpweb.entidades.compras.Proveedor;
import com.erpweb.entidades.usuarios.Usuario;
import com.erpweb.servicios.compras.ProveedorService;
import com.erpweb.utiles.AccionRespuesta;
import com.erpweb.validadores.compras.ProveedorValidator;

@CrossOrigin(origins = {"http://localhost:4200"}) //Conexion con angular 
@RestController
@RequestMapping("/proveedores")
public class ProveedorController {
	
	@Autowired 
	private ProveedorValidator proveedorValidator;
	
	@Autowired
	private ProveedorService proveedorService;
	
	@GetMapping("/proveedor/{proveedorId}")
	public @ResponseBody AccionRespuesta getProveedor( @PathVariable Long proveedorId, Usuario user ) throws Exception {
		
		return this.proveedorService.getProveedor(proveedorId, user);
	}
	
	@GetMapping("/listado")
	public String getProveedores(  ) {
		return "";
	}
	
	@GetMapping( "/crearProveedor" )
	public @ResponseBody AccionRespuesta getCrearProveedor( Model model, Usuario user ) throws Exception {
		
		return new AccionRespuesta();
	}
	
	@GetMapping( "/editarProveedor/{proveedorId}" )
	public @ResponseBody AccionRespuesta getEditarProveedor( @PathVariable Long proveedorId, Usuario user ) throws Exception {
		
		return this.proveedorService.getProveedor(proveedorId, user);
	}
	
	@PostMapping({ "/crearProveedor", "/editarProveedor" })
	public @ResponseBody AccionRespuesta postCrearProveedor( Proveedor proveedor, BindingResult result ) {
		
		this.proveedorValidator.validate(proveedor, result);
		
		if(	result.hasErrors() ) {
			
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	@PostMapping("/eliminarProveedor")
	public @ResponseBody AccionRespuesta postEliminarProveedor(  ) {
		
		return new AccionRespuesta();
	}
	
}
