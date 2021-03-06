package com.erpweb.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import com.erpweb.utiles.enumerados.TipoImpuesto;


public class VentaDto implements Serializable {

	private static final long serialVersionUID = 7011372039239889400L;
	
	private Long id;
	private String codigo;
	private Date fechaCreacion;  														//Cuando se crea la factura
	private Date fechaInicio;    														//Cuando empieza la factura
	private Date fechaFin;       														//Cuando finaliza la factura
	private String descripcion;															//Descripcion del contrato			
	private BigDecimal baseImponibleTotal;												//Importe total de la venta sin impuestos
	private TipoImpuesto impuesto;
	private BigDecimal importeTotal;	    											//Importe total de la venta con impuestos														
	private HashMap<Long, BigDecimal> articulosCantidad; 								//Mapa articuloId, Cantidad de articulos
	private HashMap<Long, Long> articulosAlmacen;										//Mapa articuloId, almacenes
	private Set<LineaVentaDto> lineasVentaDto = new HashSet<LineaVentaDto>(1);
	private HashMap<String, String> lineasVentaDtoError;
	
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
	
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
	public Date getFechaInicio() {
		return fechaInicio;
	}
	
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
	public Date getFechaFin() {
		return fechaFin;
	}
	
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public BigDecimal getBaseImponibleTotal() {
		return baseImponibleTotal;
	}
	
	public void setBaseImponibleTotal(BigDecimal baseImponibleTotal) {
		this.baseImponibleTotal = baseImponibleTotal;
	}
	
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

	public HashMap<Long, BigDecimal> getArticulosCantidad() {
		return articulosCantidad;
	}

	public void setArticulosCantidad(HashMap<Long, BigDecimal> articulosCantidad) {
		this.articulosCantidad = articulosCantidad;
	}

	public Set<LineaVentaDto> getLineasVentaDto() {
		return lineasVentaDto;
	}

	public void setLineasVentaDto(Set<LineaVentaDto> lineasVentaDto) {
		this.lineasVentaDto = lineasVentaDto;
	}

	public HashMap<Long, Long> getArticulosAlmacen() {
		return articulosAlmacen;
	}

	public void setArticulosAlmacen(HashMap<Long, Long> articulosAlmacen) {
		this.articulosAlmacen = articulosAlmacen;
	}

	public HashMap<String, String> getLineasVentaDtoError() {
		return lineasVentaDtoError;
	}

	public void setLineasVentaDtoError(HashMap<String, String> lineasVentaDtoError) {
		this.lineasVentaDtoError = lineasVentaDtoError;
	}	
	
}
