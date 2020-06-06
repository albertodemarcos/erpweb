package com.erpweb.repositorios.bi;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erpweb.entidades.bi.Informe;

@Repository
public interface InformeRepository extends JpaRepository<Informe, Long > {
	
	 
	Optional<Informe> findById( Long id  );
	
	List<Informe> findByIdIn(List<Long> ids  );
	
	
	
}
