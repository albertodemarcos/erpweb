package com.erpweb.entidades.compras;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.erpweb.entidades.abstractas.Linea;
import com.erpweb.entidades.inventario.Articulo;

@Entity
@Table(name = "lineacompra")
public class LineaCompra extends Linea implements Serializable {

	private static final long serialVersionUID = 6627764927455826114L;
	
	private Long id; 						// ID
	private Compra compra; 					// Relacion
	private Articulo articulo; 				// Articulo de la linea
	private BigDecimal baseImponible; 		// Precio sin impuesto
	private BigDecimal importeTotal; 		// Precio con impuesto
	private BigDecimal importeImpuesto; 	// Diferencia entre precio sin impuesto y con impuesto

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LINEA_COMPRA_SEQ")
	@SequenceGenerator(name="LINEA_COMPRA_SEQ",sequenceName="SEQUENCE_LINEA_COMPRA", allocationSize=1)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch=FetchType.EAGER,optional=false)
	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
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

}
