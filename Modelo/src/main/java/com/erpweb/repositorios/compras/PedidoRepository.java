package com.erpweb.repositorios.compras;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.erpweb.entidades.compras.Pedido;

@Repository
public interface PedidoRepository  extends JpaRepository<Pedido, Long>{

	
	Optional<Pedido> findById(Long id);
	
	List<Pedido> findByIdIn( List<Long> ids);
	
	@Query(" select f.id from Pedido p inner join p.factura f where p.id=:pedidoId ")
	Long obtieneFacturaIdDesdePedidoId(@Param("pedidoId") Long pedidoId);
	
}
