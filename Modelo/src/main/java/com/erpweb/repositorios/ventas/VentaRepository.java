package com.erpweb.repositorios.ventas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erpweb.entidades.ventas.Venta;


@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {

}
