package com.erpweb.repositorios.inventario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erpweb.entidades.inventario.Articulo;


@Repository
public interface ArticuloRepository extends JpaRepository<Articulo, Long> {

}
