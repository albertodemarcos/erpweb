package com.erpweb.entidades.comun;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="provincia")
public class Provincia implements Serializable {

	private static final long serialVersionUID = -1753054209855039867L;
	
	
	private Long id;
	private String codigo;
	private String nombre;
	private Pais pais;
	private Region region;
	private Set<Poblacion> poblaciones;
	
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

	@ManyToOne
	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	@ManyToOne
	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	@OneToMany(mappedBy = "provincia")
	public Set<Poblacion> getPoblaciones() {
		return poblaciones;
	}

	public void setPoblaciones(Set<Poblacion> poblaciones) {
		this.poblaciones = poblaciones;
	}
	
	
	
	
}
