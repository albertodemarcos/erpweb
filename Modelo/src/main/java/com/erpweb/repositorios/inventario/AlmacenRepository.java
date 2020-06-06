package com.erpweb.repositorios.inventario;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erpweb.entidades.inventario.Almacen;

@Repository
public interface AlmacenRepository extends JpaRepository<Almacen, Long> {

	
	Optional<Almacen> findById(Long id);
	
	List<Almacen> findByIdIn( List<Long> ids);
	
}
