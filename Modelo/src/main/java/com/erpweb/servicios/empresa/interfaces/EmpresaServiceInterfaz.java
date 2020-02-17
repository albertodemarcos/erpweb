package com.erpweb.servicios.empresa.interfaces;

import com.erpweb.entidades.empresa.Empresa;

public interface EmpresaServiceInterfaz {

	public void obtieneEmpresa(Long id, Long empresaId); //Obtenemos la empresa de BBDD
	
	public void obtieneEmpresaDto(Long id, Long empresaId); //Obtenemos la empresa  y lo llevamos a capa vista mediante dto
	
	public void actualizaEmpresa(Empresa empresa); //Actualizamos la empresa

	public void eliminaEmpresa(Empresa empresa); //Borramos la empresa
}
