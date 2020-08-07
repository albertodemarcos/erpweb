package com.erpweb.entidades.inventario;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.erpweb.entidades.abstractas.Producto;

@Entity
@Table(name="articulo")
public class Articulo extends Producto implements Serializable {

	private static final long serialVersionUID = 1968788003982981854L;

	
	private Long id;
	private String codigo;
	private Set<Almacen> almacenes = new HashSet<Almacen>();
	private String nombre;
	private String descripcion;
	private BigDecimal baseImponible;
	private String impuesto;	
	private BigDecimal importeTotal;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ARTICULO_SEQ")
	@SequenceGenerator(name="ARTICULO_SEQ",sequenceName="SEQUENCE_ARTICULO", allocationSize=1)
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

	@ManyToMany(mappedBy = "articulos" ,cascade = CascadeType.ALL)	
	public Set<Almacen> getAlmacenes() {
		return almacenes;
	}

	public void setAlmacenes(Set<Almacen> almacenes) {
		this.almacenes = almacenes;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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
