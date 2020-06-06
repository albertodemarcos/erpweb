package com.erpweb.repositorios.crm;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erpweb.entidades.crm.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	
	Optional<Cliente> findById(Long id);
	
	List<Cliente> findByIdIn( List<Long> ids);
	
}
