package com.erpweb.repositorios.recursoshumanos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erpweb.entidades.recursoshumanos.Empleado;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

	
	
	
	
	
}
