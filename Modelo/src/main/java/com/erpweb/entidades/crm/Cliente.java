package com.erpweb.entidades.crm;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.erpweb.entidades.abstractas.Persona;
import com.erpweb.utiles.enumerados.TipoCliente;


@Entity
@Table(name="cliente")
public class Cliente extends Persona implements Serializable {

	private static final long serialVersionUID = -5390944868366830324L;
	
	//Atributos Cliente
	private Long id;
    private String codigo;
    private TipoCliente tipoCliente;
	
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
	
	public TipoCliente getTipoCliente() {
		return tipoCliente;
	}
	
	public void setTipoCliente(TipoCliente tipoCliente) {
		this.tipoCliente = tipoCliente;
	}
	
}
