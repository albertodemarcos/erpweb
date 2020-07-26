package com.erpweb.entidades.compras;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
@Table(name="pedido")
public class Pedido implements Serializable {

	private static final long serialVersionUID = -8171324347908674865L;
	
	
	private Long id;
	private String codigo;
	private Set<LineaPedido> lineaPedido;
	private Date fechaPedido;
	private String articulo;
	private BigDecimal cantidad;			
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
	
	@OneToMany(mappedBy="pedido",cascade=CascadeType.ALL, orphanRemoval=true)
	public Set<LineaPedido> getLineaPedido() {
		return lineaPedido;
	}

	public void setLineaPedido(Set<LineaPedido> lineaPedido) {
		this.lineaPedido = lineaPedido;
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
