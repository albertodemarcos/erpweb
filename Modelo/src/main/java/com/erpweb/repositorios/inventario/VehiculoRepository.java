package com.erpweb.repositorios.inventario;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erpweb.entidades.inventario.Vehiculo;


@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {

	
	Vehiculo findByIdAndEmpresaId(Long id, Long empresaId);
	
	List< Vehiculo> findByIdInAndEmpresaId( List<Long> ids, Long empresaId);
	
	
	
	
}
