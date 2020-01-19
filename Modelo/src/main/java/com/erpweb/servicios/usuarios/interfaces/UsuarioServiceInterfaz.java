package com.erpweb.servicios.usuarios.interfaces;

import com.erpweb.entidades.usuarios.Usuario;
import com.erpweb.utiles.dao.UsuarioDao;

public interface UsuarioServiceInterfaz {

	public Usuario obtieneUsuarioDeUsuarioDao(UsuarioDao usuarioDao);
	
	public UsuarioDao obtieneUsuarioDaoDeUsuario(Usuario usuario);
	
	public void persisteUsuario(Usuario usuario);
	
	public void destruyeUsuario(Usuario usuario);
}
