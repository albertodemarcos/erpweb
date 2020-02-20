package com.erpweb.repositorios.bi;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;

import com.erpweb.entidades.bi.Gasto;
import com.erpweb.repositorios.bi.interfaces.GastoRepositoryInterfaz;

//@Transactional(readOnly = true)

@Repository
public interface GastoRepository extends JpaRepository<Gasto, Long>, GastoRepositoryInterfaz {

		
	Gasto findByIdAndEmpresaId(Long id, Long empresaId);
	
	List<Gasto> findByIdInAndEmpresaId( List<Long> ids, Long empresaId);
	
}
