package com.erpweb.repositorios.inventario;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.erpweb.entidades.inventario.Articulo;


@Repository
public interface ArticuloRepository extends JpaRepository<Articulo, Long> {


	Optional<Articulo> findById(Long id);
	
	List<Articulo> findByIdIn( List<Long> ids);
	
	List<Articulo> findByIdIn( Set<Long> ids);
	
	@Query(" select art from Articulo art where lower(art.nombre) like :termino order by art.nombre ")
	public List<Articulo> obtenerTodosLosArticulos(@Param("termino") String termino);
	
}
