package com.erpweb.repositorios.facturacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erpweb.entidades.facturacion.Factura;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {

}
