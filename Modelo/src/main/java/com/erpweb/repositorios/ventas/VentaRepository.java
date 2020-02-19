package com.erpweb.repositorios.ventas;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erpweb.entidades.ventas.Venta;


@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {

	Venta findByIdAndEmpresaId(Long id, Long empresaId);
	
	List<Venta> findByIdInAndEmpresaId( List<Long> ids, Long empresaId);
	
	
	
}
