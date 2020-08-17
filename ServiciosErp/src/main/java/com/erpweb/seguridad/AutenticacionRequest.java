package com.erpweb.seguridad;

import java.io.Serializable;

public class AutenticacionRequest implements Serializable {

	private static final long serialVersionUID = -8594954434167657481L;

	private String username;
	private String password;

	//Constructor por defecto
	public AutenticacionRequest() {

	}

	//Constructor
	public AutenticacionRequest(String username, String password) {
		this.setUsername(username);
		this.setPassword(password);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
