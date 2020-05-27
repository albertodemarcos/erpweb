package com.erpweb.servicios.ventas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.erpweb.dto.VentaDto;
import com.erpweb.entidades.empresa.Empresa;
import com.erpweb.entidades.ventas.Factura;
import com.erpweb.entidades.ventas.Venta;
import com.erpweb.repositorios.empresa.EmpresaRepository;
import com.erpweb.repositorios.ventas.FacturaRepository;
import com.erpweb.repositorios.ventas.VentaRepository;


@Service
public class VentaService {

	@Autowired
	private VentaRepository ventaRepository;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private FacturaRepository facturaRepository;
	
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	public void crearVentaDesdeVentaDto(VentaDto ventaDto) {
		
		Venta venta = new Venta();

		if(ventaDto.getEmpresaId() == null) {
			//return Boolean.FALSE;
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
			
			System.out.println("Error: " + e.getLocalizedMessage() );
			
			//return Boolean.FALSE;
		}
		
		//return Boolean.TRUE;
	}
	
	public void actualizarVentaDesdeVentaDto(VentaDto ventaDto) {
		
		Venta venta = new Venta();

		if(ventaDto.getEmpresaId() == null) {
			//return Boolean.FALSE;
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
			
			System.out.println("Error: " + e.getLocalizedMessage() );
			
			//return Boolean.FALSE;
		}
		
		//return Boolean.TRUE;
	}
	
	public void eliminarVenta(Venta venta) {
		
		if(venta == null || venta.getId() == null) {
			//return Boolean.FALSE;
		}
		
		try {
			//Elimnamos la venta
			ventaRepository.deleteById(venta.getId());
			
		}catch(Exception e) {
			
			System.out.println("Error: " + e.getLocalizedMessage());
			
			//return Boolean.FALSE;
		}
		
		//return Boolean.TRUE;
	}
	
	public VentaDto obtenerVentaDtoDesdeVenta(Long id, Long empresaId) {
		
		Venta venta = ventaRepository.findByIdAndEmpresaId(id, empresaId);
		
		if(venta == null) {
			return new VentaDto();
		}
		
		VentaDto ventaDto = new VentaDto();
		
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
		
		return ventaDto;
	}
	
	
}
