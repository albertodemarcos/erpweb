package com.erpweb.repositorios.compras;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erpweb.entidades.compras.Proveedor;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {

	/*
	Proveedor findByIdAndEmpresaId(Long id, Long empresaId);
	
	List<Proveedor> findByIdInAndEmpresaId( List<Long> ids, Long empresaId);
	*/
	
}
