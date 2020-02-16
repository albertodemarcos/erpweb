package com.erpweb.repositorios.ventas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erpweb.entidades.ventas.Contrato;


@Repository
public interface ContratoRepository extends JpaRepository<Contrato, Long> {

}
