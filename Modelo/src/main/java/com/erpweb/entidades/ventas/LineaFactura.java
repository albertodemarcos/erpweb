package com.erpweb.entidades.ventas;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.erpweb.entidades.comun.Impuesto;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="lineafactura")
public class LineaFactura implements Serializable {

	private static final long serialVersionUID = 8397431619364782474L;
	
	private Long id;
	private String codigo;
	private Factura factura;
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
	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
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
