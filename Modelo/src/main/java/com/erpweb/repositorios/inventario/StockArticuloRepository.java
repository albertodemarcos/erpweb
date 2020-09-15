package com.erpweb.repositorios.inventario;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.erpweb.entidades.inventario.StockArticulo;
import com.erpweb.utiles.Triple;

@Repository
public interface StockArticuloRepository extends JpaRepository<StockArticulo, Long> {

	
	Optional<StockArticulo> findByCodigo(String codigo);
	
	@Query(" select s.cantidad from StockArticulo s join s.almacen alm join s.articulo a where alm.id =:almacenId and a.id=:articuloId ")
	Long obtieneCantidadArticuloAlmacen(@Param("almacenId") Long almacenId, @Param("articuloId") Long articuloId);
	
	@Query(" select s from StockArticulo s inner join s.articulo art inner join s.almacen al where al.id in (:almacenesId) and art.id in (:articulosId) ")
	Set<StockArticulo> obtieneStockArticuloAlmacen(@Param("almacenesId") Set<Long> almacenesId, @Param("articulosId") Set<Long> articulosId);
	
	
	@Query(" select new com.erpweb.utiles.Triple(al.id, art.id, s.cantidad) " +
		   " from StockArticulo s inner join s.articulo art inner join s.almacen al " +
		   " where al.id in (:almacenesId) and art.id in (:articulosId) ")
	Set<Triple<Long, Long, Long>> obtieneStockArticuloIdAlmacenIdCantidad(@Param("almacenesId") Set<Long> almacenesId, @Param("articulosId") Set<Long> articulosId);
	
}
