package com.erpweb.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.SortedSet;
import java.util.TreeSet;

import com.erpweb.entidades.ventas.LineaContrato;

public class ContratoDto implements Serializable {

	private static final long serialVersionUID = 7361832268020684761L;

	private Long id;
	private String codigo;
	private Long empresaId;
	private Date fechaCreacion;  													
	private Date fechaInicio;    													
	private Date fechaFin;       													
	private String descripcion;														
	private BigDecimal baseImponibleTotal;											
	private BigDecimal importeTotal;
	private Long facturaId;
	private SortedSet<LineaContrato> lineasContrato = new TreeSet<LineaContrato>();
	
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
	
	public Long getEmpresaId() {
		return empresaId;
	}

	public void setEmpresaId(Long empresaId) {
		this.empresaId = empresaId;
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

	public Long getFacturaId() {
		return facturaId;
	}

	public void setFacturaId(Long facturaId) {
		this.facturaId = facturaId;
	}

	public SortedSet<LineaContrato> getLineasContrato() {
		return lineasContrato;
	}

	public void setLineasContrato(SortedSet<LineaContrato> lineasContrato) {
		this.lineasContrato = lineasContrato;
	}	
	
	
}
