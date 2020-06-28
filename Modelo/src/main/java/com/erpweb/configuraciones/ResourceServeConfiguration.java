/*package com.erpweb.configuraciones;

import java.util.Arrays;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableResourceServer
public class ResourceServeConfiguration extends ResourceServerConfigurerAdapter {


	@Override
	public void configure(HttpSecurity http) throws Exception {
		//Se necesita autentificacion para todas las rutas
		http.authorizeRequests()//.antMatchers(HttpMethod.GET, "/usuarios").permitAll()
		.anyRequest().authenticated()
		.and().cors().configurationSource(corsConfigurationSource());
	}

	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		
		CorsConfiguration configuracionCors = new CorsConfiguration();
		
		configuracionCors.setAllowedOrigins(Arrays.asList("http://localhost:4200")); //"*"
		configuracionCors.setAllowedMethods(Arrays.asList("GET","POST", "PUT", "DELETE", "OPTIONS","PATCH"));
		configuracionCors.setAllowCredentials(Boolean.TRUE);
		configuracionCors.setAllowedHeaders(Arrays.asList("Content-Type","Authorization"));
		
		UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
		corsConfigurationSource.registerCorsConfiguration("/**", configuracionCors);
		
		return corsConfigurationSource;
	}

	@Bean
	public CorsFilter corsFilter() {
	    return new CorsFilter(this.corsConfigurationSource());
	}
	
	
}*/
