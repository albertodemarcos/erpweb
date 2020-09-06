package com.erpweb.repositorios.usuarios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.erpweb.dto.UsuarioDto;
import com.erpweb.entidades.usuarios.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Optional<Usuario> findById(Long id);
	
	List<Usuario> findByIdIn( List<Long> ids);
	
	Usuario findByUsername(String usuario);	
	
	@Query("select distinct u.id from Usuario u where u.username=:username ")
	Long obtieneIdDeUsername(@Param("username") String username);
	
	@Query("select ( count( distinct u.id ) > 0 ) from Usuario u where u.username=:paramRequest ")
	Boolean compruebaSiExiteElUsuarioConUsername(@Param("paramRequest") String paramRequest);
	
	@Query(" select u.username from Usuario u where u.username=:tokenUsername ")
	String obtieneUsernameDeToken(@Param("tokenUsername") String tokenUsername);
	
	
	@Query(" select new com.erpweb.dto.UsuarioDto(u.username, u.password, u.role) from Usuario u where u.username=:tokenUsername ")
	UsuarioDto obtieneUsuarioToken(@Param("tokenUsername") String tokenUsername);
	
}
