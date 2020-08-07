package com.erpweb.entidades.inventario;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
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


@Entity
@Table(name="almacen")
public class Almacen implements Serializable {

	private static final long serialVersionUID = -8835509117976603534L;
	
	private Long id;
	private String codigo;
	private String nombre;
	
	//Atributos de direccion postal
   	private String codigoPostal;
   	private String direccion; 		//Calle/avenida/plaza, etc. con numero
   	private String edificio;        //Edificio planta y letra
   	private String observaciones;
   	private String telefono;
   	
   	//Atributos origen
   	private String poblacion;
   	private String region;
   	private String provincia;
   	private String pais;
		
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

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEdificio() {
		return edificio;
	}

	public void setEdificio(String edificio) {
		this.edificio = edificio;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
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
