package com.erpweb.entidades.ventas;

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
@Table(name = "lineafactura")
public class LineaFactura extends Linea implements Serializable {

	private static final long serialVersionUID = -1897366181766024572L;
	
	private Long id; 						// ID
	private Factura factura; 				// Relacion
	private Articulo articulo; 				// Articulo de la linea
	private BigDecimal baseImponible; 		// Precio sin impuesto
	private BigDecimal importeTotal; 		// Precio con impuesto
	private BigDecimal importeImpuesto; 	// Diferencia entre precio sin impuesto y con impuesto

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LINEA_FACTURA_SEQ")
	@SequenceGenerator(name="LINEA_FACTURA_SEQ",sequenceName="SEQUENCE_LINEA_FACTURA", allocationSize=1)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch=FetchType.EAGER, optional=false)
	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	@ManyToOne(fetch=FetchType.LAZY, optional=false)
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
