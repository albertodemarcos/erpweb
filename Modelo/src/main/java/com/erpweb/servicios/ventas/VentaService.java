package com.erpweb.servicios.ventas;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.erpweb.dto.VentaDto;
import com.erpweb.entidades.empresa.Empresa;
import com.erpweb.entidades.usuarios.Usuario;
import com.erpweb.entidades.ventas.Factura;
import com.erpweb.entidades.ventas.Venta;
import com.erpweb.repositorios.empresa.EmpresaRepository;
import com.erpweb.repositorios.ventas.FacturaRepository;
import com.erpweb.repositorios.ventas.VentaRepository;
import com.erpweb.utiles.AccionRespuesta;


@Service
public class VentaService {

	@Autowired
	private VentaRepository ventaRepository;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private FacturaRepository facturaRepository;
	
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	public AccionRespuesta crearVentaDesdeVentaDto(VentaDto ventaDto) {
		
		logger.debug("Entramos en el metodo crearVentaDesdeVentaDto() con la empresa={}", ventaDto.getEmpresaId() );
		
		Venta venta = new Venta();

		if(ventaDto.getEmpresaId() == null) {
			
			return new AccionRespuesta();
		}
		
		Empresa empresa = empresaRepository.findById(ventaDto.getEmpresaId()).orElse(new Empresa());
		
		venta.setCodigo(ventaDto.getCodigo());
		venta.setEmpresa(empresa);
		venta.setFechaCreacion(ventaDto.getFechaCreacion());
		venta.setFechaInicio(ventaDto.getFechaInicio());
		venta.setFechaFin(ventaDto.getFechaFin());
		venta.setDescripcion(ventaDto.getDescripcion());
		venta.setBaseImponibleTotal(ventaDto.getBaseImponibleTotal());
		venta.setImporteTotal(ventaDto.getImporteTotal());

		Factura factura = facturaRepository.findByIdAndEmpresaId(ventaDto.getFacturaId(), empresa.getId());
		venta.setFactura(factura);
		
		if(!CollectionUtils.isEmpty(ventaDto.getLineasVenta())) {
			venta.getLineasVenta().addAll(ventaDto.getLineasVenta());
		}
		
		try {
			
			//Guardamos la venta en base de datos
			ventaRepository.save(venta);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo crearVentaDesdeVentaDto() con la empresa{} ", ventaDto.getEmpresaId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public AccionRespuesta actualizarVentaDesdeVentaDto(VentaDto ventaDto) {
		
		logger.debug("Entramos en el metodo crearventaDesdeventaDto() con la empresa={}", ventaDto.getEmpresaId() );
		
		Venta venta = new Venta();

		if(ventaDto.getEmpresaId() == null) {
			
			return new AccionRespuesta();
		}
		
		Empresa empresa = empresaRepository.findById(ventaDto.getEmpresaId()).orElse(new Empresa());
		
		venta.setId(ventaDto.getId());
		venta.setCodigo(ventaDto.getCodigo());
		venta.setEmpresa(empresa);
		venta.setFechaCreacion(ventaDto.getFechaCreacion());
		venta.setFechaInicio(ventaDto.getFechaInicio());
		venta.setFechaFin(ventaDto.getFechaFin());
		venta.setDescripcion(ventaDto.getDescripcion());
		venta.setBaseImponibleTotal(ventaDto.getBaseImponibleTotal());
		venta.setImporteTotal(ventaDto.getImporteTotal());

		Factura factura = facturaRepository.findByIdAndEmpresaId(ventaDto.getFacturaId(), empresa.getId());
		venta.setFactura(factura);
		
		if(!CollectionUtils.isEmpty(ventaDto.getLineasVenta())) {
			venta.getLineasVenta().addAll(ventaDto.getLineasVenta());
		}
		
		try {
			
			//Guardamos la venta en base de datos
			ventaRepository.save(venta);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo actualizarVentaDesdeVentaDto() con la empresa{} ", ventaDto.getEmpresaId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public AccionRespuesta eliminarVenta(Venta venta) {
		
		logger.debug("Entramos en el metodo eliminarVenta() con la empresa={}", venta.getEmpresa().getId() );
		
		if(venta == null || venta.getId() == null) {
			
			return new AccionRespuesta();
		}
		
		try {
			
			//Elimnamos la venta
			ventaRepository.deleteById(venta.getId());
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo eliminarVenta() con la empresa{} ", venta.getEmpresa().getId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public AccionRespuesta eliminarVentaPorId(Long ventaId) {
		
		logger.error("Entramos en el metodo eliminarVentaPorId() con id={}", ventaId );
				
		try {
			
			//Elimnamos la venta
			ventaRepository.deleteById(ventaId);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo eliminarVentaPorId() con id={}", ventaId );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public VentaDto obtenerVentaDtoDesdeVenta(Long id, Long empresaId) {
		
		logger.debug("Entramos en el metodo obtenerVentaDtoDesdeVenta() con la empresa={}", empresaId );
		
		Venta venta = ventaRepository.findByIdAndEmpresaId(id, empresaId);
		
		if(venta == null) {
			return new VentaDto();
		}
		
		VentaDto ventaDto = new VentaDto();
		
		try {
			
			ventaDto.setCodigo(venta.getCodigo());
			ventaDto.setEmpresaId(venta.getEmpresa().getId());
			ventaDto.setFechaCreacion(venta.getFechaCreacion());
			ventaDto.setFechaInicio(venta.getFechaInicio());
			ventaDto.setFechaFin(venta.getFechaFin());
			ventaDto.setDescripcion(venta.getDescripcion());
			ventaDto.setBaseImponibleTotal(venta.getBaseImponibleTotal());
			ventaDto.setImporteTotal(venta.getImporteTotal());
			ventaDto.setFacturaId(venta.getFactura().getId());
			
			if(!CollectionUtils.isEmpty(venta.getLineasVenta())) {
				ventaDto.getLineasVenta().addAll(venta.getLineasVenta());
			}
			
		} catch(Exception e) {
			
			logger.error("Error en el metodo obtenerVentaDtoDesdeVenta() con la empresa{} ", empresaId );
			
			e.printStackTrace();
		}
		
		return ventaDto;
	}
	
	public AccionRespuesta getVenta(Long ventaId, Usuario user) {
		
		logger.debug("Entramos en el metodo getCrearventa()");
		
		if( ventaId == null) {
			
			return new AccionRespuesta(-1L, "Error, no existe la venta", Boolean.FALSE);
		}
		
		VentaDto ventaDto = this.obtenerVentaDtoDesdeVenta(ventaId, user.getEmpresa().getId());
		
		AccionRespuesta AccionRespuesta = new AccionRespuesta();
		
		if( ventaDto != null ) {
			
			AccionRespuesta.setId( ventaDto.getId() );
			
			AccionRespuesta.setRespuesta("");
			
			AccionRespuesta.setResultado(Boolean.TRUE);
			
			HashMap<String, Object> mapa = new HashMap<String, Object>();
			
			mapa.put("ventaDto", ventaDto);
			
			AccionRespuesta.setData(new HashMap<String, Object>(mapa));
			
		}else {
			
			AccionRespuesta.setId( -1L );
			
			AccionRespuesta.setRespuesta("Error, no se ha podido recuperar la venta");
			
			AccionRespuesta.setResultado(Boolean.FALSE);
		}
		
		return AccionRespuesta;
	}
	
}
