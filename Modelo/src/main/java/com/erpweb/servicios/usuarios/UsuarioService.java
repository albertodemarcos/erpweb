package com.erpweb.servicios.usuarios;

import org.springframework.stereotype.Service;

import com.erpweb.dto.UsuarioDto;
import com.erpweb.entidades.usuarios.Usuario;
import com.erpweb.servicios.usuarios.interfaces.UsuarioServiceInterfaz;



@Service
public class UsuarioService implements UsuarioServiceInterfaz {

	@Override
	public Boolean creaUsuarioDesdeUsuarioDto(UsuarioDto usuarioDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UsuarioDto obtieneUsuarioDto(Long id, Long empresaId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean actualizaUsuario(UsuarioDto usuarioDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean eliminaUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario obtieneUsuario(Long id, Long empresaId) {
		// TODO Auto-generated method stub
		return null;
	}


}
