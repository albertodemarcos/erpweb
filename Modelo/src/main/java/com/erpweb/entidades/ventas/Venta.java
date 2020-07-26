package com.erpweb.entidades.ventas;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.erpweb.utiles.enumerados.TipoImpuesto;



@Entity
@Table(name="venta")
public class Venta implements Serializable {

	private static final long serialVersionUID = -7571807984020434198L;
	
	private Long id;
	private String codigo;
	private Factura factura;
	private Set<LineaVenta> lineasVenta;
	private Date fechaCreacion;  														//Cuando se crea la factura
	private Date fechaInicio;    														//Cuando empieza la factura
	private Date fechaFin;       														//Cuando finaliza la factura
	private String descripcion;															//Descripcion del contrato			
	private BigDecimal baseImponibleTotal;												//Importe total de la venta sin impuestos
	private TipoImpuesto impuesto;
	private BigDecimal importeTotal;	    											//Importe total de la venta con impuestos
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VENTA_SEQ")
	@SequenceGenerator(name="VENTA_SEQ",sequenceName="SEQUENCE_VENTA", allocationSize=1)
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
	
	@OneToOne(fetch = FetchType.EAGER, optional=false)
	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	@OneToMany(mappedBy="venta",cascade=CascadeType.ALL, orphanRemoval=true)
	public Set<LineaVenta> getLineasVenta() {
		return lineasVenta;
	}

	public void setLineasVenta(Set<LineaVenta> lineasVenta) {
		this.lineasVenta = lineasVenta;
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
	
	public BigDecimal getBaseImponibleTotal() {
		return baseImponibleTotal;
	}

	public void setBaseImponibleTotal(BigDecimal baseImponibleTotal) {
		this.baseImponibleTotal = baseImponibleTotal;
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
