package com.erpweb.entidades.comun;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="poblacion")
public class Poblacion implements Serializable {

	private static final long serialVersionUID = 6805768178626313227L;
	
	
	private Long id;
	private String codigo;
	private String nombre;
	private String codigoPostal;
	private Pais pais;
	private Region region;
	private Provincia provincias;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq_gen")
	//@SequenceGenerator(name = "users_seq_gen", sequenceName = "users_id_seq")
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

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public Provincia getProvincias() {
		return provincias;
	}

	public void setProvincias(Provincia provincias) {
		this.provincias = provincias;
	}
	
	
	
}
