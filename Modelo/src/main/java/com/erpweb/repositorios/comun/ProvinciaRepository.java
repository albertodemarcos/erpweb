package com.erpweb.repositorios.comun;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erpweb.entidades.comun.Provincia;

@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, Long> {

	Provincia findOne(Long id);
	
	List<Provincia> findByPaisId(Long paisId);
}
