package com.erpweb.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.erpweb.utiles.enumerados.TipoProveedor;

public class ArticuloDto implements Serializable {

	private static final long serialVersionUID = 3278137799145219888L;

	private Long id;
	private String codigo;
	private Long empresaId;
	private String nombre;
	private String descripcion;
	private BigDecimal baseImponible;
	private Long impuestoId;
	private String codigoImpuesto;
	private String nombreImpuesto;
	private Double porcentajeImpuesto;
	private BigDecimal importeTotal;
	private Long proveedorId;
	private String codigoProveedor;
	private String nombreProveedor;
	private String nombreEmpresaProveedor;
	private String telefonoProveedor;
	private TipoProveedor tipoProveedor; 
	private Long almacenId;
	private String codigoAlmacen;
	private String nombreAlmacen;
	
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
	
	public Long getImpuestoId() {
		return impuestoId;
	}

	public void setImpuestoId(Long impuestoId) {
		this.impuestoId = impuestoId;
	}

	public String getCodigoImpuesto() {
		return codigoImpuesto;
	}

	public void setCodigoImpuesto(String codigoImpuesto) {
		this.codigoImpuesto = codigoImpuesto;
	}

	public String getNombreImpuesto() {
		return nombreImpuesto;
	}

	public void setNombreImpuesto(String nombreImpuesto) {
		this.nombreImpuesto = nombreImpuesto;
	}

	public Double getPorcentajeImpuesto() {
		return porcentajeImpuesto;
	}

	public void setPorcentajeImpuesto(Double porcentajeImpuesto) {
		this.porcentajeImpuesto = porcentajeImpuesto;
	}

	public BigDecimal getImporteTotal() {
		return importeTotal;
	}
	
	public void setImporteTotal(BigDecimal importeTotal) {
		this.importeTotal = importeTotal;
	}
	
	public Long getProveedorId() {
		return proveedorId;
	}
	
	public void setProveedorId(Long proveedorId) {
		this.proveedorId = proveedorId;
	}
	
	public String getCodigoProveedor() {
		return codigoProveedor;
	}
	
	public void setCodigoProveedor(String codigoProveedor) {
		this.codigoProveedor = codigoProveedor;
	}
	
	public String getNombreProveedor() {
		return nombreProveedor;
	}
	
	public void setNombreProveedor(String nombreProveedor) {
		this.nombreProveedor = nombreProveedor;
	}
	
	public String getNombreEmpresaProveedor() {
		return nombreEmpresaProveedor;
	}
	
	public void setNombreEmpresaProveedor(String nombreEmpresaProveedor) {
		this.nombreEmpresaProveedor = nombreEmpresaProveedor;
	}
	
	public String getTelefonoProveedor() {
		return telefonoProveedor;
	}
	
	public void setTelefonoProveedor(String telefonoProveedor) {
		this.telefonoProveedor = telefonoProveedor;
	}
	
	public TipoProveedor getTipoProveedor() {
		return tipoProveedor;
	}
	
	public void setTipoProveedor(TipoProveedor tipoProveedor) {
		this.tipoProveedor = tipoProveedor;
	}
	
	public Long getAlmacenId() {
		return almacenId;
	}
	
	public void setAlmacenId(Long almacenId) {
		this.almacenId = almacenId;
	}
	
	public String getCodigoAlmacen() {
		return codigoAlmacen;
	}
	
	public void setCodigoAlmacen(String codigoAlmacen) {
		this.codigoAlmacen = codigoAlmacen;
	}
	
	public String getNombreAlmacen() {
		return nombreAlmacen;
	}
	
	public void setNombreAlmacen(String nombreAlmacen) {
		this.nombreAlmacen = nombreAlmacen;
	}
	
}
