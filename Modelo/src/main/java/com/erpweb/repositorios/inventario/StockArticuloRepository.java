package com.erpweb.repositorios.inventario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.erpweb.entidades.inventario.StockArticulo;

@Repository
public interface StockArticuloRepository extends JpaRepository<StockArticulo, Long> {

	
	Optional<StockArticulo> findByCodigo(String codigo);
	
	@Query(" select s.cantidad from StockArticulo s join s.almacen alm join s.articulo a where alm.id =:almacenId and a.id=:articuloId ")
	Long obtieneCantidadArticuloAlmacen(@Param("almacenId") Long almacenId, @Param("articuloId") Long articuloId);
	
	
}
