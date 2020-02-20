package com.erpweb.servicios.compras;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpweb.dto.CompraDto;
import com.erpweb.entidades.compras.Compra;
import com.erpweb.entidades.empresa.Empresa;
import com.erpweb.repositorios.compras.CompraRepository;
import com.erpweb.repositorios.empresa.EmpresaRepository;
import com.erpweb.servicios.compras.interfaces.CompraServiceInterfaz;



@Service
public class CompraService implements CompraServiceInterfaz{

	@Autowired
	private CompraRepository compraRepository;
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Override
	public Boolean creaCompraDesdeCompraDto(CompraDto compraDto) {
		
		Compra compra = new Compra();
		
		if(compraDto.getEmpresaId() == null) {
			return Boolean.FALSE;
		}
		
		Empresa empresa = empresaRepository.findOne(compraDto.getEmpresaId());
		
		compra.setCodigo(compraDto.getCodigo());
		compra.setEmpresa(empresa);
		compra.setFechaCompra(compraDto.getFechaCompra());
		compra.setLineaCompra(compraDto.getLineaCompra());
		compra.setProveedor(compraDto.getProveedor());
		
		try {
			//Guardamos la compra en base de datos
			compraRepository.save(compra);
			
		}catch(Exception e) {
			
			System.out.println("Error: " + e.getLocalizedMessage() );
			
			return Boolean.FALSE;
		}
		
		return Boolean.TRUE;
	}

	@Override
	public CompraDto obtieneCompraDto(Long id, Long empresaId) {
		
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
		
		return compraDto;
	}

	@Override
	public Boolean actualizaCompra(CompraDto compraDto) {

		Compra compra = new Compra();
		
		if(compraDto.getEmpresaId() == null) {
			return Boolean.FALSE;
		}
		
		Empresa empresa = empresaRepository.findOne(compraDto.getEmpresaId());
		
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
			
			System.out.println("Error: " + e.getLocalizedMessage() );
			
			return Boolean.FALSE;
		}
		
		return Boolean.TRUE;
	}

	@Override
	public Boolean eliminaCompra(Compra compra) {
		
		if(compra == null || compra.getId() == null) {
			return Boolean.FALSE;
		}
		
		try {
			//Elimnamos la compra
			compraRepository.deleteById(compra.getId());
			
		}catch(Exception e) {
			
			System.out.println("Error: " + e.getLocalizedMessage());
			
			return Boolean.FALSE;
		}
		
		return Boolean.TRUE;
	}

	@Override
	public Compra obtieneCompra(Long id, Long empresaId) {
		
		Compra compra;
		
		try {
			//Recuperamos la compra
			compra = compraRepository.findByIdAndEmpresaId(id, empresaId);
			
		}catch(Exception e) {
			
			System.out.println("Error: " + e.getLocalizedMessage());
			
			return new Compra();
		}
		
		return compra;
	}

}
