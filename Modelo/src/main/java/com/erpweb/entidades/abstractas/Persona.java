package com.erpweb.entidades.abstractas;

import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;

import com.erpweb.entidades.embebidos.DireccionPostal;
import com.erpweb.entidades.embebidos.OrigenPersona;

@MappedSuperclass
public abstract class Persona {

	private String codigo;
	private String nombre;
    private String apellidoPrimero;
    private String apellidoSegundo;
    private String nif;
    private DireccionPostal direccionPostal; 	 //Atributos de direccion postal
    private OrigenPersona origenPersona;		 //Atributos origen
   	
    
    public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
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

    @Embedded
	public DireccionPostal getDireccionPostal() {
		return direccionPostal;
	}

    
	public void setDireccionPostal(DireccionPostal direccionPostal) {
		this.direccionPostal = direccionPostal;
	}

	@Embedded
	public OrigenPersona getOrigenPersona() {
		return origenPersona;
	}

	public void setOrigenPersona(OrigenPersona origenPersona) {
		this.origenPersona = origenPersona;
	}
    
}