package com.erpweb.repositorios.bi.implementacion;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.erpweb.entidades.bi.Gasto;
import com.erpweb.repositorios.bi.interfaces.GastoRepositoryInterfaz;

public class GastoRepositoryJpa implements GastoRepositoryInterfaz  {

	@PersistenceContext
	private EntityManager em;

	
/*
	@Override
	public List<Gasto> listaGastoDeEmpresa(Long empresaId) {
		// TODO Auto-generated method stub
		
		Query query = em.createQuery("select g from Gasto g where g.empresa.id =: "+empresaId);
			
		
		return null;
	}
	
	*/
	
	

}
