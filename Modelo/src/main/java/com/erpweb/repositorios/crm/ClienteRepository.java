package com.erpweb.repositorios.crm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erpweb.entidades.crm.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
