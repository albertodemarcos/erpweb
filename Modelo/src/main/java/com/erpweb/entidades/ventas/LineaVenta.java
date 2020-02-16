package com.erpweb.entidades.ventas;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.erpweb.entidades.inventario.Articulo;



@Entity
@Table(name="lineaventa")
public class LineaVenta implements Serializable {

	
	private static final long serialVersionUID = 4495567024697117693L;

	
	private Long id;
	private String codigo;
	private Venta venta;
	private Set<Articulo> articulos;
	
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

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	public Set<Articulo> getArticulos() {
		return articulos;
	}

	public void setArticulos(Set<Articulo> articulos) {
		this.articulos = articulos;
	}

	
}
