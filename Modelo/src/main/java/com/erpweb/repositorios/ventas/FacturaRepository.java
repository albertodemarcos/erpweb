package com.erpweb.repositorios.ventas;

//import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erpweb.entidades.ventas.Factura;


@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {

	/*
	Factura findByIdAndEmpresaId(Long id, Long empresaId);
	
	List<Factura> findByIdInAndEmpresaId( List<Long> ids, Long empresaId);
	*/
}
