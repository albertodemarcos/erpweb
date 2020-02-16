package com.erpweb.repositorios.usuarios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erpweb.entidades.usuarios.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	
	
	
	
	
	
}
