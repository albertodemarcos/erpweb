package com.erpweb.servicios.bi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpweb.dto.GastoDto;
import com.erpweb.entidades.bi.Gasto;
import com.erpweb.entidades.empresa.Empresa;
import com.erpweb.repositorios.bi.GastoRepository;
import com.erpweb.repositorios.empresa.EmpresaRepository;

@Service
public class GastoService {
	
	@Autowired
	private GastoRepository gastoRepository;
	@Autowired
	private EmpresaRepository empresaRepository;

	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	public void crearGastoDesdeGastoDto(GastoDto gastoDto) {
		
		logger.debug("Entramos en el metodo crearGastoDesdeGastoDto() con la empresa={}", gastoDto.getEmpresaId() );
		
		Gasto gasto = new Gasto();

		if(gastoDto.getEmpresaId() == null) {
			//return Boolean.FALSE;
			return;
		}
		
		Empresa empresa = empresaRepository.findById(gastoDto.getEmpresaId()).orElse(new Empresa());
		
		gasto.setCodigo(gastoDto.getCodigo());
		gasto.setEmpresa(empresa);
		gasto.setProcedencia(gastoDto.getProcedencia());
		gasto.setBaseImponible(gastoDto.getBaseImponible());
		gasto.setCuotaTributaria(gastoDto.getCuotaTributaria());
		gasto.setImporteTotal(gastoDto.getImporteTotal());
		gasto.setDescripcion(gastoDto.getDescripcion());
		gasto.setObservaciones(gastoDto.getObservaciones());
		
		try {
			//Guardamos el gasto en base de datos
			gastoRepository.save(gasto);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo crearGastoDesdeGastoDto() con la empresa{} ", gastoDto.getEmpresaId() );
			
			e.printStackTrace();
			
			//return Boolean.FALSE;
			return;
		}
		
		//return Boolean.TRUE;
		return;
	}
	
	public void actualizarGastoDesdeGastoDto(GastoDto gastoDto) {
		
		Gasto gasto = new Gasto();

		if(gastoDto.getEmpresaId() == null) {
			//return Boolean.FALSE;
			return;
		}
		
		Empresa empresa = empresaRepository.findById(gastoDto.getEmpresaId()).orElse(new Empresa());
		
		gasto.setId(gastoDto.getId());
		gasto.setCodigo(gastoDto.getCodigo());
		gasto.setEmpresa(empresa);
		gasto.setProcedencia(gastoDto.getProcedencia());
		gasto.setBaseImponible(gastoDto.getBaseImponible());
		gasto.setCuotaTributaria(gastoDto.getCuotaTributaria());
		gasto.setImporteTotal(gastoDto.getImporteTotal());
		gasto.setDescripcion(gastoDto.getDescripcion());
		gasto.setObservaciones(gastoDto.getObservaciones());
		
		try {
			//Actualizamos el gasto en base de datos
			gastoRepository.save(gasto);
			
		}catch(Exception e) {
			
			System.out.println("Error: " + e.getLocalizedMessage() );
			
			//return Boolean.FALSE;
			return;
		}
		
		//return Boolean.TRUE;
		return;
	}
	
	public void eliminarGasto(Gasto gasto) {
		
		if(gasto == null || gasto.getId() == null) {
			//return Boolean.FALSE;
			return;
		}
		
		try {
			//Elimnamos el gasto
			gastoRepository.deleteById(gasto.getId());
			
		}catch(Exception e) {
			
			System.out.println("Error: " + e.getLocalizedMessage());
			
			//return Boolean.FALSE;
			return;
		}
		
		//return Boolean.TRUE;
		return;
	}
	
	public GastoDto obtenerGastoDtoDesdeGasto(Long id, Long empresaId) {
		
		if( (id ==  null || id.doubleValue() < 1 ) || (empresaId == null || empresaId.intValue() < 1 ) ) {
			
			return new GastoDto();
		}
		
		GastoDto gastoDto = new GastoDto();
		
		try {
			
			Gasto gasto = gastoRepository.findByIdAndEmpresaId(id, empresaId);
			
			gastoDto.setCodigo(gasto.getCodigo());
			gastoDto.setEmpresaId(gasto.getEmpresa().getId());
			gastoDto.setProcedencia(gasto.getProcedencia());
			gastoDto.setBaseImponible(gasto.getBaseImponible());
			gastoDto.setCuotaTributaria(gasto.getCuotaTributaria());
			gastoDto.setImporteTotal(gasto.getImporteTotal());
			gastoDto.setDescripcion(gasto.getDescripcion());
			gastoDto.setObservaciones(gasto.getObservaciones());
			
		} catch(Exception e) {
			
			System.out.println("Error: " + e.getLocalizedMessage());
			
		}
		
		return gastoDto;
	}
	
	
}
