package com.erpweb.repositorios.inventario;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erpweb.entidades.inventario.Articulo;


@Repository
public interface ArticuloRepository extends JpaRepository<Articulo, Long> {


	Articulo findByIdAndEmpresaId(Long id, Long empresaId);
	
	List<Articulo> findByIdInAndEmpresaId( List<Long> ids, Long empresaId);
}
