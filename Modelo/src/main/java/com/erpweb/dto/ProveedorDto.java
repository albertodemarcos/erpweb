package com.erpweb.dto;

import java.io.Serializable;

import com.erpweb.utiles.enumerados.TipoProveedor;

public class ProveedorDto implements Serializable {

	private static final long serialVersionUID = -1102776761477673497L;

	private Long id;
	private String codigo;
	private String nombre;
	private String nombreEmpresa;
	private String telefono;
	private TipoProveedor tipoProveedor; 	//Tipo de proveedor por suministros
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}
	
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}
	
	public String getTelefono() {
		return telefono;
	}
	
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public TipoProveedor getTipoProveedor() {
		return tipoProveedor;
	}
	
	public void setTipoProveedor(TipoProveedor tipoProveedor) {
		this.tipoProveedor = tipoProveedor;
	}
	
}
