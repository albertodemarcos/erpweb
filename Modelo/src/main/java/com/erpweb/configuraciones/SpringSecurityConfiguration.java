package com.erpweb.configuraciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.erpweb.servicios.usuarios.UsuarioService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

		
	@Autowired
    private UsuarioService  usuarioService; //  UserDetailsService
	

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Override
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.usuarioService).passwordEncoder(passwordEncoder());			
	}

	@Bean("authenticationManager")
	@Override
	public AuthenticationManager authenticationManager() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManager();
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		http.formLogin().disable();         
        //http.csrf().disable();
        
        /*http.authorizeRequests().antMatchers(HttpMethod.POST, "/v1/users", "/v1/oauth/token").permitAll()
        .anyRequest().authenticated()
        .and()          
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);*/
		
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/oauth/token").permitAll()
		.anyRequest().authenticated()
		.and()
		.csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	/*@Override
	public void configure(WebSecurity web) throws Exception {
		 web.ignoring().antMatchers( "/**", "/oauth/token" ,"/oauth/**" ).anyRequest();		 
	}*/
	
	
}
