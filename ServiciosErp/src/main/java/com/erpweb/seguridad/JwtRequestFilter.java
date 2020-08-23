package com.erpweb.seguridad;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.erpweb.servicios.usuarios.UsuarioDetailsService;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private UsuarioDetailsService usuarioDetailsService;
	
	@Autowired
    private JwtUtil jwtUtil;
	
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		
		final String authorizationHeader = request.getHeader("Authorization");
		
		String username = null;
        String jwt = null;
		
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            username = jwtUtil.extractUsername(jwt);
        }
        
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = this.usuarioDetailsService.loadUserByUsername(username);

            if (jwtUtil.validateToken(jwt, userDetails)) {

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }	
        filterChain.doFilter(request, response);
		/*
		//Primero extraemos el token
		final String authorizationHeader = request.getHeader("Authorization");
		
		String username = null;
        String jwt = null;
		
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            username = jwtUtil.extractUsername(jwt);
        }
		
		//Comprobamos si viene el username, si no viene directmente no seguimos
        if(username == null) {
        	
        	filterChain.doFilter(request, response);
        	return;
        }
        
        //Si existe, comprobamos la sesion
        if(username != null) {
        	
        	//Recuperamos la sesion
        	HttpSession sessionNow = request.getSession();
        	SecurityContext securityContext = (SecurityContext) sessionNow.getAttribute("SPRING_SECURITY_CONTEXT");
        	
        	if(securityContext != null) {
        		
        		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        			
    			filterChain.doFilter(request, response);
        		
        	}else {
        		
        		UserDetails userDetails = this.usuarioDetailsService.loadUserByUsername(username);
        		
        		if (jwtUtil.validateToken(jwt, userDetails)) {

                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    
                    //Guardamos en sesion el contexto
                    HttpSession createdSesion = request.getSession(true);
                    createdSesion.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
                }
        		
        		filterChain.doFilter(request, response);
        	}
        }*/
	}

}
