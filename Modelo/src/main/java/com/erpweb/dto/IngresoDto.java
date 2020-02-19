package com.erpweb.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.erpweb.entidades.empresa.Empresa;

public class IngresoDto implements Serializable {

	private static final long serialVersionUID = -5302205067845393881L;

	private Long id;
	private String codigo;
	private Long empresaId;
	private String procedencia; 			
	private BigDecimal baseImponible;     
	private BigDecimal cuotaTributaria;   
	private BigDecimal importeTotal;      
	private String descripcion;      
	private String observaciones;
	
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

	public String getProcedencia() {
		return procedencia;
	}
	
	public void setProcedencia(String procedencia) {
		this.procedencia = procedencia;
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
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getObservaciones() {
		return observaciones;
	}
	
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
}
