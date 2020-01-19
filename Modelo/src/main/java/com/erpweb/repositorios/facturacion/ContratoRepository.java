package com.erpweb.repositorios.facturacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erpweb.entidades.facturacion.Contrato;

@Repository
public interface ContratoRepository extends JpaRepository<Contrato, Long> {

}
