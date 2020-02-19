package com.erpweb.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.erpweb.entidades.inventario.Articulo;
import com.erpweb.utiles.enumerados.TipoProveedor;

public class ProveedorDto implements Serializable {

	private static final long serialVersionUID = -1102776761477673497L;

	private Long id;
	private String codigo;
	private Long empresaId;
	private String nombre;
	private String nombreEmpresa;
	private String telefono;
	private Articulo articulo; 				
	private BigDecimal cantidad;			
	private TipoProveedor tipoProveedor;
	
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
	
	public Long getEmpresaId() {
		return empresaId;
	}
	
	public void setEmpresaId(Long empresaId) {
		this.empresaId = empresaId;
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
	
	public Articulo getArticulo() {
		return articulo;
	}
	
	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}
	
	public BigDecimal getCantidad() {
		return cantidad;
	}
	
	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}
	
	public TipoProveedor getTipoProveedor() {
		return tipoProveedor;
	}
	
	public void setTipoProveedor(TipoProveedor tipoProveedor) {
		this.tipoProveedor = tipoProveedor;
	}
	
}
