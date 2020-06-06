package com.erpweb.repositorios.compras;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erpweb.entidades.compras.Proveedor;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {

	
	Optional<Proveedor> findById(Long id);
	
	List<Proveedor> findByIdInAndEmpresaId( List<Long> ids);
	
	
}
