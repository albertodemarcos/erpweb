package com.erpweb.entidades.embebidos;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class OrigenPersona implements Serializable {

	private static final long serialVersionUID = 4991349489503725355L;
	
	private String poblacion;
	private String region;
	private String provincia;
	private String pais;

	public OrigenPersona() {
		super();
	}

	public OrigenPersona(String poblacion, String region, String provincia, String pais) {
		super();
		this.poblacion = poblacion;
		this.region = region;
		this.provincia = provincia;
		this.pais = pais;
	}

	public String getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

}
