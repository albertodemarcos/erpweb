package com.erpweb.servicios.usuarios;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.erpweb.entidades.usuarios.Usuario;
import com.erpweb.repositorios.usuarios.UsuarioRepository;

@Service
public class UsuarioDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		logger.trace("Entramos en el metodo loadUserByUsername()");
		
		UserDetails usuarioDetails = null;
		
		try {
			// Buscamos el usuario por el username
			Usuario usuario = usuarioRepository.findByUsername(username);
			
			if( usuario == null ) {
				
				return null;
			}
			
			usuarioDetails = new User(usuario.getUsername(), usuario.getPassword(), new ArrayList<>());
			
		} catch(Exception e) {
			
			logger.error("Error en el metodo loadUserByUsername()" );
			
			e.printStackTrace();
		}
		
		return usuarioDetails;
	}

}
