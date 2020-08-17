package com.erpweb.seguridad;

import java.io.Serializable;

public class AutenticacionResponse implements Serializable {

	private static final long serialVersionUID = 5051427370756313716L;

	private final String jwt;
	
	public AutenticacionResponse(String jwt){
		this.jwt = jwt;
	}
		
	public String getJwt() {
		return jwt;
	}
	
}
