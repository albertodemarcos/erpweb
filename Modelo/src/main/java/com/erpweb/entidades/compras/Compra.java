package com.erpweb.entidades.compras;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
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

import com.erpweb.entidades.ventas.Factura;
import com.erpweb.utiles.enumerados.TipoImpuesto;


@Entity
@Table(name="compra")
public class Compra implements Serializable {

	private static final long serialVersionUID = -202927476348897177L;
	
	private Long id;
	private String codigo;
	private Factura factura;
	private Date fechaCompra;
	private Set<LineaCompra> lineasCompra = new HashSet<LineaCompra>(1);
	private BigDecimal baseImponibleTotal;	
	private TipoImpuesto impuesto; 			
	private BigDecimal importeTotal;	    
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMPRA_SEQ")
	@SequenceGenerator(name="COMPRA_SEQ",sequenceName="SEQUENCE_COMPRA", allocationSize=1)
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

	public Date getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	@OneToMany(mappedBy="compra",cascade=CascadeType.ALL, orphanRemoval=true)
	public Set<LineaCompra> getLineasCompra() {
		return lineasCompra;
	}

	public void setLineasCompra(Set<LineaCompra> lineasCompra) {
		this.lineasCompra = lineasCompra;
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
