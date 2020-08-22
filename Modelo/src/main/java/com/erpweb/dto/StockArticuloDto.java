package com.erpweb.dto;

import java.io.Serializable;

import com.erpweb.entidades.inventario.Almacen;
import com.erpweb.entidades.inventario.Articulo;


public class StockArticuloDto implements Serializable {

	private static final long serialVersionUID = 1048881533534692852L;

	private Long id;
	private String codigo;
	private Long articuloId;
	private Long almacenId;
	private AlmacenDto almacenDto;
	private ArticuloDto articuloDto;
	private Long cantidad;


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

	public Long getArticuloId() {
		return articuloId;
	}

	public void setArticuloId(Long articuloId) {
		this.articuloId = articuloId;
	}

	public Long getAlmacenId() {
		return almacenId;
	}

	public void setAlmacenId(Long almacenId) {
		this.almacenId = almacenId;
	}

	public AlmacenDto getAlmacenDto() {
		return almacenDto;
	}

	public void setAlmacenDto(AlmacenDto almacenDto) {
		this.almacenDto = almacenDto;
	}

	public ArticuloDto getArticuloDto() {
		return articuloDto;
	}

	public void setArticuloDto(ArticuloDto articuloDto) {
		this.articuloDto = articuloDto;
	}
	
	public Long getCantidad() {
		return cantidad;
	}

	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}

}
