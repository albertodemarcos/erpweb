package com.erpweb.repositorios.compras;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erpweb.entidades.compras.LineaCompra;

@Repository
public interface LineaCompraRepository extends JpaRepository<LineaCompra, Long> {
	
	
	

}
