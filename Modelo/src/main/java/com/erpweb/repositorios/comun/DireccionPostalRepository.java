package com.erpweb.repositorios.comun;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erpweb.entidades.comun.DireccionPostal;

@Repository
public interface DireccionPostalRepository extends JpaRepository<DireccionPostal, Long> {

	DireccionPostal findByIdAndEmpresaId(Long id, Long empresaId);
	
	List<DireccionPostal> findByIdInAndEmpresaId(List<Long> ids, Long empresaId);
}
