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
@Table(name="lineapedido")
public class LineaPedido extends Linea implements Serializable {

	private static final long serialVersionUID = -8000962734735927592L;
	
	
	private Long id; 					// ID
	private Pedido pedido; 				// Relacion
	private Articulo articulo; 			// Articulo de la linea
	private BigDecimal baseImponible; 	// Precio sin impuesto
	private BigDecimal importeTotal; 	// Precio con impuesto
	private BigDecimal importeImpuesto; // Diferencia entre precio sin impuesto y con impuesto

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LINEA_VENTA_SEQ")
	@SequenceGenerator(name="LINEA_VENTA_SEQ",sequenceName="SEQUENCE_LINEA_VENTA", allocationSize=1)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch=FetchType.EAGER,optional=false)
	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
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
