package com.erpweb.controladores.compras;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erpweb.entidades.compras.Proveedor;
import com.erpweb.servicios.compras.ProveedorService;
import com.erpweb.validadores.compras.ProveedorValidator;

@CrossOrigin(origins = {"http://localhost:4200"}) //Conexion con angular 
@RestController
@RequestMapping("/proveedores")
public class ProveedorController {
	
	@Autowired 
	private ProveedorValidator proveedorValidator;
	
	@Autowired
	private ProveedorService proveedorService;
	
	@GetMapping("/proveedor")
	public String getProveedor(  ) {
		return "";
	}
	
	@GetMapping("/proveedores")
	public String getProveedores(  ) {
		return "";
	}
	
	@GetMapping("/crearProveedor")
	public String getCrearProveedor(  ) {
		return "";
	}
	
	@PostMapping("/crearProveedor")
	public String postCrearProveedor( Proveedor proveedor, BindingResult result ) {
		
		this.proveedorValidator.validate(proveedor, result);
		
		if(	result.hasErrors() ) {
			return "";
		}
		
		return "";
	}
	
	@GetMapping("/editarProveedor")
	public String getEditarProveedor(  ) {
		return "";
	}
	
	@PostMapping("/editarProveedor")
	public String postEditarProveedor( Proveedor proveedor, BindingResult result ) {
		
		this.proveedorValidator.validate(proveedor, result);
		
		if(	result.hasErrors() ) {
			return "";
		}
		
		return "";
	}
	
	@PostMapping("/eliminarProveedor")
	public String postEliminarProveedor(  ) {
		return "";
	}
	
}
