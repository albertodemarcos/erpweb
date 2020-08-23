package com.erpweb.entidades.ventas;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.erpweb.utiles.enumerados.TipoImpuesto;




@Entity
@Table(name="factura")
public class Factura implements Serializable {

	private static final long serialVersionUID = 2543133846497019708L;
	
	private Long id;
	private String codigo;
	private Set<LineaFactura> lineasFactura = new HashSet<LineaFactura>(1);
	private Date fechaCreacion;  													//Cuando se crea la factura
	private Date fechaInicio;    													//Cuando empieza la factura
	private Date fechaFin;       													//Cuando finaliza la factura
	private String descripcion;      												//Descripcion de la factura
	private BigDecimal baseImponible;     											//Precio sin impuesto
	private TipoImpuesto impuesto;   												//Valor del impuesto sobre el producto
	private BigDecimal importeTotal;      											//Base imponible mas cuota tributaria
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FACTURA_SEQ")
	@SequenceGenerator(name="FACTURA_SEQ",sequenceName="SEQUENCE_FACTURA", allocationSize=1)
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
	
	@OneToMany(mappedBy="factura", cascade=CascadeType.ALL, orphanRemoval=true)
	public Set<LineaFactura> getLineasFactura() {
		return lineasFactura;
	}

	public void setLineasFactura(Set<LineaFactura> lineasFactura) {
		this.lineasFactura = lineasFactura;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
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

	@Enumerated(EnumType.STRING)
	public TipoImpuesto getImpuesto() {
		return impuesto;
	}

	public void setImpuesto(TipoImpuesto impuesto) {
		this.impuesto = impuesto;
	}

	public BigDecimal getImporteTotal() {
		return importeTotal;
	}

	public void setImporteTotal(BigDecimal importeTotal) {
		this.importeTotal = importeTotal;
	}

}
