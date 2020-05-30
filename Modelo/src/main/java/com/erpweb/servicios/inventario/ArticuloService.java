package com.erpweb.servicios.inventario;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpweb.dto.ArticuloDto;
import com.erpweb.entidades.compras.Proveedor;
import com.erpweb.entidades.comun.Impuesto;
import com.erpweb.entidades.empresa.Empresa;
import com.erpweb.entidades.inventario.Almacen;
import com.erpweb.entidades.inventario.Articulo;
import com.erpweb.repositorios.compras.ProveedorRepository;
import com.erpweb.repositorios.comun.ImpuestoRepository;
import com.erpweb.repositorios.empresa.EmpresaRepository;
import com.erpweb.repositorios.inventario.AlmacenRepository;
import com.erpweb.repositorios.inventario.ArticuloRepository;
import com.erpweb.utiles.AccionRespuesta;



@Service
public class ArticuloService {

	@Autowired
	private ArticuloRepository articuloRepository;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private ProveedorRepository proveedorRepository;
	
	@Autowired
	private ImpuestoRepository impuestoRepository;
	
	@Autowired
	private AlmacenRepository almacenRepository;
	
	
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	public AccionRespuesta crearArticuloDesdeArticuloDto(ArticuloDto articuloDto) {
		
		logger.debug("Entramos en el metodo crearArticuloDesdeArticuloDto() con la empresa={}", articuloDto.getEmpresaId() );
		
		Articulo articulo = new Articulo();
		
		if(articuloDto.getEmpresaId() == null) {
			
			return new AccionRespuesta();
		}
		
		Empresa empresa = empresaRepository.findById(articuloDto.getEmpresaId()).orElse(new Empresa());
		
		articulo.setCodigo(articuloDto.getCodigo());
		articulo.setEmpresa(empresa);
		articulo.setNombre(articuloDto.getNombre());
		articulo.setDescripcion(articuloDto.getDescripcion());
		articulo.setBaseImponible(articuloDto.getBaseImponible());
		articulo.setImporteTotal(articuloDto.getImporteTotal());
		
		//Recuperamos el impuesto
		Impuesto impuesto = impuestoRepository.findById(articuloDto.getImpuestoId()).orElse(new Impuesto());
		
		//Recuperamos el proveedor
		Proveedor proveedor = proveedorRepository.findByIdAndEmpresaId(articuloDto.getProveedorId(), articuloDto.getEmpresaId());
		
		//Sera opcional
		Almacen almacen;
		
		if(articuloDto.getAlmacenId() != null) {
			//Recuperamos el almacen
			almacen = almacenRepository.findByIdAndEmpresaId(articuloDto.getAlmacenId(), articuloDto.getEmpresaId());
			//Introducimos el almacen
			articulo.setAlmacen(almacen);
		}
		
		articulo.setImpuesto(impuesto);
		articulo.setProveedor(proveedor);
		
		try {
			
			//Guardamos el articulo en base de datos
			articuloRepository.save(articulo);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo crearArticuloDesdeArticuloDto() con la empresa{} ", articuloDto.getEmpresaId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public AccionRespuesta actualizarArticuloDesdeArticuloDto(ArticuloDto articuloDto) {
		
		logger.debug("Entramos en el metodo actualizarArticuloDesdeArticuloDto() con la empresa={}", articuloDto.getEmpresaId() );
		
		Articulo articulo = new Articulo();
		
		if(articuloDto.getEmpresaId() == null) {
			
			return new AccionRespuesta();
		}
		
		Empresa empresa = empresaRepository.findById(articuloDto.getEmpresaId()).orElse(new Empresa());
		
		articulo.setId(articuloDto.getId());
		articulo.setCodigo(articuloDto.getCodigo());
		articulo.setEmpresa(empresa);
		articulo.setNombre(articuloDto.getNombre());
		articulo.setDescripcion(articuloDto.getDescripcion());
		articulo.setBaseImponible(articuloDto.getBaseImponible());
		articulo.setImporteTotal(articuloDto.getImporteTotal());
		
		//Recuperamos el impuesto
		Impuesto impuesto = impuestoRepository.findById(articuloDto.getImpuestoId()).orElse(new Impuesto());
		
		//Recuperamos el proveedor
		Proveedor proveedor = proveedorRepository.findByIdAndEmpresaId(articuloDto.getProveedorId(), articuloDto.getEmpresaId());
		
		//Sera opcional
		Almacen almacen;
		
		if(articuloDto.getAlmacenId() != null) {
			//Recuperamos el almacen
			almacen = almacenRepository.findByIdAndEmpresaId(articuloDto.getAlmacenId(), articuloDto.getEmpresaId());
			//Introducimos el almacen
			articulo.setAlmacen(almacen);
		}
		articulo.setImpuesto(impuesto);
		articulo.setProveedor(proveedor);
		
		try {
			//Guardamos el articulo en base de datos
			articuloRepository.save(articulo);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo actualizarArticuloDesdeArticuloDto() con la empresa{} ", articuloDto.getEmpresaId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public AccionRespuesta eliminarArticulo(Articulo articulo) {
		
		logger.debug("Entramos en el metodo eliminarArticulo() con la empresa={}", articulo.getEmpresa().getId() );
		
		if(articulo == null || articulo.getId() == null) {
			
			return new AccionRespuesta();
		}
		
		try {
			//Elimnamos el gasto
			articuloRepository.deleteById(articulo.getId());
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo eliminarArticulo() con la empresa{} ", articulo.getEmpresa().getId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public ArticuloDto obtenerArticuloDtoDesdeArticulo(Long id, Long empresaId) {
		
		logger.debug("Entramos en el metodo obtenerArticuloDtoDesdeArticulo() con la empresa={}", empresaId );
		
		Articulo articulo = articuloRepository.findByIdAndEmpresaId(id, empresaId);
		
		if(articulo == null) {
			return new ArticuloDto();
		}
		
		ArticuloDto articuloDto = new ArticuloDto();
		
		try {
			
			articuloDto.setId(articulo.getId());
			articuloDto.setCodigo(articulo.getCodigo());
			articuloDto.setEmpresaId(articulo.getEmpresa().getId());
			articuloDto.setNombre(articulo.getNombre());
			articuloDto.setDescripcion(articulo.getDescripcion());
			articuloDto.setBaseImponible(articulo.getBaseImponible());
			articuloDto.setImporteTotal(articulo.getImporteTotal());
			
			if(articulo.getImpuesto() != null) {
				articuloDto.setImpuestoId(articulo.getImpuesto().getId());
				articuloDto.setCodigoImpuesto(articulo.getImpuesto().getCodigo());
				articuloDto.setNombreImpuesto(articulo.getImpuesto().getNombre());
				articuloDto.setPorcentajeImpuesto(articulo.getImpuesto().getPorcentaje());
			}
			
			if(articulo.getProveedor() != null) {
				articuloDto.setProveedorId(articulo.getProveedor().getId());
				articuloDto.setCodigoProveedor(articulo.getProveedor().getCodigo());
				articuloDto.setNombreProveedor(articulo.getProveedor().getNombre());
				articuloDto.setNombreEmpresaProveedor(articulo.getProveedor().getNombreEmpresa());
				articuloDto.setTelefonoProveedor(articulo.getProveedor().getTelefono());
				articuloDto.setTipoProveedor(articulo.getProveedor().getTipoProveedor());
			}
			
			if(articulo.getAlmacen() != null) {
				articuloDto.setAlmacenId(articulo.getAlmacen().getId());
				articuloDto.setCodigoAlmacen(articulo.getAlmacen().getCodigo());
				articuloDto.setNombreAlmacen(articulo.getAlmacen().getNombre());
			}
			
		} catch(Exception e) {
			
			logger.error("Error en el metodo obtenerArticuloDtoDesdeArticulo() con la empresa{} ", empresaId );
			
			e.printStackTrace();
		}
		
		return articuloDto;
	}

}
