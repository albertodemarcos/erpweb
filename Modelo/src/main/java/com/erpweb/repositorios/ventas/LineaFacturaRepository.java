package com.erpweb.repositorios.ventas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erpweb.entidades.ventas.LineaFactura;

@Repository
public interface LineaFacturaRepository extends JpaRepository<LineaFactura, Long> {

}
