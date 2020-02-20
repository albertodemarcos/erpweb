package com.erpweb.repositorios.comun;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erpweb.entidades.comun.Impuesto;

@Repository
public interface ImpuestoRepository extends JpaRepository<Impuesto, Long> {

	Optional<Impuesto> findById(Long id);
	
	List<Impuesto> findByPaisId(Long paisId);
	
	
}
