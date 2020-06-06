package com.erpweb.dto;

import java.io.Serializable;
import java.util.Date;

import com.erpweb.utiles.enumerados.TipoVehiculo;

public class VehiculoDto implements Serializable {

	private Long id;
	private String codigo;
	private String matricula;
	private String marca;
	private String modelo;
	private TipoVehiculo tipoVehiculo;
	private Date fechaMatriculacion;
	
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
	
	public String getMatricula() {
		return matricula;
	}
	
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	public String getMarca() {
		return marca;
	}
	
	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	public String getModelo() {
		return modelo;
	}
	
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	public TipoVehiculo getTipoVehiculo() {
		return tipoVehiculo;
	}
	
	public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}
	
	public Date getFechaMatriculacion() {
		return fechaMatriculacion;
	}
	
	public void setFechaMatriculacion(Date fechaMatriculacion) {
		this.fechaMatriculacion = fechaMatriculacion;
	}
	
	
	
}
