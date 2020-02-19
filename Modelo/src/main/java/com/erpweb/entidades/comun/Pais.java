package com.erpweb.entidades.comun;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="pais")
public class Pais implements Serializable {

	private static final long serialVersionUID = -8611536509150634540L;

	private Long id;
	private String codigo;     //Alfa-2 ISO 3166-1
	private String codigoPais; //Alfa-3 ISO 3166-1
	private String nombre;
	private Set<Region> regiones;
	private Set<Provincia> provincias;
	
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
	
	public String getCodigoPais() {
		return codigoPais;
	}
	
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@OneToMany(mappedBy = "pais")
	public Set<Region> getRegiones() {
		return regiones;
	}

	public void setRegiones(Set<Region> regiones) {
		this.regiones = regiones;
	}

	@OneToMany(mappedBy = "pais")
	public Set<Provincia> getProvincias() {
		return provincias;
	}

	public void setProvincias(Set<Provincia> provincias) {
		this.provincias = provincias;
	}
	
	
}
