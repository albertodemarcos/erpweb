package com.erpweb.servicios.ventas;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erpweb.entidades.compras.Compra;
import com.erpweb.entidades.compras.LineaCompra;
import com.erpweb.entidades.compras.LineaPedido;
import com.erpweb.entidades.compras.Pedido;
import com.erpweb.entidades.ventas.Contrato;
import com.erpweb.entidades.ventas.Factura;
import com.erpweb.entidades.ventas.LineaContrato;
import com.erpweb.entidades.ventas.LineaFactura;
import com.erpweb.entidades.ventas.LineaVenta;
import com.erpweb.entidades.ventas.Venta;
import com.erpweb.repositorios.ventas.FacturaRepository;
import com.erpweb.repositorios.ventas.LineaFacturaRepository;

@Service
public class RegeneraFacturasService {

	@Autowired
	private FacturaRepository facturaRepository;
	@Autowired
	private LineaFacturaRepository lineaFacturaRepository;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	/*
	 * Metodos Especificos Entitys
	 */
	
	/**
	 * Regenera factura de Compra
	 * @param compra
	 */
	@Transactional
	public void actualizarFacturaCompra(Compra compra) {
		
		logger.trace("Entramos en el metodo actualizarFacturaCompra()");
		
		Factura facturaCompra = compra.getFactura();
		
		if( facturaCompra == null) {
			
			logger.error("Error, no existe la factura para poder actualizarla");
			
			return;
		}
		
		logger.trace("Primero borramos las lineas de la factura");
		
		this.eliminarLineasFactura(facturaCompra);
		
		facturaCompra.getLineasFactura().clear();
		
		logger.trace("Segundo persistimos la factura sin lineas");
		
		this.actualizarFactura(facturaCompra);
		
		logger.trace("Tercero creamos y persistimos las nuevas lineas de la factura");
		
		Set<LineaFactura> lineasFacturaCompra = this.crearLineasFacturaDeLineasCompra(compra, facturaCompra);
		
		facturaCompra.getLineasFactura().addAll(lineasFacturaCompra);
		
		this.crearNuevasLineasFactura(facturaCompra);
		
		logger.trace("Por ultimo volvemos a persistir la factura");
		
		this.actualizarFactura(facturaCompra);
	}
	
	private Set<LineaFactura> crearLineasFacturaDeLineasCompra(Compra compra, Factura facturaCompra) {
		
		Set<LineaFactura> lineasFactura = facturaCompra.getLineasFactura();
		
		for(LineaCompra lineaCompra : compra.getLineasCompra() ) {
			
			LineaFactura lineaFactura = new LineaFactura();
			
			lineaFactura.setFactura(facturaCompra);
			
			lineaFactura.setArticulo(lineaCompra.getArticulo());
			lineaFactura.setCantidad(lineaCompra.getCantidad());
			lineaFactura.setBaseImponible(lineaCompra.getBaseImponible());
			lineaFactura.setImporteImpuesto(lineaCompra.getImporteImpuesto());
			lineaFactura.setImporteTotal(lineaCompra.getImporteTotal());
			lineaFactura.setDescripcionLinea(lineaCompra.getDescripcionLinea());
			
			lineasFactura.add(lineaFactura);
		}
		
		return lineasFactura;
	}
	
	/**
	 * Regenera factura de Pedido
	 * @param pedido
	 */
	@Transactional
	public void actualizarFacturaPedido(Pedido pedido) {
		
		logger.trace("Entramos en el metodo actualizarFacturaCompra()");
		
		Factura facturaPedido = pedido.getFactura();
		
		if( facturaPedido == null) {
			
			logger.error("Error, no existe la factura para poder actualizarla");
			
			return;
		}
		
		logger.trace("Primero borramos las lineas de la factura");
		
		this.eliminarLineasFactura(facturaPedido);
		
		facturaPedido.getLineasFactura().clear();
		
		logger.trace("Segundo persistimos la factura sin lineas");
		
		this.actualizarFactura(facturaPedido);
		
		logger.trace("Tercero creamos y persistimos las nuevas lineas de la factura");
		
		Set<LineaFactura> lineasFacturaPedido = this.crearLineasFacturaDeLineasPedido(pedido, facturaPedido);
		
		facturaPedido.getLineasFactura().addAll(lineasFacturaPedido);
		
		this.crearNuevasLineasFactura(facturaPedido);
		
		logger.trace("Por ultimo volvemos a persistir la factura");
		
		this.actualizarFactura(facturaPedido);
	}
	
	private Set<LineaFactura> crearLineasFacturaDeLineasPedido(Pedido pedido, Factura facturaPedido) {
		
		Set<LineaFactura> lineasFactura = facturaPedido.getLineasFactura();
		
		for(LineaPedido lineaPedido : pedido.getLineasPedido() ) {
			
			LineaFactura lineaFactura = new LineaFactura();
			
			lineaFactura.setFactura(facturaPedido);
			
			lineaFactura.setArticulo(lineaPedido.getArticulo());
			lineaFactura.setCantidad(lineaPedido.getCantidad());
			lineaFactura.setBaseImponible(lineaPedido.getBaseImponible());
			lineaFactura.setImporteImpuesto(lineaPedido.getImporteImpuesto());
			lineaFactura.setImporteTotal(lineaPedido.getImporteTotal());
			lineaFactura.setDescripcionLinea(lineaPedido.getDescripcionLinea());
			
			lineasFactura.add(lineaFactura);
		}
		
		return lineasFactura;
	}
	
