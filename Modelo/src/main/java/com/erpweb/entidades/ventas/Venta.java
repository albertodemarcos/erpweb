package com.erpweb.entidades.ventas;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.erpweb.entidades.empresa.Empresa;

@Entity
@Table(name="venta")
public class Venta implements Serializable {

	private static final long serialVersionUID = -7571807984020434198L;
	
	private Long id;
	private String codigo;
	private Empresa empresa;
	private Date fechaCreacion;  				//Cuando se crea la factura
	private Date fechaInicio;    				//Cuando empieza la factura
	private Date fechaFin;       				//Cuando finaliza la factura
	private String descripcion;
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
	
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
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
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@OneToMany(mappedBy = "venta")
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
