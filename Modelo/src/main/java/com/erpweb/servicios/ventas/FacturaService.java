package com.erpweb.servicios.ventas;


import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.erpweb.dto.FacturaDto;
import com.erpweb.entidades.empresa.Empresa;
import com.erpweb.entidades.usuarios.Usuario;
import com.erpweb.entidades.ventas.Factura;
import com.erpweb.repositorios.empresa.EmpresaRepository;
import com.erpweb.repositorios.ventas.FacturaRepository;
import com.erpweb.utiles.AccionRespuesta;


@Service
public class FacturaService {

	@Autowired
	private FacturaRepository facturaRepository;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	public AccionRespuesta crearFacturaDesdeFacturaDto(FacturaDto facturaDto) {
		
		logger.debug("Entramos en el metodo crearFacturaDesdeFacturaDto() con la empresa={}", facturaDto.getEmpresaId() );
		
		Factura factura = new Factura();

		if(facturaDto.getEmpresaId() == null) {
			
			return new AccionRespuesta();
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
			
			logger.error("Error en el metodo crearFacturaDesdeFacturaDto() con la empresa{} ", facturaDto.getEmpresaId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public AccionRespuesta actualizarFacturaDesdeFacturaDto(FacturaDto facturaDto) {
		
		logger.debug("Entramos en el metodo actualizarFacturaDesdeFacturaDto() con la empresa={}", facturaDto.getEmpresaId() );
		
		Factura factura = new Factura();

		if(facturaDto.getEmpresaId() == null) {
			
			return new AccionRespuesta();
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
			
			logger.error("Error en el metodo actualizarFacturaDesdeFacturaDto() con la empresa{} ", facturaDto.getEmpresaId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public AccionRespuesta eliminarFactura(Factura factura) {
		
		logger.debug("Entramos en el metodo eliminarFactura() con la empresa={}", factura.getEmpresa().getId() );
		
		if(factura == null || factura.getId() == null) {
			
			return new AccionRespuesta();
		}
		
		try {
			
			//Elimnamos la factura
			facturaRepository.deleteById(factura.getId());
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo eliminarFactura() con la empresa{} ", factura.getEmpresa().getId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public AccionRespuesta eliminarFacturaPorId(Long facturaId) {
		
		logger.error("Entramos en el metodo eliminarFacturaPorId() con id={}", facturaId );
				
		try {
			
			//Elimnamos la factura
			facturaRepository.deleteById(facturaId);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo eliminarFacturaPorId() con id={}", facturaId );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		
		return new AccionRespuesta();
	}
	
	public FacturaDto obtenerFacturaDtoDesdeFactura(Long id, Long empresaId) {
		
		logger.debug("Entramos en el metodo obtenerFacturaDtoDesdeFactura() con la empresa={}", empresaId );

		Factura factura = facturaRepository.findByIdAndEmpresaId(id, empresaId);
		
		if(factura == null) {
			return new FacturaDto();
		}
		
		FacturaDto facturaDto = new FacturaDto();
		
		try {
			
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
			
		} catch(Exception e) {
			
			logger.error("Error en el metodo obtenerFacturaDtoDesdeFactura() con la empresa{} ", empresaId );
			
			e.printStackTrace();
		}
		
		return facturaDto;
	}

	public AccionRespuesta getFactura(Long facturaId, Usuario user) {
		
		logger.debug("Entramos en el metodo getFactura()");
		
		if( facturaId == null) {
			
			return new AccionRespuesta(-1L, "Error, existe la factura", Boolean.FALSE);
		}
		
		FacturaDto facturaDto = this.obtenerFacturaDtoDesdeFactura(facturaId, user.getEmpresa().getId());
		
		AccionRespuesta AccionRespuesta = new AccionRespuesta();
		
		if( facturaDto != null ) {
			
			AccionRespuesta.setId( facturaDto.getId() );
			
			AccionRespuesta.setRespuesta("");
			
			AccionRespuesta.setResultado(Boolean.TRUE);
			
			HashMap<String, Object> mapa = new HashMap<String, Object>();
			
			mapa.put("facturaDto", facturaDto);
			
			AccionRespuesta.setData(new HashMap<String, Object>(mapa));
			
		}else {
			
			AccionRespuesta.setId( -1L );
			
			AccionRespuesta.setRespuesta("Error, no se ha podido recuperar la factura");
			
			AccionRespuesta.setResultado(Boolean.FALSE);
		}
		
		return AccionRespuesta;
	}
	
}
