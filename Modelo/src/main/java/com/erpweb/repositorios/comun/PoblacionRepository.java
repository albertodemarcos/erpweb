package com.erpweb.repositorios.comun;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erpweb.entidades.comun.Poblacion;


@Repository
public interface PoblacionRepository extends JpaRepository<Poblacion, Long> {

	
	Optional<Poblacion> findById(Long id);
	
	List<Poblacion> findByProvinciaId(Long paisId);
	
	
	
}
