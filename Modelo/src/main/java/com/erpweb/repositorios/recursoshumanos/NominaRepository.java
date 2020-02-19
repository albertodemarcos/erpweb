package com.erpweb.repositorios.recursoshumanos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erpweb.entidades.recursoshumanos.Nomina;

@Repository
public interface NominaRepository extends JpaRepository<Nomina, Long> {
/*
	Nomina findByIdAndEmpresaId(Long id, Long empresaId);
	
	List<Nomina> findByIdInAndEmpresaId( List<Long> ids, Long empresaId);
	
	*/
	
	
}
