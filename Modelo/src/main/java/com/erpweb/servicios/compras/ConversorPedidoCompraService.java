package com.erpweb.servicios.compras;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpweb.dto.CompraDto;
import com.erpweb.dto.PedidoDto;
import com.erpweb.entidades.compras.Compra;
import com.erpweb.patrones.builder.constructores.ConstructorCompra;
import com.erpweb.utiles.AccionRespuesta;

@Service
public class ConversorPedidoCompraService {

	//Servicios
	@Autowired
	private PedidoService pedidoService;
	
	//Otros
	@Autowired
	private ConstructorCompra constructorCompra;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	public AccionRespuesta convetirPedidoEnCompra(Long pedidoId) {
		
		logger.debug("Entramos en el metodo convetirPedidoEnCompra() con ID={}", pedidoId );
		
		if( pedidoId == null || pedidoId.longValue() <= 0) {
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}

		try {
			
			//1º Recuperar el pedido
			PedidoDto pedidoDto = this.pedidoService.obtenerPedidoDtoDesdePedido(pedidoId);
			
			//2º Crear el dto de compra a partir del pedidoDto
			CompraDto compraDto = this.convertirPedidoDtoEnCompraDto(pedidoDto);
			
			//3º Crear la compra
			Compra compraSave = constructorCompra.crearEntidadLineasEntidad(compraDto);
			
			//4º Eliminar el pedido
			this.pedidoService.eliminarPedidoPorId(pedidoId);
			
			//5º Devolvemos el dto de Compra
			return this.getCompraDto(compraDto, compraSave);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo convetirPedidoEnCompra() con ID={}", pedidoId );
			
			e.printStackTrace();
						
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
	}
	
	private CompraDto convertirPedidoDtoEnCompraDto(PedidoDto pedidoDto) {
		
		CompraDto compraDto = new CompraDto();
		
		//1º Objetos simples
		
		compraDto.setArticulo(pedidoDto.getArticulo());
		compraDto.setBaseImponibleTotal(pedidoDto.getBaseImponibleTotal());
		compraDto.setCantidad(pedidoDto.getCantidad());
		compraDto.setCodigo(pedidoDto.getCodigo());
		compraDto.setFechaCompra(pedidoDto.getFechaPedido());
		compraDto.setImporteTotal(pedidoDto.getImporteTotal());
		compraDto.setImpuesto(pedidoDto.getImpuesto());		
		
		try {
			
			//2º Colecciones
			compraDto.getArticulosCantidad().putAll(pedidoDto.getArticulosCantidad());
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo convertirPedidoDtoEnCompraDto() con ID={} cuando se clonaba la coleccion", pedidoDto.getId() );
			
			e.printStackTrace();
		}
		
		return compraDto;
	}
	
	public AccionRespuesta getCompraDto(CompraDto compraDto, Compra compraSave) {
		
		AccionRespuesta respuesta = new AccionRespuesta();
		
		//Guardado el cliente se devuelve en su dto
		if(compraSave != null && compraSave.getId() != null) {
			
			compraDto.setId(compraSave.getId());
			
			respuesta.setId(compraSave.getId());
			
			respuesta.setCodigo("OK");
						
			respuesta.setResultado(Boolean.TRUE);
			
			HashMap<String, Object> data= new HashMap<String, Object> ();
			
			data.put("compraDto", compraDto);
			
			respuesta.setData(data);
			
		}else {
			
			respuesta.setCodigo("NOK");
						
			respuesta.setResultado(Boolean.FALSE);
			
			HashMap<String, Object> data= new HashMap<String, Object> ();
			
			data.put("compraDto", compraDto);
			
			respuesta.setData(data);
		}
		
		return respuesta;
	}
	
	
	
}
