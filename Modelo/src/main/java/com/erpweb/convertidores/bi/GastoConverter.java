package com.erpweb.convertidores.bi;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.Assert;

import com.erpweb.entidades.bi.Gasto;
import com.erpweb.repositorios.bi.GastoRepository;

public class GastoConverter implements Converter<String, Gasto> {

	@Autowired private GastoRepository gastoRepository;
	
	
	@Override
	public Gasto convert(String source) {
		// TODO Auto-generated method stub
		
		Long id=null;
		
		try {
			id = Long.parseLong(source.replaceAll("\\D+", ""));
		} catch (Exception e) {
			throw new IllegalArgumentException("Se pidió una TareaCliente con un ID=("+source+") que no es numérico.",e);
		}
		
		//AppUser appUser = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
//		if (appUser==null) {
//		    throw new IllegalArgumentException("No se ha podido recuperar el AppUser");
//		}
		Gasto entidad = gastoRepository.findByIdAndEmpresaId(id, /*appUser.getEmpresaId()*/ 1L );
		
		Assert.notNull(entidad,"No se encuentra ningún usuario con ese ID");
		
		return entidad;
	}


	public void setGastoRepository(GastoRepository gastoRepository) {
		this.gastoRepository = gastoRepository;
	}

	
	
	
}
