package com.erpweb.repositorios.compras;

//import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erpweb.entidades.compras.Compra;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {

	
	
}
