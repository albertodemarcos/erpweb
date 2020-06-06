package com.erpweb.dto;

import java.io.Serializable;
import java.math.BigDecimal;


public class GastoDto implements Serializable {

	private Long id;
	private String codigo;
	private String procedencia; 			//Procedencia del gasto (contrato o venta)
	private BigDecimal baseImponible;     //Precio sin impuesto
	private BigDecimal cuotaTributaria;   //Valor del impuesto sobre el producto
	private BigDecimal importeTotal;      //Base imponible mas cuota tributaria
	private String descripcion;      //Descripcion del gasto
	private String observaciones;    //Observaciones que se quieran incluir
	
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
