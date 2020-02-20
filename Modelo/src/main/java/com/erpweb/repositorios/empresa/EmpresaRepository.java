package com.erpweb.repositorios.empresa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erpweb.entidades.empresa.Empresa;


@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

	
	Optional<Empresa> findById(Long id);
	
	List<Empresa> findByIdIn( List<Long> empresasId);
	
	
}
