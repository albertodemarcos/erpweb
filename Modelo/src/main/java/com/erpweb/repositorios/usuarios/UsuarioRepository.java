package com.erpweb.repositorios.usuarios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erpweb.entidades.usuarios.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
/*
	Usuario findByIdAndEmpresaId(Long id, Long empresaId);
	
	List<Usuario> findByIdInAndEmpresaId( List<Long> ids, Long empresaId);
	
	*/
	
	
	
	
}
