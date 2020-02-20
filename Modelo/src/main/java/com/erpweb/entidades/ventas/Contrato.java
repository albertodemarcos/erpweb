package com.erpweb.entidades.ventas;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.SortNatural;

import com.erpweb.entidades.empresa.Empresa;

@Entity
@Table(name="contrato")
public class Contrato implements Serializable {

	private static final long serialVersionUID = -6237983148813797015L;
	
	private Long id;
	private String codigo;
	private Empresa empresa;
	private Date fechaCreacion;  													//Cuando se crea el contrato
	private Date fechaInicio;    													//Cuando empieza el contrato
	private Date fechaFin;       													//Cuando finaliza el contrato
	private String descripcion;														//Descripcion del contrato
	private SortedSet<LineaContrato> lineasContrato = new TreeSet<LineaContrato>();	//Lineas del contrato ordenadas
	private Factura factura;														//Factura asociada al contrato
	private BigDecimal baseImponibleTotal;											//Importe total del contrato sin impuestos
	private BigDecimal importeTotal;	    										//Importe total del contro con impuestos

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

	@SortNatural
	@OneToMany(orphanRemoval = true, mappedBy = "contrato", cascade = CascadeType.ALL)
	public SortedSet<LineaContrato> getLineasContrato() {
		return lineasContrato;
	}

	public void setLineasContrato(SortedSet<LineaContrato> lineasContrato) {
		this.lineasContrato = lineasContrato;
	}

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public BigDecimal getBaseImponibleTotal() {
		return baseImponibleTotal;
	}

	public void setBaseImponibleTotal(BigDecimal baseImponibleTotal) {
		this.baseImponibleTotal = baseImponibleTotal;
	}

	public BigDecimal getImporteTotal() {
		return importeTotal;
	}

	public void setImporteTotal(BigDecimal importeTotal) {
		this.importeTotal = importeTotal;
	}
	
	
}
