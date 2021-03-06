package com.erpweb.repositorios.ventas;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.erpweb.entidades.ventas.Venta;


@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {

	Optional<Venta> findById(Long id);
	
	List<Venta> findByIdIn( List<Long> ids);
	
	@Query(" select f.id from Venta v inner join v.factura f where v.id=:ventaId ")
	Long obtieneFacturaIdDesdeVentaId(@Param("ventaId") Long ventaId);
}