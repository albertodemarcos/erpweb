package com.erpweb.utiles;

import java.io.Serializable;
import java.util.HashMap;

public class AccionRespuesta implements Serializable {

	private static final long serialVersionUID = -9137312212281213465L;
	
	private Long id;
	private String codigo;
	private String respuesta;
	private Boolean resultado;
	private HashMap<String, Object> data;
	
	
	public AccionRespuesta() {
		super();
	}
	
	public AccionRespuesta(Long id, String codigo) {
		super();
		this.id = id;
		this.codigo = codigo;
	}
	
	public AccionRespuesta(Long id, String codigo, Boolean resultado ) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.resultado = resultado;
	}
	
	public AccionRespuesta(Long id, String codigo, Boolean resultado, HashMap<String, Object> data) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.resultado = resultado;
		this.data = data;
	}

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
	
	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	
	public Boolean getResultado() {
		return resultado;
	}
	
	public void setResultado(Boolean resultado) {
		this.resultado = resultado;
	}
	
	public HashMap<String, Object> getData() {
		return data;
	}
	
	public void setData(HashMap<String, Object> data) {
		this.data = data;
	}
	
}
