package com.erpweb.repositorios.facturacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erpweb.entidades.facturacion.Venta;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {

}
