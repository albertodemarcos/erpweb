/*package com.erpweb.configuraciones;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.erpweb.entidades.usuarios.Usuario;
import com.erpweb.servicios.usuarios.UsuarioService;

@Component
public class InformacionAdiccionalToken implements TokenEnhancer {

	@Autowired
	private UsuarioService usuarioService; 
	
	@SuppressWarnings("deprecation")
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		
		Usuario usuario = usuarioService.devolverInformacionUsuario(authentication.getName());
		
		Map<String, Object> informacion = new HashMap<String, Object>();
		
		informacion.put("usuario_autenticado", usuario);
		
		( (DefaultOAuth2AccessToken) accessToken ).setAdditionalInformation(informacion);
		
		
		return accessToken;
	}

	
	
	
	
}*/
