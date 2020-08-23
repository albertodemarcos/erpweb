package com.erpweb.servicios.generales;

import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpweb.entidades.inventario.Almacen;
import com.erpweb.repositorios.inventario.AlmacenRepository;

@Service	
public class GeneralService {
	
	
	//Repositorys
	@Autowired
	private AlmacenRepository almacenRepository;
	
	//Otros
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	
	public Set<Almacen> obtieneAlmacenesParaArticulo(Set<Long> almacenesIds){
	
		if( CollectionUtils.isNotEmpty(almacenesIds) ) {
			
			logger.error("Error, no se han enviado los ids del almacen/es");
			
			return null;
		}
		
		logger.trace("Entramos en el metodo () con almacenesIds={}", almacenesIds.toArray().toString());
		
		try {
			
			Set<Almacen> almacenes = almacenRepository.findByIdIn(almacenesIds);
			
			return almacenes;
			
		}catch(Exception e) {
			
			logger.error("Error, no se ha podido encontrar los almacenes requeridos");
			
			e.printStackTrace();
			
			return null;
		}
	}
	
	public void compruebaSiHayCantidadSuficienteDelArticuloParaContratoOVenta() {}
	
}
