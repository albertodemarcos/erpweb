package com.erpweb.repositorios.crm;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erpweb.entidades.crm.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	
	Cliente findByIdAndEmpresaId(Long id, Long empresaId);
	
	List<Cliente> findByIdInAndEmpresaId( List<Long> ids, Long empresaId);
	
}
