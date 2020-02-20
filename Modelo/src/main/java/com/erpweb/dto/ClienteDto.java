package com.erpweb.dto;

import java.io.Serializable;

import com.erpweb.utiles.enumerados.TipoCliente;

public class ClienteDto implements Serializable {

	private static final long serialVersionUID = 4050171406101916788L;

	private Long id;
    private String codigo;   
    private Long empresaId;
    private String nombre;
    private String apellidoPrimero;
    private String apellidoSegundo;
    private String nif;		
    private Long direccionPostalId;
	private Long poblacionId;
	private String nombrePoblacion;
	private String codigoDireccionPostal;
	private String codigoPostal;
	private String direccion; 		
	private String edificio;        
	private String observaciones;   
	private Long provinciaId;   
	private String nombreProvincia;
	private String telefono;
    private TipoCliente TipoCliente;
    
    
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
	
	public String getApellidoPrimero() {
		return apellidoPrimero;
	}

	public void setApellidoPrimero(String apellidoPrimero) {
		this.apellidoPrimero = apellidoPrimero;
	}

	public String getApellidoSegundo() {
		return apellidoSegundo;
	}

	public void setApellidoSegundo(String apellidoSegundo) {
		this.apellidoSegundo = apellidoSegundo;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}
	
	public Long getDireccionPostalId() {
		return direccionPostalId;
	}

	public void setDireccionPostalId(Long direccionPostalId) {
		this.direccionPostalId = direccionPostalId;
	}

	public Long getPoblacionId() {
		return poblacionId;
	}

	public void setPoblacionId(Long poblacionId) {
		this.poblacionId = poblacionId;
	}

	public String getNombrePoblacion() {
		return nombrePoblacion;
	}

	public void setNombrePoblacion(String nombrePoblacion) {
		this.nombrePoblacion = nombrePoblacion;
	}

	public String getCodigoDireccionPostal() {
		return codigoDireccionPostal;
	}

	public void setCodigoDireccionPostal(String codigoDireccionPostal) {
		this.codigoDireccionPostal = codigoDireccionPostal;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEdificio() {
		return edificio;
	}

	public void setEdificio(String edificio) {
		this.edificio = edificio;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Long getProvinciaId() {
		return provinciaId;
	}

	public void setProvinciaId(Long provinciaId) {
		this.provinciaId = provinciaId;
	}

	public String getNombreProvincia() {
		return nombreProvincia;
	}

	public void setNombreProvincia(String nombreProvincia) {
		this.nombreProvincia = nombreProvincia;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public TipoCliente getTipoCliente() {
		return TipoCliente;
	}
	
	public void setTipoCliente(TipoCliente tipoCliente) {
		TipoCliente = tipoCliente;
	}
    
}
