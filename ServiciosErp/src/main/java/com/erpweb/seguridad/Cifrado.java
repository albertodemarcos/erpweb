package com.erpweb.seguridad;

//import java.nio.charset.StandardCharsets;
//import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class Cifrado {

	 /*public static String cifrado(String content, String key) {
	       
		 String encCon = "";
	        String ivStr = "";

	        try {
	            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes("UTF-8"), "Blowfish");
	            Cipher cipher = Cipher.getInstance("Blowfish/CBC/PKCS5Padding");

	            byte[] iv = new byte[cipher.getBlockSize()];
	            SecureRandom secureRandom = new SecureRandom();
	            secureRandom.nextBytes(iv);
	            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

	            String secret = content;
	            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParameterSpec);
	            byte[] encoding = cipher.doFinal(secret.getBytes("UTF-8"));

	            System.out.println("-- Encrypted -----------");
	            encCon = DatatypeConverter.printBase64Binary(encoding);
	            ivStr = DatatypeConverter.printBase64Binary(iv);
	            System.out.println("-- encCon : " + encCon);
	            System.out.println("-- iv : " + ivStr);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }

	        return encCon + ":" + ivStr;
	    }*/

	    public static String descifrado(String encContent, String key) {
	        String decCon = "";

	        try {
	            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes("UTF-8"), "Blowfish");
	            Cipher cipher = Cipher.getInstance("Blowfish/CBC/PKCS5Padding");

	            byte[] iv = DatatypeConverter.parseBase64Binary(encContent.substring(encContent.indexOf(":") + 1));

	            String secret = encContent.substring(0, encContent.indexOf(":"));
	            cipher.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv));
	            byte[] decoding = cipher.doFinal(Base64.getDecoder().decode(secret));

	            System.out.println("-- Decrypted -----------");
	            decCon = new String(decoding, "UTF-8");
	            System.out.println("-- decCon : " + decCon);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }

	        return decCon;
	    }

	    /*public static void main(String args[]) {
	        String e = Crypto.enc("hello world", "key123");
	        String d = Crypto.dec(e, "key123");
	    }*/
	    
	    /*public static String enc(String content, String key) {
	        String encCon = "";

	        try {
	            String IV = "12345678";

	            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "Blowfish");
	            Cipher        cipher  = Cipher.getInstance("Blowfish/CBC/PKCS5Padding");

	            byte[] nonce = new byte[cipher.getBlockSize()];
	            SECURE_RANDOM.nextBytes(nonce);

	            // Construct plaintext = nonce + secret
	            byte[] secret    = content.getBytes(StandardCharsets.UTF_8);
	            byte[] plaintext = new byte[nonce.length + secret.length];
	            System.arraycopy(nonce, 0, plaintext, 0, nonce.length);
	            System.arraycopy(secret, 0, plaintext, nonce.length, secret.length);

	            cipher.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(IV.getBytes(StandardCharsets.UTF_8)));
	            byte[] encoding = cipher.doFinal(plaintext);

	            encCon = DatatypeConverter.printBase64Binary(encoding);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }

	        return encCon;
	    }*/

	    /*public static String dec(String content, String key) {
	        String decCon = "";

	        try {
	            String IV = "12345678";

	            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "Blowfish");
	            Cipher        cipher  = Cipher.getInstance("Blowfish/CBC/PKCS5Padding");

	            // Decode Base64
	            byte[] ciphertext = DatatypeConverter.parseBase64Binary(content);

	            // Decrypt
	            cipher.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(IV.getBytes(StandardCharsets.UTF_8)));
	            byte[] message = cipher.doFinal(ciphertext);

	            decCon = new String(message,
	                                cipher.getBlockSize(),
	                                message.length - cipher.getBlockSize(),
	                                StandardCharsets.UTF_8);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }

	        return decCon;
	    }*/
}
/*
 //Recuperamos el usuario
		final UserDetails userDetails = usuarioDetailsService.loadUserByUsername( autenticacion.getUsername() );
		
		if( userDetails == null ) {
			
			logger.error("Error, el usuario no existe");
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
		
		String textoCodificado = this.encriptarTexto("AngularApp", userDetails.getPassword());
		
		logger.trace("Texto: " + textoCodificado);
		
		//Desencriptamos lo que nos envian..
		//String textoDesencriptado = Cifrado.descifrado(autenticacion.getPassword(), userDetails.getPassword()); //this.desencriptarTexto(autenticacion.getPassword(), userDetails.getPassword() );
		
		try {
			//Comprobamos que la palabra encriptada y la desencriptada son la misma
			if( StringUtils.isNotBlank(password) && password.equals(userDetails.getPassword()) ) {
				
				final String jwt = jwtUtil.generateToken(userDetails);
				
				HashMap<String, Object> data = new HashMap<String, Object>();
				
				data.put("autenticacionResponse", new AutenticacionResponse(jwt) );
				
				return new AccionRespuesta(1L, "OK", Boolean.TRUE, data);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
*/
/*private Boolean comprobarTextoEncriptado(String textoDesencriptado) throws Exception {

logger.trace("Entramos en el metodo comprobarTextoEncriptado() con texto descodificado={}", textoDesencriptado); 

if( StringUtils.isNotBlank(textoDesencriptado) && PALABRA_ENCRIPTAR.equals(textoDesencriptado) ) {
	
	return Boolean.TRUE;
}
	
return Boolean.FALSE;
}*/

/*private String encriptarTexto(String texto, String password) {

logger.trace("Entramos en el metodo desencriptarTexto() con texto codificado={}", texto);

try {
	
	byte[] keyData = (password).getBytes();
	
	SecretKeySpec secretKeySpec = new SecretKeySpec(keyData, "Blowfish" );
	
	Cipher cipher = Cipher.getInstance("Blowfish");
	
	cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
	
	byte[] hasil = cipher.doFinal( texto.getBytes() );
	
	return new String(Base64.getEncoder().encode(hasil));
	
}catch(Exception e) {
	
	logger.error("Error en el metodo encriptarTexto() con texto codificado={}", texto);
	
	e.printStackTrace();
	
	return null;
}
}*/


/*private String desencriptarTexto(String texto, String password) {

logger.trace("Entramos en el metodo desencriptarTexto() con texto codificado={}", texto); 

try {
	
	byte[] keyData = (password.trim()).getBytes();
	
	logger.debug("Datos: " + keyData.toString());
	
	SecretKeySpec secretKeySpec = new SecretKeySpec(keyData, METODO_ENCRIPTADOR );

	Cipher cipher = Cipher.getInstance("Blowfish/ECB/PKCS5Padding");
	
	cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
	
	byte[] textoBase64 = Base64.getDecoder().decode(texto);
	
	String textp = textoBase64.toString();
	
	byte[] hasil = cipher.doFinal( textoBase64 );
	
	return new String(hasil);

}catch(Exception e){
	
	logger.error("Error en el metodo desencriptarTexto() con texto codificado={}", texto);

	e.printStackTrace();
	
	return null;
}
}*/