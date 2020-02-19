package com.erpweb.repositorios.ventas;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erpweb.entidades.ventas.Contrato;


@Repository
public interface ContratoRepository extends JpaRepository<Contrato, Long> {

	Contrato findByIdAndEmpresaId(Long id, Long empresaId);
	
	List<Contrato> findByIdInAndEmpresaId( List<Long> ids, Long empresaId);
	
	
}
