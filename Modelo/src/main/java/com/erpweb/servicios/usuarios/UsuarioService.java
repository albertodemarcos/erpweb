package com.erpweb.servicios.usuarios;

import org.springframework.stereotype.Service;

import com.erpweb.entidades.usuarios.Usuario;
import com.erpweb.servicios.usuarios.interfaces.UsuarioServiceInterfaz;
import com.erpweb.utiles.dao.UsuarioDao;



@Service
public class UsuarioService implements UsuarioServiceInterfaz {

	@Override
	public Usuario obtieneUsuarioDeUsuarioDao(UsuarioDao usuarioDao) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UsuarioDao obtieneUsuarioDaoDeUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void persisteUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
	}

	@Override
	public void destruyeUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
	}

}
