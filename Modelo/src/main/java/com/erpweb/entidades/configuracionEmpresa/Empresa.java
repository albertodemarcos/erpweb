package com.erpweb.entidades.configuracionEmpresa;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.erpweb.utiles.enumerados.TipoSociedadJuridica;

@Entity
@Table(name="empresa")
public class Empresa implements Serializable {

	private static final long serialVersionUID = 4010444593235602942L;
	
	private Long id;
	private String codigo;
	private String nombre;
	private TipoSociedadJuridica tipoSociedadJuridica;
	private String cif;
	
	
	
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
 
	@Enumerated
	public TipoSociedadJuridica getTipoSociedadJuridica() {
		return tipoSociedadJuridica;
	}

	public void setTipoSociedadJuridica(TipoSociedadJuridica tipoSociedadJuridica) {
		this.tipoSociedadJuridica = tipoSociedadJuridica;
	}

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}
	
	
}
