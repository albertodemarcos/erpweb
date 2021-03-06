package com.erpweb.repositorios.compras;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.erpweb.entidades.compras.Compra;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {

	Optional<Compra> findById(Long id);
	
	List<Compra> findByIdIn( List<Long> ids);
	
	@Query(" select f.id from Compra c inner join c.factura f where c.id=:compraId ")
	Long obtieneFacturaIdDesdeCompraId(@Param("compraId") Long compraId);
	
}
