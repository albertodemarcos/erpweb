package com.erpweb.dto;

import java.io.Serializable;


public class UsuarioDto implements Serializable {

	private static final long serialVersionUID = -7816139174470066257L;

	private Long id;
	private String codigo;
	private String name;
	private String password;
    private String identidad;
    private Long empresaId;
    private String idioma;
    
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
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getIdentidad() {
		return identidad;
	}
	
	public void setIdentidad(String identidad) {
		this.identidad = identidad;
	}
	
	public Long getEmpresaId() {
		return empresaId;
	}
	
	public void setEmpresaId(Long empresaId) {
		this.empresaId = empresaId;
	}
	
	public String getIdioma() {
		return idioma;
	}
	
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	
    
    
    
}
