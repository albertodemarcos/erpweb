package com.erpweb.repositorios.bi;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erpweb.entidades.bi.Gasto;
import com.erpweb.repositorios.bi.interfaces.GastoRepositoryInterfaz;


@Repository
public interface GastoRepository extends JpaRepository<Gasto, Long>, GastoRepositoryInterfaz {

		
	Optional<Gasto> findById(Long id);
	
	List<Gasto> findByIdIn( List<Long> ids);
	
	
}
