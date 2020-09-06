package com.erpweb.seguridad;

import java.util.Base64;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.erpweb.repositorios.usuarios.UsuarioRepository;
import com.erpweb.utiles.AccionRespuesta;

@Service
public class SecurityService {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public AccionRespuesta obtieneRespuestaAccesoUsuario(AutenticacionRequest autenticacion) {
		
		try {
			
			String username = autenticacion.getUsername();
			
			String password = new String( Base64.getDecoder().decode( autenticacion.getPassword() ) );
			
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
			
			Authentication authentication = authenticationManager.authenticate(authenticationToken);
			
			if(authentication != null && authentication.isAuthenticated()) {
				
				final UserDetails userDetails = (UserDetails) authentication.getPrincipal();
				
				Long usuarioId  = this.usuarioRepository.obtieneIdDeUsername(userDetails.getUsername());
				
				final String jwt = jwtUtil.generateToken(userDetails);
				
				HashMap<String, Object> data = new HashMap<String, Object>();
				
				data.put("autenticacionResponse", new AutenticacionResponse(jwt) );
				
				data.put("id", usuarioId);
				
				return new AccionRespuesta(1L, "OK", Boolean.TRUE, data);
			}
			
			logger.error("Error, el usuario no existe o es erroneo las credenciales");
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
			
		}catch(BadCredentialsException badCredentialsException) {
			
			logger.error("Error en el metodo obtieneRespuestaAccesoUsuario() por causa: " + badCredentialsException.getMessage());
			
			badCredentialsException.printStackTrace();
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
			
		}catch(DisabledException disabledException) {

			logger.error("Error en el metodo obtieneRespuestaAccesoUsuario() por causa: " + disabledException.getMessage());
			
			disabledException.printStackTrace();
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
			
		}catch(LockedException lockedException) {
			
			logger.error("Error en el metodo obtieneRespuestaAccesoUsuario() por causa: " + lockedException.getMessage());
			
			lockedException.printStackTrace();
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
	}
	
}
