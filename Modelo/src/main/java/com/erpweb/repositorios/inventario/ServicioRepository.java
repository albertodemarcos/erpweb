package com.erpweb.repositorios.inventario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erpweb.entidades.compras.Servicio;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Long> {

}
