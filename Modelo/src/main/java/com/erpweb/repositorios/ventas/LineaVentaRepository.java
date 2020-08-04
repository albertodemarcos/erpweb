package com.erpweb.repositorios.ventas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erpweb.entidades.ventas.LineaVenta;

@Repository
public interface LineaVentaRepository extends JpaRepository<LineaVenta, Long> {

}
