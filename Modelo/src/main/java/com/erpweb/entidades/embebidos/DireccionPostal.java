package com.erpweb.entidades.embebidos;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class DireccionPostal implements Serializable {

	private static final long serialVersionUID = 791932759603132491L;
	
	
	private String codigoPostal;		// Codigo postal de la localidad
	private String direccion; 			// Calle/avenida/plaza, etc. con numero
	private String edificio; 			// Edificio planta y letra
	private String observaciones;		// Observaciones sobre la direccion postal
	private String telefono;			// Telefono de contacto
	
	
	public DireccionPostal() {
		super();		
	}

	public DireccionPostal(String codigoPostal, String direccion, String edificio, String observaciones,
			String telefono) {
		super();
		this.codigoPostal = codigoPostal;
		this.direccion = direccion;
		this.edificio = edificio;
		this.observaciones = observaciones;
		this.telefono = telefono;
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

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}
