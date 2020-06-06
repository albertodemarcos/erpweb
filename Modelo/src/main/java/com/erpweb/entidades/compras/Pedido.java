package com.erpweb.entidades.compras;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="pedido")
public class Pedido implements Serializable {

	private static final long serialVersionUID = -8171324347908674865L;
	
	
	private Long id;
	private String codigo;
	private Date fechaPedido;
	private String articulo; 				//Articulo
	private BigDecimal cantidad;			//Cantidad de articulos del mismo tipo y precio
	private BigDecimal baseImponibleTotal;	//Importe de la linea correspondiente al importe de los articulos sin impuestos
	private String impuesto; 				//Impuesto 
	private BigDecimal importeTotal;	    //Importe de la linea correspondiente al importe de los articuloscon impuestos
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
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
	
	public Date getFechaPedido() {
		return fechaPedido;
	}
	
	public void setFechaPedido(Date fechaPedido) {
		this.fechaPedido = fechaPedido;
	}
	
	public String getArticulo() {
		return articulo;
	}
	
	public void setArticulo(String articulo) {
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
	
}
