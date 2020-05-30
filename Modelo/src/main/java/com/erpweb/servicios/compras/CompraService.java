package com.erpweb.servicios.compras;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpweb.dto.CompraDto;
import com.erpweb.entidades.compras.Compra;
import com.erpweb.entidades.empresa.Empresa;
import com.erpweb.repositorios.compras.CompraRepository;
import com.erpweb.repositorios.empresa.EmpresaRepository;
import com.erpweb.utiles.AccionRespuesta;



@Service
public class CompraService {

	@Autowired
	private CompraRepository compraRepository;
	@Autowired
	private EmpresaRepository empresaRepository;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public AccionRespuesta crearCompraDesdeCompraDto(CompraDto compraDto) {
		
		logger.debug("Entramos en el metodo crearCompraDesdeCompraDto() con la empresa={}", compraDto.getEmpresaId() );
		
		Compra compra = new Compra();
		
		if(compraDto.getEmpresaId() == null) {
			
			return new AccionRespuesta();
		}
		
		Empresa empresa = empresaRepository.findById(compraDto.getEmpresaId()).orElse(new Empresa());
		
		compra.setCodigo(compraDto.getCodigo());
		compra.setEmpresa(empresa);
		compra.setFechaCompra(compraDto.getFechaCompra());
		compra.setLineaCompra(compraDto.getLineaCompra());
		compra.setProveedor(compraDto.getProveedor());
		
		try {
			
			//Guardamos la compra en base de datos
			compraRepository.save(compra);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo crearCompraDesdeCompraDto() con la empresa{} ", compraDto.getEmpresaId() );
			
			e.printStackTrace();
						
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public AccionRespuesta actualizarCompraDesdeCompraDto(CompraDto compraDto) {
		
		logger.debug("Entramos en el metodo actualizarCompraDesdeCompraDto() con la empresa={}", compraDto.getEmpresaId() );
		
		Compra compra = new Compra();
		
		if(compraDto.getEmpresaId() == null) {
			
			return new AccionRespuesta();
		}
		
		Empresa empresa = empresaRepository.findById(compraDto.getEmpresaId()).orElse(new Empresa());
		
		compra.setId(compraDto.getId());
		compra.setCodigo(compraDto.getCodigo());
		compra.setEmpresa(empresa);
		compra.setFechaCompra(compraDto.getFechaCompra());
		compra.setLineaCompra(compraDto.getLineaCompra());
		compra.setProveedor(compraDto.getProveedor());
		
		try {
			
			//Actualizamos la compra en base de datos
			compraRepository.save(compra);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo actualizarCompraDesdeCompraDto() con la empresa{} ", compraDto.getEmpresaId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
				
		return new AccionRespuesta();
	}
	
	public AccionRespuesta eliminarCompra(Compra compra) {
		
		logger.debug("Entramos en el metodo eliminarCompra() con la empresa={}", compra.getId() );
		
		if(compra == null || compra.getId() == null) {
			
			return new AccionRespuesta();
		}
		
		try {
			
			//Elimnamos la compra
			compraRepository.deleteById(compra.getId());
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo eliminarCompra() con la empresa{} ", compra.getId() );
			
			e.printStackTrace();
						
			return new AccionRespuesta();
		}
				
		return new AccionRespuesta();
	}
		
	public CompraDto obtenerCompraDtoDesdeCompra(Long id, Long empresaId) {
		
		logger.debug("Entramos en el metodo obtenerCompraDtoDesdeCompra() con la empresa={}", empresaId );
		
		Compra compra = compraRepository.findByIdAndEmpresaId(id, empresaId);
		
		if(compra == null) {
			
			return new CompraDto();
		}
		
		CompraDto compraDto = new CompraDto();
		
		compraDto.setCodigo(compra.getCodigo());
		compraDto.setEmpresaId(empresaId);
		compraDto.setFechaCompra(compra.getFechaCompra());
		compraDto.setLineaCompra(compra.getLineaCompra());
		compraDto.setProveedor(compra.getProveedor());
		
		logger.error("Error en el metodo obtenerCompraDtoDesdeCompra() con la empresa{} ", empresaId );
		
		return compraDto;
	}

}
