package com.erpweb.entidades.usuarios;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

@SuppressWarnings("deprecation")
public class User implements ClientDetails, Serializable {
	
	private static final long serialVersionUID = 8150407611719188413L;
	
	private Usuario usuario;
	
	
	public User() {
		super();		
	}
	

	public User(Usuario usuario) {
		super();
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	@Override
	public String getClientId() {
		// TODO Auto-generated method stub
		return this.usuario.getUsername();
	}


	@Override
	public Set<String> getResourceIds() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean isSecretRequired() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public String getClientSecret() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean isScoped() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public Set<String> getScope() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Set<String> getAuthorizedGrantTypes() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Set<String> getRegisteredRedirectUri() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Integer getAccessTokenValiditySeconds() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Integer getRefreshTokenValiditySeconds() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean isAutoApprove(String scope) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public Map<String, Object> getAdditionalInformation() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
