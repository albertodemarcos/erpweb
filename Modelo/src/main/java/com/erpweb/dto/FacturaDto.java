package com.erpweb.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import com.erpweb.utiles.enumerados.TipoImpuesto;


public class FacturaDto implements Serializable {

	private static final long serialVersionUID = -4396864364800392931L;
	
	private Long id;
	private String codigo;
	private Date fechaCreacion;  													//Cuando se crea la factura
	private Date fechaInicio;    													//Cuando empieza la factura
	private Date fechaFin;       													//Cuando finaliza la factura
	private String descripcion;      												//Descripcion de la factura
	private BigDecimal baseImponible;     											//Precio sin impuesto
	private TipoImpuesto impuesto;   												//Valor del impuesto sobre el producto
	private BigDecimal importeTotal;      											//Base imponible mas cuota tributaria 
	private HashMap<Long, BigDecimal> articulosCantidad; 							//Mapa articuloId, Cantidad de articulos
	private Set<LineaFacturaDto> lineasFacturaDto = new HashSet<LineaFacturaDto>();
	
	
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
	
	public BigDecimal getBaseImponible() {
		return baseImponible;
	}
	
	public void setBaseImponible(BigDecimal baseImponible) {
		this.baseImponible = baseImponible;
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

	public Set<LineaFacturaDto> getLineasFacturaDto() {
		return lineasFacturaDto;
	}

	public void setLineasFacturaDto(Set<LineaFacturaDto> lineasFacturaDto) {
		this.lineasFacturaDto = lineasFacturaDto;
	}

	
}
