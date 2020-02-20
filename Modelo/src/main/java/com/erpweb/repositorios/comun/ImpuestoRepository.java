package com.erpweb.repositorios.comun;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erpweb.entidades.comun.Impuesto;

@Repository
public interface ImpuestoRepository extends JpaRepository<Impuesto, Long> {

	Impuesto findOne(Long id);
	
	List<Impuesto> findByPaisId(Long paisId);
	
	
}
