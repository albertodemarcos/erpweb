package com.erpweb.servicios.ventas;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.erpweb.dto.FacturaDto;
import com.erpweb.entidades.empresa.Empresa;
import com.erpweb.entidades.ventas.Factura;
import com.erpweb.repositorios.empresa.EmpresaRepository;
import com.erpweb.repositorios.ventas.FacturaRepository;
import com.erpweb.servicios.ventas.interfaces.FacturaServiceInterfaz;



@Service
public class FacturaService implements FacturaServiceInterfaz {

	@Autowired
	private FacturaRepository facturaRepository;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	
	@Override
	public Boolean creaFacturaDesdeFacturaDto(FacturaDto facturaDto) {
		
		Factura factura = new Factura();

		if(facturaDto.getEmpresaId() == null) {
			return Boolean.FALSE;
		}
		
		Empresa empresa = empresaRepository.findById(facturaDto.getEmpresaId()).orElse(new Empresa());
		
		factura.setCodigo(facturaDto.getCodigo());
		factura.setEmpresa(empresa);
		factura.setFechaCreacion(facturaDto.getFechaCreacion());
		factura.setFechaInicio(facturaDto.getFechaInicio());
		factura.setFechaFin(facturaDto.getFechaFin());
		factura.setDescripcion(facturaDto.getDescripcion());
		factura.setBaseImponible(facturaDto.getBaseImponible());
		factura.setCuotaTributaria(facturaDto.getCuotaTributaria());
		factura.setImporteTotal(facturaDto.getImporteTotal());
		
		if(!CollectionUtils.isEmpty(facturaDto.getLineasFactura())) {
			factura.getLineasFactura().addAll(facturaDto.getLineasFactura());
		}
		
		try {
			//Guardamos la factura en base de datos
			facturaRepository.save(factura);
			
		}catch(Exception e) {
			
			System.out.println("Error: " + e.getLocalizedMessage() );
			
			return Boolean.FALSE;
		}
		
		return Boolean.TRUE;
	}

	@Override
	public FacturaDto obtieneFacturaDto(Long id, Long empresaId) {

		Factura factura = facturaRepository.findByIdAndEmpresaId(id, empresaId);
		
		if(factura == null) {
			return new FacturaDto();
		}
		
		FacturaDto facturaDto = new FacturaDto();
		
		facturaDto.setId(factura.getId());
		facturaDto.setCodigo(factura.getCodigo());
		facturaDto.setEmpresaId(factura.getEmpresa().getId());
		facturaDto.setFechaCreacion(factura.getFechaCreacion());
		facturaDto.setFechaInicio(factura.getFechaInicio());
		facturaDto.setFechaFin(factura.getFechaFin());
		facturaDto.setDescripcion(factura.getDescripcion());
		facturaDto.setBaseImponible(factura.getBaseImponible());
		facturaDto.setCuotaTributaria(factura.getCuotaTributaria());
		facturaDto.setImporteTotal(factura.getImporteTotal());
		
		if(!CollectionUtils.isEmpty(factura.getLineasFactura())) {
			facturaDto.getLineasFactura().addAll(factura.getLineasFactura());
		}
		
		return facturaDto;
	}

	@Override
	public Boolean actualizaFactura(FacturaDto facturaDto) {
		
		Factura factura = new Factura();

		if(facturaDto.getEmpresaId() == null) {
			return Boolean.FALSE;
		}
		
		Empresa empresa = empresaRepository.findById(facturaDto.getEmpresaId()).orElse(new Empresa());
		
		factura.setId(facturaDto.getId());
		factura.setCodigo(facturaDto.getCodigo());
		factura.setEmpresa(empresa);
		factura.setFechaCreacion(facturaDto.getFechaCreacion());
		factura.setFechaInicio(facturaDto.getFechaInicio());
		factura.setFechaFin(facturaDto.getFechaFin());
		factura.setDescripcion(facturaDto.getDescripcion());
		factura.setBaseImponible(facturaDto.getBaseImponible());
		factura.setCuotaTributaria(facturaDto.getCuotaTributaria());
		factura.setImporteTotal(facturaDto.getImporteTotal());
		
		if(!CollectionUtils.isEmpty(facturaDto.getLineasFactura())) {
			factura.getLineasFactura().addAll(facturaDto.getLineasFactura());
		}
		
		try {
			//Guardamos la factura en base de datos
			facturaRepository.save(factura);
			
		}catch(Exception e) {
			
			System.out.println("Error: " + e.getLocalizedMessage() );
			
			return Boolean.FALSE;
		}
		
		return Boolean.TRUE;
	}

	@Override
	public Boolean eliminaFactura(Factura factura) {
		
		if(factura == null || factura.getId() == null) {
			return Boolean.FALSE;
		}
		
		try {
			//Elimnamos la factura
			facturaRepository.deleteById(factura.getId());
			
		}catch(Exception e) {
			
			System.out.println("Error: " + e.getLocalizedMessage());
			
			return Boolean.FALSE;
		}
		
		return Boolean.TRUE;
	}

	@Override
	public Factura obtieneFactura(Long id, Long empresaId) {

		Factura factura;
		
		try {
			//Recuperamos el gasto
			factura = facturaRepository.findByIdAndEmpresaId(id, empresaId);
			
		}catch(Exception e) {
			
			System.out.println("Error: " + e.getLocalizedMessage());
			
			return new Factura();
		}
		
		return factura;
	}


}
