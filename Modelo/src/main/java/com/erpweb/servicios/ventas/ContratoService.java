package com.erpweb.servicios.ventas;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.erpweb.dto.ContratoDto;
import com.erpweb.entidades.empresa.Empresa;
import com.erpweb.entidades.ventas.Contrato;
import com.erpweb.entidades.ventas.Factura;
import com.erpweb.repositorios.empresa.EmpresaRepository;
import com.erpweb.repositorios.ventas.ContratoRepository;
import com.erpweb.repositorios.ventas.FacturaRepository;


@Service
public class ContratoService {

	@Autowired
	private ContratoRepository contratoRepository;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private FacturaRepository facturaRepository;
	
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	public void crearContratoDesdeContratoDto(ContratoDto contratoDto) {
		
		Contrato contrato = new Contrato();

		if(contratoDto.getEmpresaId() == null) {
			//return Boolean.FALSE;
		}
		
		Empresa empresa = empresaRepository.findById(contratoDto.getEmpresaId()).orElse(new Empresa());
		
		contrato.setCodigo(contratoDto.getCodigo());
		contrato.setEmpresa(empresa);
		contrato.setFechaCreacion(contratoDto.getFechaCreacion());
		contrato.setFechaInicio(contratoDto.getFechaInicio());
		contrato.setFechaFin(contratoDto.getFechaFin());
		contrato.setDescripcion(contratoDto.getDescripcion());
		contrato.setBaseImponibleTotal(contratoDto.getBaseImponibleTotal());
		contrato.setImporteTotal(contratoDto.getImporteTotal());

		Factura factura = facturaRepository.findByIdAndEmpresaId(contratoDto.getFacturaId(), empresa.getId());
		contrato.setFactura(factura);
		
		if(!CollectionUtils.isEmpty(contratoDto.getLineasContrato())) {
			
			contrato.getLineasContrato().addAll(contratoDto.getLineasContrato());
		}
		
		try {
			//Guardamos el contrato en base de datos
			contratoRepository.save(contrato);
			
		}catch(Exception e) {
			
			System.out.println("Error: " + e.getLocalizedMessage() );
			
			//return Boolean.FALSE;
		}
		
		//return Boolean.TRUE;
	}
	
	public void actualizarContratoDesdeContratoDto(ContratoDto contratoDto) {
		
		Contrato contrato = new Contrato();

		if(contratoDto.getEmpresaId() == null) {
			//return Boolean.FALSE;
		}
		
		Empresa empresa = empresaRepository.findById(contratoDto.getEmpresaId()).orElse(new Empresa());
		
		contrato.setId(contratoDto.getId());
		contrato.setCodigo(contratoDto.getCodigo());
		contrato.setEmpresa(empresa);
		contrato.setFechaCreacion(contratoDto.getFechaCreacion());
		contrato.setFechaInicio(contratoDto.getFechaInicio());
		contrato.setFechaFin(contratoDto.getFechaFin());
		contrato.setDescripcion(contratoDto.getDescripcion());
		contrato.setBaseImponibleTotal(contratoDto.getBaseImponibleTotal());
		contrato.setImporteTotal(contratoDto.getImporteTotal());

		Factura factura = facturaRepository.findByIdAndEmpresaId(contratoDto.getFacturaId(), empresa.getId());
		contrato.setFactura(factura);
		
		if(!CollectionUtils.isEmpty(contratoDto.getLineasContrato())) {
			contrato.getLineasContrato().addAll(contratoDto.getLineasContrato());
		}
		
		try {
			//Guardamos el contrato en base de datos
			contratoRepository.save(contrato);
			
		}catch(Exception e) {
			
			System.out.println("Error: " + e.getLocalizedMessage() );
			
			//return Boolean.FALSE;
		}
		
		//return Boolean.TRUE;
	}
	
	public void eliminarContrato(Contrato contrato) {
		
		if(contrato == null || contrato.getId() == null) {
			//return Boolean.FALSE;
		}
		
		try {
			//Elimnamos el contrato
			contratoRepository.deleteById(contrato.getId());
			
		}catch(Exception e) {
			
			System.out.println("Error: " + e.getLocalizedMessage());
			
			//return Boolean.FALSE;
		}
		
		//return Boolean.TRUE;
	}
	
	public ContratoDto obtenerContratoDtoDesdeContrato(Long id, Long empresaId) {
		
		Contrato contrato = contratoRepository.findByIdAndEmpresaId(id, empresaId);
		
		if(contrato == null) {
			return new ContratoDto();
		}
		
		ContratoDto contratoDto = new ContratoDto();
		
		contratoDto.setCodigo(contrato.getCodigo());
		contratoDto.setEmpresaId(contrato.getEmpresa().getId());
		contratoDto.setFechaCreacion(contrato.getFechaCreacion());
		contratoDto.setFechaInicio(contrato.getFechaInicio());
		contratoDto.setFechaFin(contrato.getFechaFin());
		contratoDto.setDescripcion(contrato.getDescripcion());
		contratoDto.setBaseImponibleTotal(contrato.getBaseImponibleTotal());
		contratoDto.setImporteTotal(contrato.getImporteTotal());
		contratoDto.setFacturaId(contrato.getFactura().getId());
		
		if(!CollectionUtils.isEmpty(contrato.getLineasContrato())) {
			contratoDto.getLineasContrato().addAll(contrato.getLineasContrato());
		}
		
		return contratoDto;
	}

}
