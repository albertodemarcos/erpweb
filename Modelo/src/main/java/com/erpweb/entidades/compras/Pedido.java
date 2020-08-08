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
@Table(name="pedido")
public class Pedido implements Serializable {

	private static final long serialVersionUID = -8171324347908674865L;
	
	private Long id;
	private String codigo;
	private Factura factura;
	private Date fechaPedido;
	private Set<LineaPedido> lineasPedido = new HashSet<LineaPedido>();
	private BigDecimal baseImponibleTotal;	
	private TipoImpuesto impuesto; 			 
	private BigDecimal importeTotal;	    
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PEDIDO_SEQ")
	@SequenceGenerator(name="PEDIDO_SEQ",sequenceName="SEQUENCE_PEDIDO", allocationSize=1)
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
	
	@OneToMany(mappedBy="pedido",cascade=CascadeType.ALL, orphanRemoval=true)
	public Set<LineaPedido> getLineasPedido() {
		return lineasPedido;
	}

	public void setLineasPedido(Set<LineaPedido> lineasPedido) {
		this.lineasPedido = lineasPedido;
	}

	public Date getFechaPedido() {
		return fechaPedido;
	}
	
	public void setFechaPedido(Date fechaPedido) {
		this.fechaPedido = fechaPedido;
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
