package com.erpweb.repositorios.compras;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erpweb.entidades.compras.LineaPedido;

@Repository
public interface LineaPedidoRepository extends JpaRepository<LineaPedido, Long> {

}
