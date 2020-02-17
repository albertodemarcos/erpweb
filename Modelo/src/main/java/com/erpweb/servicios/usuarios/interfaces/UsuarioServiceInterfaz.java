package com.erpweb.servicios.usuarios.interfaces;

import com.erpweb.entidades.usuarios.Usuario;

public interface UsuarioServiceInterfaz {

	public void obtieneUsuario(Long id, Long empresaId); //Obtenemos el usuario de BBDD
	
	public void obtieneUsuarioDto(Long id, Long empresaId); //Obtenemos el usuario y lo llevamos a capa vista mediante dto
	
	public void actualizaUsuario(Usuario usuario); //Actualizamos el usuario

	public void eliminaUsuario(Usuario usuario); //Borramos el usuario
	
	
}
