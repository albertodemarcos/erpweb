package com.erpweb.repositorios.bi;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erpweb.entidades.bi.Ingreso;

@Repository
public interface IngresoRepository extends JpaRepository<Ingreso, Long> {
/*
	Ingreso findByIdAndEmpresaId(Long id, Long empresaId);
	
	List<Ingreso> findByIdInAndEmpresaId(List<Long> ids, Long empresaId);
	*/
}
