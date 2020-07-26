package com.erpweb.entidades.abstractas;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Linea {

	private String descripcionLinea;

	
	
	public String getDescripcionLinea() {
		return descripcionLinea;
	}

	public void setDescripcionLinea(String descripcionLinea) {
		this.descripcionLinea = descripcionLinea;
	}
	
	
}
