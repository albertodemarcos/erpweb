package com.erpweb.entidades.usuarios;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

import com.erpweb.entidades.usuarios.interfaces.UsuarioInterfaz;


@Entity
@Table(name="usuario")
public class Usuario extends User implements UsuarioInterfaz, Serializable {

	private static final long serialVersionUID = -5624847003092101671L;
	
	private Long id;
    private String identidad;
    private String idioma; 
    
    public Usuario () {
    	
    }
    
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq_gen")
	//@SequenceGenerator(name = "users_seq_gen", sequenceName = "users_id_seq")
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
    
    public String getIdentidad() {
        return identidad;
    }

    public void setIdentidad(String identidad) {
        this.identidad = identidad;
    }
    
    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }
    
    public String mostrarDatos() {
	// TODO Auto-generated method stub
    	return this.getName();
    }
    
    
}
