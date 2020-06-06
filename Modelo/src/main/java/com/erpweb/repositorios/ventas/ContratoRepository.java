package com.erpweb.repositorios.ventas;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erpweb.entidades.ventas.Contrato;


@Repository
public interface ContratoRepository extends JpaRepository<Contrato, Long> {

	Optional<Contrato> findById(Long id);
	
	List<Contrato> findByIdIn( List<Long> ids);
	
	
}
