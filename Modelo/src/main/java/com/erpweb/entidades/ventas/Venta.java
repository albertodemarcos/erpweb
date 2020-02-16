package com.erpweb.entidades.ventas;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="venta")
public class Venta implements Serializable {

	private static final long serialVersionUID = -7571807984020434198L;
	
	
	private Long id;
	private String codigo;
	private Set<LineaVenta> lineaVenta;
	private Factura factura;
	
	
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
	public Set<LineaVenta> getLineaVenta() {
		return lineaVenta;
	}
	public void setLineaVenta(Set<LineaVenta> lineaVenta) {
		this.lineaVenta = lineaVenta;
	}
	public Factura getFactura() {
		return factura;
	}
	public void setFactura(Factura factura) {
		this.factura = factura;
	}
	
	
}
