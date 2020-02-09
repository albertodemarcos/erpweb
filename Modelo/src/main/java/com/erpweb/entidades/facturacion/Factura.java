package com.erpweb.entidades.facturacion;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="factura")
public class Factura implements Serializable {


	private static final long serialVersionUID = 2543133846497019708L;
	
	private Long id;
	private String codigo;
	private String descripcion;      			//Descripcion del gasto
	private BigDecimal baseImponible;     		//Precio sin impuesto
	private BigDecimal cuotaTributaria;   		//Valor del impuesto sobre el producto
	private BigDecimal importeTotal;      		//Base imponible mas cuota tributaria
	private Set<LineaFactura> lineasFactura;  	//Lineas de la factura
	
	
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	public Set<LineaFactura> getLineasFactura() {
		return lineasFactura;
	}

	public void setLineasFactura(Set<LineaFactura> lineasFactura) {
		this.lineasFactura = lineasFactura;
	}
}
