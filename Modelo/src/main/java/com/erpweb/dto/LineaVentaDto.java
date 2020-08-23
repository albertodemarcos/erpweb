package com.erpweb.dto;

import java.io.Serializable;
import java.math.BigDecimal;


public class LineaVentaDto implements Serializable {

	private static final long serialVersionUID = 6103622267110264900L;

	private Long id; // ID
	private Long ventaId; // Relacion
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

	public Long getVentaId() {
		return ventaId;
	}

	public void setVentaId(Long ventaId) {
		this.ventaId = ventaId;
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
