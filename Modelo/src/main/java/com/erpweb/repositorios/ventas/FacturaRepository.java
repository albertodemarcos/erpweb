package com.erpweb.repositorios.ventas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erpweb.entidades.ventas.Factura;


@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {

}
