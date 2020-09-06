package com.erpweb.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class LineaFacturaDto implements Serializable {

	private static final long serialVersionUID = -4533528462633810703L;

	private Long id; // ID
	private Long facturaId; // Relacion
	private ArticuloDto articuloDto; // Articulo de la linea
	private BigDecimal baseImponible; // Precio sin impuesto
	private BigDecimal importeTotal; // Precio con impuesto
	private BigDecimal importeImpuesto; // Diferencia entre precio sin impuesto y con impuesto
	private Integer cantidad; // Cantidad del articulo
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getFacturaId() {
		return facturaId;
	}
	
	public void setFacturaId(Long facturaId) {
		this.facturaId = facturaId;
	}
	
	public ArticuloDto getArticuloDto() {
		return articuloDto;
	}
	
	public void setArticuloDto(ArticuloDto articuloDto) {
		this.articuloDto = articuloDto;
	}
	
	public BigDecimal getBaseImponible() {
		return baseImponible;
	}
	
	public void setBaseImponible(BigDecimal baseImponible) {
		this.baseImponible = baseImponible;
	}
	
	public BigDecimal getImporteTotal() {
		return importeTotal;
	}
	
	public void setImporteTotal(BigDecimal importeTotal) {
		this.importeTotal = importeTotal;
	}
	
	public BigDecimal getImporteImpuesto() {
		return importeImpuesto;
	}
	
	public void setImporteImpuesto(BigDecimal importeImpuesto) {
		this.importeImpuesto = importeImpuesto;
	}
	
	public Integer getCantidad() {
		return cantidad;
	}
	
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	
	
	
}
