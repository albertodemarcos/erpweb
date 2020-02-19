package com.erpweb.entidades.ventas;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.erpweb.entidades.comun.Impuesto;
import com.erpweb.entidades.inventario.Articulo;
import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name="lineaventa")
public class LineaVenta implements Serializable {

	private static final long serialVersionUID = 4495567024697117693L;
	
	private Long id;
	private String codigo;
	private Venta venta;
	private Articulo articulo;				//Articulo
	private BigDecimal cantidad;			//Cantidad de articulos del mismo tipo y precio
	private BigDecimal baseImponibleTotal;	//Importe de la linea correspondiente al importe de los articulos sin impuestos
	private Impuesto impuesto; 				//Impuesto 
	private BigDecimal importeTotal;	    //Importe de la linea correspondiente al importe de los articulos con impuestos
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq_gen")
	//@SequenceGenerator(name = "users_seq_gen", sequenceName = "users_id_seq")
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

	@JsonIgnore
	@ManyToOne
	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	public BigDecimal getCantidad() {
		return cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getBaseImponibleTotal() {
		return baseImponibleTotal;
	}

	public void setBaseImponibleTotal(BigDecimal baseImponibleTotal) {
		this.baseImponibleTotal = baseImponibleTotal;
	}

	public Impuesto getImpuesto() {
		return impuesto;
	}

	public void setImpuesto(Impuesto impuesto) {
		this.impuesto = impuesto;
	}

	public BigDecimal getImporteTotal() {
		return importeTotal;
	}

	public void setImporteTotal(BigDecimal importeTotal) {
		this.importeTotal = importeTotal;
	}

	
}
