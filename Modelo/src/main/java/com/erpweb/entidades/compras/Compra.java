package com.erpweb.entidades.compras;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.erpweb.entidades.empresa.Empresa;

@Entity
@Table(name="compra")
public class Compra implements Serializable {

	private static final long serialVersionUID = -202927476348897177L;
	
	private Long id;
	private String codigo;
	private Empresa empresa;
	private Date fechaCompra;
	private Set<LineaCompra> lineaCompra;
	private Proveedor proveedor;
	
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
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Date getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	@OneToMany(orphanRemoval=true,mappedBy="compra",cascade=CascadeType.ALL)
	public Set<LineaCompra> getLineaCompra() {
		return lineaCompra;
	}

	public void setLineaCompra(Set<LineaCompra> lineaCompra) {
		this.lineaCompra = lineaCompra;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	
	
	
	
	
}
