package com.erpweb.repositorios.bi;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erpweb.entidades.bi.Ingreso;

@Repository
public interface IngresoRepository extends JpaRepository<Ingreso, Long> {

	Optional<Ingreso> findById(Long id);
	
	List<Ingreso> findByIdIn(List<Long> ids);

	
}
