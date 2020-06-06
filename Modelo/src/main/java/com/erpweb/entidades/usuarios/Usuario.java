package com.erpweb.entidades.usuarios;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;



@Entity
@Table(name="usuario")
public class Usuario extends User implements Serializable {

	private static final long serialVersionUID = -5624847003092101671L;
	
	private Long id;
	private String codigo;
    private String identidad;
    private String idioma; 
    
    public Usuario () {
    	
    }
    
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
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
    
    
}
