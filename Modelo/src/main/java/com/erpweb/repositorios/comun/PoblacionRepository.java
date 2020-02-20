package com.erpweb.repositorios.comun;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erpweb.entidades.comun.Poblacion;


@Repository
public interface PoblacionRepository extends JpaRepository<Poblacion, Long> {

	
	Poblacion findOne(Long id);
	
	List<Poblacion> findByProvinciaId(Long paisId);
	
	
	
}
