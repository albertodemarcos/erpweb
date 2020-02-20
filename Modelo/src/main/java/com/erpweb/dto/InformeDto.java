package com.erpweb.dto;

import java.io.Serializable;


public class InformeDto implements Serializable {

	private static final long serialVersionUID = -7179358966581049663L;

	private Long id;
	private String codigo;
	private Long empresaId;
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
	
	public Long getEmpresaId() {
		return empresaId;
	}
	
	public void setEmpresaId(Long empresaId) {
		this.empresaId = empresaId;
	}
	
	public Boolean getGenerado() {
		return generado;
	}
	
	public void setGenerado(Boolean generado) {
		this.generado = generado;
	}
	
	
	
	
}
