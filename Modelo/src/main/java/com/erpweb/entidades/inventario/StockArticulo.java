package com.erpweb.entidades.inventario;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="stockarticulo", indexes = { @Index(columnList = "almacen_id"), @Index(columnList = "articulo_id") } )
public class StockArticulo implements Serializable {

	private static final long serialVersionUID = 3830853905216577288L;

	private Long id;
	private String codigo;
	private Articulo articulo;
	private Almacen almacen;
	private Long cantidad;
	

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STOCK_ARTICULO_SEQ")
	@SequenceGenerator(name="STOCK_ARTICULO_SEQ",sequenceName="SEQUENCE_STOCK_ARTICULO", allocationSize=1)
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
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	public Almacen getAlmacen() {
		return almacen;
	}

	public void setAlmacen(Almacen almacen) {
		this.almacen = almacen;
	}

	public Long getCantidad() {
		return cantidad;
	}

	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}

}
