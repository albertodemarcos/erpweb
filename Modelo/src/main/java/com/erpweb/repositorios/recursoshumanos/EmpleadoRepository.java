package com.erpweb.repositorios.recursoshumanos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erpweb.entidades.recursoshumanos.Empleado;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

	
	Empleado findByIdAndEmpresaId(Long id, Long empresaId);
	
	List<Empleado> findByIdInAndEmpresaId( List<Long> ids, Long empresaId);
	
	
	
	
}
