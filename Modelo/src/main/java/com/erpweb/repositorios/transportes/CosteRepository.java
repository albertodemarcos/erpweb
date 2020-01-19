package com.erpweb.repositorios.transportes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erpweb.entidades.transportes.Coste;

@Repository
public interface CosteRepository extends JpaRepository<Coste, Long> {

}
