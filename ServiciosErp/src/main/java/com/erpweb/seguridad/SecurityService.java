package com.erpweb.seguridad;

import java.util.Base64;
import java.util.HashMap;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.erpweb.servicios.usuarios.UsuarioDetailsService;
import com.erpweb.utiles.AccionRespuesta;

@Service
public class SecurityService {

	/*@Autowired
	private AuthenticationManager authenticationManager;*/
	
	@Autowired
	private UsuarioDetailsService usuarioDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	//private final static String METODO_ENCRIPTADOR = "Blowfish";
	//private final static String PALABRA_ENCRIPTAR = "AngularApp";
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public AccionRespuesta obtieneRespuestaAccesoUsuario(AutenticacionRequest autenticacion) {
		
		try {
			
			String username = autenticacion.getUsername();
			
			String password = new String( Base64.getDecoder().decode( autenticacion.getPassword() ) );
			
			//Recuperamos el usuario
			final UserDetails userDetails = usuarioDetailsService.loadUserByUsername( username );
			
			if( userDetails == null ) {
				
				logger.error("Error, el usuario no existe");
				
				return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
			}
			
			//Comprobamos que la palabra encriptada y la desencriptada son la misma
			if( this.compruebaCredenciales(password, userDetails.getPassword() ) ) {
				
				final String jwt = jwtUtil.generateToken(userDetails);
				
				HashMap<String, Object> data = new HashMap<String, Object>();
				
				data.put("autenticacionResponse", new AutenticacionResponse(jwt) );
				
				return new AccionRespuesta(1L, "OK", Boolean.TRUE, data);
			}
			
			logger.error("Error, el usuario no existe");
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
			
		}catch(BadCredentialsException e) {
			
			logger.error("Error en el metodo obtieneRespuestaAccesoUsuario()");
			
			e.printStackTrace();
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
	}
	
	private Boolean compruebaCredenciales(String passwordEnviada, String passwordUser) {
		
		return passwordUser.equalsIgnoreCase(passwordEnviada);		
		
	}
	
	
	
}
