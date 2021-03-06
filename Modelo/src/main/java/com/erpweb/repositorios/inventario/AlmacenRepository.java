package com.erpweb.repositorios.inventario;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.erpweb.entidades.inventario.Almacen;

@Repository
public interface AlmacenRepository extends JpaRepository<Almacen, Long> {

	
	Optional<Almacen> findById(Long id);
	
	List<Almacen> findByIdIn( List<Long> ids);
	
	Set<Almacen> findByIdIn( Set<Long> ids);
	
	@Query(" select a from Almacen a where lower(a.nombre) like :termino order by a.nombre ")
	public List<Almacen> obtenerTodosLosAlmacenes(@Param("termino") String termino);
	
}
