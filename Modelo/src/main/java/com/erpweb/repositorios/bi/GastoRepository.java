package com.erpweb.repositorios.bi;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erpweb.entidades.bi.Gasto;

@Repository
public interface GastoRepository extends JpaRepository<Gasto, Long> {
/*
	Gasto findByIdAndEmpresaId(Long id, Long empresaId);
	
	List<Gasto> findByIdInAndEmpresaId( List<Long> ids, Long empresaId);
	*/
}
