package com.erpweb.repositorios.bi;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erpweb.entidades.bi.Informe;

@Repository
public interface InformeRepository extends JpaRepository<Informe, Long> {
	
	Informe findByIdAndEmpresaId(Long id, Long empresaId);
	
	List<Informe> findByIdInAndEmpresaId(List<Long> ids, Long empresaId);
	
}
