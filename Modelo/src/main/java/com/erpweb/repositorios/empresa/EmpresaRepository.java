package com.erpweb.repositorios.empresa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erpweb.entidades.empresa.Empresa;


@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

	
	Empresa findOne(Long id);
	
	List<Empresa> findByIdInAndEmpresaId( List<Long> ids, Long empresaId);
	
	
}
