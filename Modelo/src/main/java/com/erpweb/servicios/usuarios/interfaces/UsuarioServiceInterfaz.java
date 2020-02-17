package com.erpweb.servicios.usuarios.interfaces;

import com.erpweb.dto.UsuarioDto;
import com.erpweb.entidades.usuarios.Usuario;

public interface UsuarioServiceInterfaz {

	public Boolean creaUsuarioDesdeUsuarioDto(UsuarioDto usuarioDto); //Crea el usuario mediante dto
	
	public UsuarioDto obtieneUsuarioDto(Long id, Long empresaId); //Visualizar el usuario
	
	public Boolean actualizaUsuario(UsuarioDto usuarioDto); //Actualizamos el usuario

	public Boolean eliminaUsuario(Usuario usuario); //Borramos el usuario
	
	public Usuario obtieneUsuario(Long id, Long empresaId); //Obtenemos el usuario de BBDD

}
