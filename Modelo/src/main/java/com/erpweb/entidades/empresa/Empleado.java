package com.erpweb.entidades.empresa;

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
import com.erpweb.utiles.enumerados.TipoEmpleado;

@Entity
@Table(name="empleado")
public class Empleado extends Persona implements Serializable {

	private static final long serialVersionUID = -1432951231505559165L;
	
	
	//Atributos Cliente
	private Long id;
    private String codigo;
	private TipoEmpleado tipoEmpleado;
	
	
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMPLEADO_SEQ")
    @SequenceGenerator(name="EMPLEADO_SEQ",sequenceName="SEQUENCE_EMPLEADO", allocationSize=1)
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

	@Enumerated(EnumType.STRING)
	public TipoEmpleado getTipoEmpleado() {
		return tipoEmpleado;
	}

	public void setTipoEmpleado(TipoEmpleado tipoEmpleado) {
		this.tipoEmpleado = tipoEmpleado;
	}
	
	
}
