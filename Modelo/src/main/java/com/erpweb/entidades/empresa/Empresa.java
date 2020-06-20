package com.erpweb.entidades.empresa;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.erpweb.utiles.enumerados.TipoSociedadJuridica;

@Entity
@Table(name="empresa")
public class Empresa implements Serializable {

	
	private static final long serialVersionUID = 4721055372396339567L;
	
	
	private Long id;
	private String codigo;
	private String nombre;
	private TipoSociedadJuridica tipoSociedadJuridica;
	private String cif;
	private String idioma;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
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

	@Enumerated(EnumType.STRING)
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

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	
	
	
}
