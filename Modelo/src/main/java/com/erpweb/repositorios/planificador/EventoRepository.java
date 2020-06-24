package com.erpweb.repositorios.planificador;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erpweb.entidades.planificador.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

	
	
	
	
	
	
}
