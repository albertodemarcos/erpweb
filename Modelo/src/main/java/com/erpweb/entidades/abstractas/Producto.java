package com.erpweb.entidades.abstractas;


import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Producto {

	private String nombre;
	private String descripcion;
	
	public Producto() {
		
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
	
    
}
