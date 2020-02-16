package com.erpweb.entidades.compras;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="proveedor")
public class Proveedor implements Serializable {

	private static final long serialVersionUID = 8114672366873670400L;
	
	private Long id;
	private String codigo;
	private String nombre;
	private String empresa;
	
	
	public Proveedor() {
		
	}
	
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
	
	
	
	
}
