package com.erpweb.dto;

import java.io.Serializable;

import com.erpweb.utiles.enumerados.TipoSociedadJuridica;

public class EmpresaDto implements Serializable {

	private static final long serialVersionUID = -4713303484334409808L;

	private Long id;
	private String codigo;
	private String nombre;
	private TipoSociedadJuridica tipoSociedadJuridica;
	private String cif;
	
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
	
	public TipoSociedadJuridica getTipoSociedadJuridica() {
		return tipoSociedadJuridica;
	}
	
	public void setTipoSociedadJuridica(TipoSociedadJuridica tipoSociedadJuridica) {
		this.tipoSociedadJuridica = tipoSociedadJuridica;
	}
	
	public String getCif() {
		return cif;
	}
	
	public void setCif(String cif) {
		this.cif = cif;
	}
	
	
	
}
