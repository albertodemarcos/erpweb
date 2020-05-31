package com.erpweb.servicios.ventas;


import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.erpweb.dto.ContratoDto;
import com.erpweb.entidades.empresa.Empresa;
import com.erpweb.entidades.usuarios.Usuario;
import com.erpweb.entidades.ventas.Contrato;
import com.erpweb.entidades.ventas.Factura;
import com.erpweb.repositorios.empresa.EmpresaRepository;
import com.erpweb.repositorios.ventas.ContratoRepository;
import com.erpweb.repositorios.ventas.FacturaRepository;
import com.erpweb.utiles.AccionRespuesta;


@Service
public class ContratoService {

	@Autowired
	private ContratoRepository contratoRepository;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private FacturaRepository facturaRepository;
	
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	public AccionRespuesta crearContratoDesdeContratoDto(ContratoDto contratoDto) {
		
		logger.debug("Entramos en el metodo crearContratoDesdeContratoDto() con la empresa={}", contratoDto.getEmpresaId() );
		
		Contrato contrato = new Contrato();

		if(contratoDto.getEmpresaId() == null) {
			
			return new AccionRespuesta();
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
			
			logger.error("Error en el metodo crearContratoDesdeContratoDto() con la empresa{} ", contratoDto.getEmpresaId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public AccionRespuesta actualizarContratoDesdeContratoDto(ContratoDto contratoDto) {
		
		logger.debug("Entramos en el metodo actualizarContratoDesdeContratoDto() con la empresa={}", contratoDto.getEmpresaId() );
		
		Contrato contrato = new Contrato();

		if(contratoDto.getEmpresaId() == null) {
			
			return new AccionRespuesta();
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
			
			logger.error("Error en el metodo actualizarContratoDesdeContratoDto() con la empresa{} ", contratoDto.getEmpresaId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public AccionRespuesta eliminarContrato(Contrato contrato) {
		
		logger.debug("Entramos en el metodo eliminarContrato() con la empresa={}", contrato.getEmpresa().getId() );
		
		if(contrato == null || contrato.getId() == null) {
			
			return new AccionRespuesta();
		}
		
		try {
			//Elimnamos el contrato
			contratoRepository.deleteById(contrato.getId());
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo eliminarContrato() con la empresa{} ", contrato.getEmpresa().getId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public ContratoDto obtenerContratoDtoDesdeContrato(Long id, Long empresaId) {
		
		logger.debug("Entramos en el metodo obtenerContratoDtoDesdeContrato() con la empresa={}", empresaId );
		
		Contrato contrato = contratoRepository.findByIdAndEmpresaId(id, empresaId);
		
		if(contrato == null) {
			return new ContratoDto();
		}
		
		ContratoDto contratoDto = new ContratoDto();
		
		try {
			
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
			
		} catch(Exception e) {
			
			logger.error("Error en el metodo obtenerContratoDtoDesdeContrato() con la empresa{} ", empresaId );
			
			e.printStackTrace();
		}
		
		return contratoDto;
	}

	public AccionRespuesta getContrato(Long contratoId, Usuario user) {
		
		logger.debug("Entramos en el metodo getContrato()");
		
		if( contratoId == null) {
			
			return new AccionRespuesta(-1L, "Error, existe el contrato", Boolean.FALSE);
		}
		
		ContratoDto contratoDto = this.obtenerContratoDtoDesdeContrato(contratoId, user.getEmpresa().getId());
		
		AccionRespuesta AccionRespuesta = new AccionRespuesta();
		
		if( contratoDto != null ) {
			
			AccionRespuesta.setId( contratoDto.getId() );
			
			AccionRespuesta.setRespuesta("");
			
			AccionRespuesta.setResultado(Boolean.TRUE);
			
			HashMap<String, Object> mapa = new HashMap<String, Object>();
			
			mapa.put("contratoDto", contratoDto);
			
			AccionRespuesta.setData(new HashMap<String, Object>(mapa));
			
		}else {
			
			AccionRespuesta.setId( -1L );
			
			AccionRespuesta.setRespuesta("Error, no se ha podido recuperar el contrato");
			
			AccionRespuesta.setResultado(Boolean.FALSE);
		}
		
		return AccionRespuesta;
	}
	
}
