package com.erpweb.servicios.empresa.interfaces;

import com.erpweb.dto.EmpresaDto;
import com.erpweb.entidades.empresa.Empresa;

public interface EmpresaServiceInterfaz {

	public Boolean creaEmpresaDesdeEmpresaDto(EmpresaDto empresaDto); //Crea  mediante
	
	public EmpresaDto obtieneEmpresaDto(Long id, Long empresaId); //Visualizar la empresa
	
	public Boolean actualizaEmpresa(EmpresaDto empresaDto); //Actualizamos la empresa

	public Boolean eliminaEmpresa(Empresa empresa); //Borramos la empresa
	
	public Empresa obtieneEmpresa(Long id, Long empresaId); //Obtenemos la empresa de BBDD
}
