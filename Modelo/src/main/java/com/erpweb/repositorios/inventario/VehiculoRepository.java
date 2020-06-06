package com.erpweb.repositorios.inventario;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erpweb.entidades.inventario.Vehiculo;


@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {

	
	Optional<Vehiculo> findById(Long id);
	
	List< Vehiculo> findByIdIn( List<Long> ids);
	
	
	
	
}
