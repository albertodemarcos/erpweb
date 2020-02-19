package com.erpweb.repositorios.empresa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erpweb.entidades.empresa.Configuracion;


@Repository
public interface ConfiguracionRepository extends JpaRepository<Configuracion, Long> {

	
	Configuracion findByIdAndEmpresaId(Long id, Long empresaId);
	
	List<Configuracion> findByIdInAndEmpresaId( List<Long> ids, Long empresaId);
	
	
}
