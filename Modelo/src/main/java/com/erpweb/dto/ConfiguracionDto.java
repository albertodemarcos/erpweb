package com.erpweb.dto;

import java.io.Serializable;


public class ConfiguracionDto implements Serializable {

	private static final long serialVersionUID = -6507871447265721550L;

	private Long id;
	private String codigo;
	private Long empresaId;
	private String idiomaApp;
	
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
	
	public String getIdiomaApp() {
		return idiomaApp;
	}
	
	public void setIdiomaApp(String idiomaApp) {
		this.idiomaApp = idiomaApp;
	}
	
	
	
}
