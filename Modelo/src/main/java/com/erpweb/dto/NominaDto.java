package com.erpweb.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


public class NominaDto implements Serializable {

	private static final long serialVersionUID = 1861221720946106977L;

	private Long id;
	private String codigo;
	private Long empresaId;
	private Long empleadoId;  
	private String codigoEmpleado;
	private String nombreEmpleado;
	private String apellidoPrimeroEmpleado;
    private String apellidoSegundoEmpleado;
    private String nifEmpleado;
	private String descripcion; 
	private BigDecimal sueldo; 
	private BigDecimal extras; 
	private Date fechaNomina;
	
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
	
	public Long getEmpleadoId() {
		return empleadoId;
	}
	
	public void setEmpleadoId(Long empleadoId) {
		this.empleadoId = empleadoId;
	}
	
	public String getCodigoEmpleado() {
		return codigoEmpleado;
	}
	
	public void setCodigoEmpleado(String codigoEmpleado) {
		this.codigoEmpleado = codigoEmpleado;
	}
	
	public String getNombreEmpleado() {
		return nombreEmpleado;
	}
	
	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}
	
	public String getApellidoPrimeroEmpleado() {
		return apellidoPrimeroEmpleado;
	}
	
	public void setApellidoPrimeroEmpleado(String apellidoPrimeroEmpleado) {
		this.apellidoPrimeroEmpleado = apellidoPrimeroEmpleado;
	}
	
	public String getApellidoSegundoEmpleado() {
		return apellidoSegundoEmpleado;
	}
	
	public void setApellidoSegundoEmpleado(String apellidoSegundoEmpleado) {
		this.apellidoSegundoEmpleado = apellidoSegundoEmpleado;
	}
	
	public String getNifEmpleado() {
		return nifEmpleado;
	}
	
	public void setNifEmpleado(String nifEmpleado) {
		this.nifEmpleado = nifEmpleado;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public BigDecimal getSueldo() {
		return sueldo;
	}
	
	public void setSueldo(BigDecimal sueldo) {
		this.sueldo = sueldo;
	}
	
	public BigDecimal getExtras() {
		return extras;
	}
	
	public void setExtras(BigDecimal extras) {
		this.extras = extras;
	}
	
	public Date getFechaNomina() {
		return fechaNomina;
	}
	
	public void setFechaNomina(Date fechaNomina) {
		this.fechaNomina = fechaNomina;
	}
	
}
