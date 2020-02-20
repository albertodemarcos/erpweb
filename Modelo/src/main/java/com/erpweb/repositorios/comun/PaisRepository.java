package com.erpweb.repositorios.comun;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erpweb.entidades.comun.Pais;


@Repository
public interface PaisRepository extends JpaRepository<Pais, Long> {

	Pais findOne(Long id);
	
	List<Pais> findByIdIn(List<Long> paisId);
	
	
	
}
