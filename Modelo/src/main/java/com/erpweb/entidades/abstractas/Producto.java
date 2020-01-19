package com.erpweb.entidades.abstractas;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Producto {

	private String codigo;
	private String nombre;
	private String descripcion;
	private Double precio;
	
	
	public Producto() {
		
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
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public Double getPrecio() {
		return precio;
	}
	
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
    
    
    
}
