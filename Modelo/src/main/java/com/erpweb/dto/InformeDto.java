package com.erpweb.dto;

import java.io.Serializable;


public class InformeDto implements Serializable {

	private Long id;
	private String codigo;
	private Boolean generado = Boolean.FALSE;
	
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
	
	public Boolean getGenerado() {
		return generado;
	}
	
	public void setGenerado(Boolean generado) {
		this.generado = generado;
	}
	
	
	
	
}
