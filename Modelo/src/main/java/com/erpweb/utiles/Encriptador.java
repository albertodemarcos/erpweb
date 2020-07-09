package com.erpweb.utiles;

import java.util.Base64;


import org.springframework.stereotype.Component;

@Component
public class Encriptador {
	
	private static final String CLAVE_SECRETA = "AngularApp";

	
	// Metodo de desencriptacion de la contraseña
	public String desencriptarBase64(String datosEncriptados)  {
		
		try {
			
			byte[] bytesEncriptados = Base64.getDecoder().decode(datosEncriptados);
			
			String password = new String(bytesEncriptados).replace(CLAVE_SECRETA, "").trim();
			
			return password;
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
			return null;			
		}
	}

}
