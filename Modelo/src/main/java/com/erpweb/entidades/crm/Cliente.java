package com.erpweb.entidades.crm;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.erpweb.entidades.abstractas.Persona;
import com.erpweb.utiles.enumerados.TipoCliente;


@Entity
@Table(name="cliente")
public class Cliente extends Persona implements Serializable {

	private static final long serialVersionUID = -5390944868366830324L;
	
	//Atributos Cliente
	private Long id;
    
    private TipoCliente tipoCliente;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLIENTE_SEQ")
	@SequenceGenerator(name="CLIENTE_SEQ",sequenceName="SEQUENCE_CLIENTE", allocationSize=1)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Enumerated(EnumType.STRING)
	public TipoCliente getTipoCliente() {
		return tipoCliente;
	}
	
	public void setTipoCliente(TipoCliente tipoCliente) {
		this.tipoCliente = tipoCliente;
	}
	
}