	/**
	 * Regenera factura de Contrato
	 * @param contrato
	 */
	@Transactional
	public void actualizarFacturaContrato(Contrato contrato) {
		
		logger.trace("Entramos en el metodo actualizarFacturaCompra()");
		
		Factura facturaContrato = contrato.getFactura();
		
		if( facturaContrato == null) {
			
			logger.error("Error, no existe la factura para poder actualizarla");
			
			return;
		}
		
		logger.trace("Primero borramos las lineas de la factura");
		
		this.eliminarLineasFactura(facturaContrato);
		
		facturaContrato.getLineasFactura().clear();
		
		logger.trace("Segundo persistimos la factura sin lineas");
		
		this.actualizarFactura(facturaContrato);
		
		logger.trace("Tercero creamos y persistimos las nuevas lineas de la factura");
		
		Set<LineaFactura> lineasFacturaContrato = this.crearLineasFacturaDeLineasContrato(contrato, facturaContrato);
		
		facturaContrato.getLineasFactura().addAll(lineasFacturaContrato);
		
		this.crearNuevasLineasFactura(facturaContrato);
		
		logger.trace("Por ultimo volvemos a persistir la factura");
		
		this.actualizarFactura(facturaContrato);
	}
	
	private Set<LineaFactura> crearLineasFacturaDeLineasContrato(Contrato contrato, Factura facturaContrato) {
		
		Set<LineaFactura> lineasFactura = facturaContrato.getLineasFactura();
		
		for(LineaContrato lineaContrato : contrato.getLineasContrato() ) {
			
			LineaFactura lineaFactura = new LineaFactura();
			
			lineaFactura.setFactura(facturaContrato);
			
			lineaFactura.setArticulo(lineaContrato.getArticulo());
			lineaFactura.setCantidad(lineaContrato.getCantidad());
			lineaFactura.setBaseImponible(lineaContrato.getBaseImponible());
			lineaFactura.setImporteImpuesto(lineaContrato.getImporteImpuesto());
			lineaFactura.setImporteTotal(lineaContrato.getImporteTotal());
			lineaFactura.setDescripcionLinea(lineaContrato.getDescripcionLinea());
			
			facturaContrato.getLineasFactura().add(lineaFactura);
		}
		
		return lineasFactura;
	}
	
	/**
	 * Regenera factura de Venta
	 * @param venta
	 */
	@Transactional
	public void actualizarFacturaVenta(Venta venta) {
		
		logger.trace("Entramos en el metodo actualizarFacturaCompra()");
		
		Factura facturaVenta = venta.getFactura();
		
		if( facturaVenta == null) {
			
			logger.error("Error, no existe la factura para poder actualizarla");
			
			return;
		}
		
		logger.trace("Primero borramos las lineas de la factura");
		
		this.eliminarLineasFactura(facturaVenta);
		
		facturaVenta.getLineasFactura().clear();
		
		logger.trace("Segundo persistimos la factura sin lineas");
		
		this.actualizarFactura(facturaVenta);
		
		logger.trace("Tercero creamos y persistimos las nuevas lineas de la factura");
		
		Set<LineaFactura> lineasFacturaVenta = this.crearLineasFacturaDeLineasVenta(venta, facturaVenta);
		
		facturaVenta.getLineasFactura().addAll(lineasFacturaVenta);
		
		this.crearNuevasLineasFactura(facturaVenta);
		
		logger.trace("Por ultimo volvemos a persistir la factura");
		
		this.actualizarFactura(facturaVenta);
	}
	
	private Set<LineaFactura> crearLineasFacturaDeLineasVenta(Venta venta, Factura facturaVenta) {
		
		Set<LineaFactura> lineasFactura = facturaVenta.getLineasFactura();
		
		for(LineaVenta lineaVenta : venta.getLineasVenta() ) {
			
			LineaFactura lineaFactura = new LineaFactura();
			
			lineaFactura.setFactura(facturaVenta);
			
			lineaFactura.setArticulo(lineaVenta.getArticulo());
			lineaFactura.setCantidad(lineaVenta.getCantidad());
			lineaFactura.setBaseImponible(lineaVenta.getBaseImponible());
			lineaFactura.setImporteImpuesto(lineaVenta.getImporteImpuesto());
			lineaFactura.setImporteTotal(lineaVenta.getImporteTotal());
			lineaFactura.setDescripcionLinea(lineaVenta.getDescripcionLinea());
			
			lineasFactura.add(lineaFactura);
		}
		
		return lineasFactura;
	}
	
	/*
	 * Metodos Generales
	 */
	
	/**
	 * Elimina las lineas de una factura
	 * @param factura
	 */
	private void eliminarLineasFactura(Factura factura) {
		
		try {
			
			lineaFacturaRepository.deleteAll( factura.getLineasFactura() );
			
			lineaFacturaRepository.flush();
			
		}catch(Exception e) {
			
			logger.error("Error, no se ha podido borrar las lineas de factura");
			
			e.printStackTrace();
		}
	}

	/**
	 * Actualiza la factura
	 * @param factura
	 */
	private void actualizarFactura(Factura factura) {
		
		try {
			
			facturaRepository.saveAndFlush(factura);
			
		}catch(Exception e) {
			
			logger.error("Error, no se ha podido actualizar la factura");
			
			e.printStackTrace();
		}
	}
	
	/**
	 * Persiste nuevas lineas de factura
	 * @param factura
	 */
	private void crearNuevasLineasFactura(Factura factura) {
		
		try {
			
			lineaFacturaRepository.saveAll( factura.getLineasFactura() );
			
			lineaFacturaRepository.flush();
			
		}catch(Exception e) {
			
			logger.error("Error, al persistir las nuevas lineas de factura");
			
			e.printStackTrace();
		}
	}
}
