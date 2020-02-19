package com.erpweb.entidades.bi;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.erpweb.entidades.empresa.Empresa;

@Entity
@Table(name="informe")
public class Informe implements Serializable {

	private static final long serialVersionUID = -6157545631637784507L;
	
	
	
	private Long id;
	private String codigo;
	private Empresa empresa;
	private Boolean generado = Boolean.FALSE;
	
	
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
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public Boolean getGenerado() {
		return generado;
	}
	
	public void setGenerado(Boolean generado) {
		this.generado = generado;
	}
	
}
