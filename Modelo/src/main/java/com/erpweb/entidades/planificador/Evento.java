package com.erpweb.entidades.planificador;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="evento")
public class Evento implements Serializable {

	private static final long serialVersionUID = 7458449138690937445L;
	
	private Long id;
	private String titulo;
	private Date fechaInicio;
	private Date fechaFin;
	private String descripcion;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EVENTO_SEQ")
	@SequenceGenerator(name="EVENTO_SEQ",sequenceName="SEQUENCE_EVENTO", allocationSize=1)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public Date getFechaInicio() {
		return fechaInicio;
	}
	
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
	public Date getFechaFin() {
		return fechaFin;
	}
	
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	@Lob @Type(type="org.hibernate.type.TextType")
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

		
}
