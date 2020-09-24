package com.erpweb.entidades.inventario;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.erpweb.entidades.embebidos.DireccionPostal;
import com.erpweb.entidades.embebidos.OrigenPersona;


@Entity
@Table(name="almacen")
public class Almacen implements Serializable {

	private static final long serialVersionUID = -8835509117976603534L;
	
	private Long id;
	private String codigo;
	private String nombre;
	
    private DireccionPostal direccionPostal; 	 //Atributos de direccion postal
    private OrigenPersona origenPersona;		 //Atributos origen
		
   	//Articulo del Almacen
   	private Set<Articulo> articulos = new HashSet<Articulo>();
   	
   	//Stock articulos almacen
   	private Set<StockArticulo> stockAlmacen = new HashSet<StockArticulo>();
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ALMACEN_SEQ")
	@SequenceGenerator(name="ALMACEN_SEQ",sequenceName="SEQUENCE_ALMACEN", allocationSize=1)
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
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Embedded
	public DireccionPostal getDireccionPostal() {
		return direccionPostal;
	}

	public void setDireccionPostal(DireccionPostal direccionPostal) {
		this.direccionPostal = direccionPostal;
	}

	@Embedded
	public OrigenPersona getOrigenPersona() {
		return origenPersona;
	}

	public void setOrigenPersona(OrigenPersona origenPersona) {
		this.origenPersona = origenPersona;
	}

	@JoinTable(
		name = "almacen_articulo",
		joinColumns = @JoinColumn(name = "almacen_id", nullable=true),
		inverseJoinColumns = @JoinColumn(name = "articulo_id", nullable=true)
	)
	@ManyToMany(cascade = CascadeType.ALL)
	public Set<Articulo> getArticulos() {
		return articulos;
	}

	public void setArticulos(Set<Articulo> articulos) {
		this.articulos = articulos;
	}

	@OneToMany(mappedBy="almacen", cascade=CascadeType.ALL, orphanRemoval=true)
	public Set<StockArticulo> getStockAlmacen() {
		return stockAlmacen;
	}

	public void setStockAlmacen(Set<StockArticulo> stockAlmacen) {
		this.stockAlmacen = stockAlmacen;
	}
	
}
