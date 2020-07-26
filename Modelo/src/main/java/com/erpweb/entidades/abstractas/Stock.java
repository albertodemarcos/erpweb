package com.erpweb.entidades.abstractas;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Stock {

	private String descripcion; //Anotaciones 
	
	
	public Stock() {
		
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	
}
