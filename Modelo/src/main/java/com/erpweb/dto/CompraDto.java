package com.erpweb.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import com.erpweb.entidades.compras.LineaCompra;
import com.erpweb.entidades.compras.Proveedor;

public class CompraDto implements Serializable {

	private static final long serialVersionUID = -2904706329060743892L;

	private Long id;
	private String codigo;
	private Long empresaId;
	private Date fechaCompra;
	private Set<LineaCompra> lineaCompra;
	private Proveedor proveedor;
	
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

	public Date getFechaCompra() {
		return fechaCompra;
	}
	
	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}
	
	public Set<LineaCompra> getLineaCompra() {
		return lineaCompra;
	}
	
	public void setLineaCompra(Set<LineaCompra> lineaCompra) {
		this.lineaCompra = lineaCompra;
	}
	
	public Proveedor getProveedor() {
		return proveedor;
	}
	
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	
	
}
