package com.erpweb.entidades.compras;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="compra")
public class Compra implements Serializable {

	private static final long serialVersionUID = -202927476348897177L;
	
	private Long id;
	private String codigo;
	private Set<LineaCompra> lineaCompra;
	private Set<Proveedor> proveedores;
	
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

	public Set<LineaCompra> getLineaCompra() {
		return lineaCompra;
	}

	public void setLineaCompra(Set<LineaCompra> lineaCompra) {
		this.lineaCompra = lineaCompra;
	}
	
	public Set<Proveedor> getProveedor() {
		return proveedores;
	}

	public void setProveedor(Set<Proveedor> proveedores) {
		this.proveedores = proveedores;
	}
	
	
}
