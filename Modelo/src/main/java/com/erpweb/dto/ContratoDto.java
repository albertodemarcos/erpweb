package com.erpweb.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import com.erpweb.utiles.enumerados.TipoImpuesto;


public class ContratoDto implements Serializable {

	private static final long serialVersionUID = -7766898516171882200L;
	
	private Long id;
	private String codigo;
	private Date fechaCreacion;  													//Cuando se crea el contrato
	private Date fechaInicio;    													//Cuando empieza el contrato
	private Date fechaFin;       													//Cuando finaliza el contrato
	private String descripcion;														//Descripcion del contrato
	private BigDecimal baseImponibleTotal;											//Importe total del contrato sin impuestos
	private TipoImpuesto impuesto;
	private BigDecimal importeTotal;	    										//Importe total del contro con impuestos
	private HashMap<Long, BigDecimal> articulosCantidad; 							//Mapa articuloId, Cantidad de articulos
	private Set<LineaContratoDto> lineasContratoDto = new HashSet<LineaContratoDto>();
	
	
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

	public Set<LineaContratoDto> getLineasContratoDto() {
		return lineasContratoDto;
	}

	public void setLineasContratoDto(Set<LineaContratoDto> lineasContratoDto) {
		this.lineasContratoDto = lineasContratoDto;
	}

	
}
