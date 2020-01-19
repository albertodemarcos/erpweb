package com.erpweb.repositorios.planificacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erpweb.entidades.planificacion.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

}
