package com.erpweb.repositorios.configuracion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erpweb.entidades.configuracionEmpresa.Empresa;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

}
