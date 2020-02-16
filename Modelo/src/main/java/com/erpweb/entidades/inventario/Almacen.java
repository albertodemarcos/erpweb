package com.erpweb.entidades.inventario;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="almacen")
public class Almacen implements Serializable {

	private static final long serialVersionUID = -8835509117976603534L;
	
	
	private Long id;
	private String codigo;
	private Set<Articulo> articulos;
		
	
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
	public Set<Articulo> getArticulos() {
		return articulos;
	}
	public void setArticulos(Set<Articulo> articulos) {
		this.articulos = articulos;
	}
    
    
}
