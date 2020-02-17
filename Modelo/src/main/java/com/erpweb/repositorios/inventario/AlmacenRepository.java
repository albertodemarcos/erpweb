package com.erpweb.repositorios.inventario;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erpweb.entidades.inventario.Almacen;

@Repository
public interface AlmacenRepository extends JpaRepository<Almacen, Long> {

	
	Almacen findByIdAndEmpresaId(Long id, Long empresaId);
	
	List<Almacen> findByIdInAndEmpresaId( List<Long> ids, Long empresaId);
}
