package com.erpweb.repositorios.inventario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erpweb.entidades.inventario.Almacen;

@Repository
public interface AlmacenRepository extends JpaRepository<Almacen, Long> {

}
