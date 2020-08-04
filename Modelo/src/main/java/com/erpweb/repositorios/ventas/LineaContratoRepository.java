package com.erpweb.repositorios.ventas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erpweb.entidades.ventas.LineaContrato;

@Repository
public interface LineaContratoRepository extends JpaRepository<LineaContrato, Long>{

}
