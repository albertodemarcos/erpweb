package com.erpweb.repositorios.empresa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erpweb.entidades.empresa.Empleado;


@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

	
	Optional<Empleado> findById(Long id);
	
	List<Empleado> findByIdIn( List<Long> ids);
	
	
	
	
}
