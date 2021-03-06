package com.erpweb.repositorios.ventas;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erpweb.entidades.ventas.Factura;


@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {

	
	Optional<Factura> findById(Long id);
	
	List<Factura> findByIdIn( List<Long> ids);
	
}
