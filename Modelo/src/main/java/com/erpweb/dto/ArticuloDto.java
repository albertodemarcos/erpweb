package com.erpweb.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;


public class ArticuloDto implements Serializable {


	private static final long serialVersionUID = 4936944605420860256L;
	
	private Long id;
	private String codigo;
	private String nombre;
	private String descripcion;
	private BigDecimal baseImponible;
	private String impuesto;	
	private BigDecimal importeTotal;
	private Set<Long> almacenesId;
	
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
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	
	public String getImpuesto() {
		return impuesto;
	}

	public void setImpuesto(String impuesto) {
		this.impuesto = impuesto;
	}

	public BigDecimal getImporteTotal() {
		return importeTotal;
	}
	
	public void setImporteTotal(BigDecimal importeTotal) {
		this.importeTotal = importeTotal;
	}

	public Set<Long> getAlmacenesId() {
		return almacenesId;
	}

	public void setAlmacenesId(Set<Long> almacenesId) {
		this.almacenesId = almacenesId;
	}
	
}
