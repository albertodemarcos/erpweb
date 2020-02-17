package com.erpweb.repositorios.compras;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erpweb.entidades.compras.Compra;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {

	Compra findByIdAndEmpresaId(Long id, Long empresaId);
	
	List<Compra> findByIdInAndEmpresaId( List<Long> ids, Long empresaId);
	
}
