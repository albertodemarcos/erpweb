package com.erpweb.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.SortedSet;
import java.util.TreeSet;

import com.erpweb.entidades.ventas.LineaFactura;

public class FacturaDto implements Serializable {

	private static final long serialVersionUID = 553445342127999493L;

	private Long id;
	private String codigo;
	private Long empresaId;
	private Date fechaCreacion;
	private Date fechaInicio;    				
	private Date fechaFin;       				
	private String descripcion;      			
	private BigDecimal baseImponible;     		
	private BigDecimal cuotaTributaria;   		
	private BigDecimal importeTotal; 
	private SortedSet<LineaFactura> lineasFactura = new TreeSet<LineaFactura>();
	
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
	
	public BigDecimal getBaseImponible() {
		return baseImponible;
	}
	
	public void setBaseImponible(BigDecimal baseImponible) {
		this.baseImponible = baseImponible;
	}
	
	public BigDecimal getCuotaTributaria() {
		return cuotaTributaria;
	}
	
	public void setCuotaTributaria(BigDecimal cuotaTributaria) {
		this.cuotaTributaria = cuotaTributaria;
	}
	
	public BigDecimal getImporteTotal() {
		return importeTotal;
	}
	
	public void setImporteTotal(BigDecimal importeTotal) {
		this.importeTotal = importeTotal;
	}

	public SortedSet<LineaFactura> getLineasFactura() {
		return lineasFactura;
	}

	public void setLineasFactura(SortedSet<LineaFactura> lineasFactura) {
		this.lineasFactura = lineasFactura;
	}
	
	
	
}
