package com.erpweb.servicios.ventas.interfaces;

import com.erpweb.dto.FacturaDto;
import com.erpweb.entidades.ventas.Factura;

public interface FacturaServiceInterfaz {

	public Boolean creaFacturaDesdeFacturaDto(FacturaDto facturaDto); //Crea la factura mediante dto
	
	public FacturaDto obtieneFacturaDto(Long id, Long empresaId); //Visualizar la factura
	
	public Boolean actualizaFactura(FacturaDto facturaDto); //Actualizamos la factura 

	public Boolean eliminaFactura(Factura factura); //Borramos la factura 
	
	public Factura obtieneFactura(Long id, Long empresaId); //Obtenemos la factura de BBDD

}
