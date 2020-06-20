package com.erpweb.entidades.compras;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.erpweb.utiles.enumerados.TipoProveedor;

@Entity
@Table(name="proveedor")
public class Proveedor implements Serializable {

	private static final long serialVersionUID = 8114672366873670400L;
	
	private Long id;
	private String codigo;
	private String nombre;
	private String nombreEmpresa;
	private String telefono;
	private TipoProveedor tipoProveedor; 	//Tipo de proveedor por suministros
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
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

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Enumerated(EnumType.STRING)
	public TipoProveedor getTipoProveedor() {
		return tipoProveedor;
	}

	public void setTipoProveedor(TipoProveedor tipoProveedor) {
		this.tipoProveedor = tipoProveedor;
	}
	
}
