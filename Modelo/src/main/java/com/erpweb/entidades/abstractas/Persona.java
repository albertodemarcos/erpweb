package com.erpweb.entidades.abstractas;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Persona {

	private String nombre;
    private String apellidoPrimero;
    private String apellidoSegundo;
    private String nif;
    
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
    
    
}
