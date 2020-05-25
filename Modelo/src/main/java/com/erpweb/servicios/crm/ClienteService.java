package com.erpweb.servicios.crm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpweb.repositorios.comun.DireccionPostalRepository;
import com.erpweb.repositorios.comun.PoblacionRepository;
import com.erpweb.repositorios.comun.ProvinciaRepository;
import com.erpweb.repositorios.crm.ClienteRepository;
import com.erpweb.repositorios.empresa.EmpresaRepository;
import com.erpweb.servicios.crm.interfaces.ClienteServiceInterfaz;



@Service
public class ClienteService implements ClienteServiceInterfaz {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private DireccionPostalRepository direccionPostalRepository;
	
	@Autowired
	private ProvinciaRepository provinciaRepository;
	
	@Autowired
	private PoblacionRepository poblacionRepository;
	
	
		
/*
//Rellenamos la direccion postal
DireccionPostal direccionPostal = direccionPostalRepository.findByIdAndEmpresaId(id, empresaId);

//Buscamos la provinicia
Provincia provincia = direccionPostal.getProvincia() != null ? direccionPostal.getProvincia() : new Provincia();

//Buscamos la poblacion
Poblacion poblacion = direccionPostal.getPoblacion()!= null ? direccionPostal.getPoblacion() : new Poblacion();



clienteDto.setPoblacionId(provincia.getId() !=null ? provincia.getId() : 0L );
clienteDto.setNombrePoblacion(provincia.getNombre() != null ? provincia.getNombre() : "SIN_PROVINCIA");

clienteDto.setProvinciaId(poblacion.getId() !=null ? poblacion.getId() : 0L);
clienteDto.setNombreProvincia(poblacion.getNombre() !=null ? poblacion.getNombre() : "SIN_POBLACION");


//Buscamos la provinicia
Provincia provincia = provinciaRepository.findById(clienteDto.getProvinciaId()).orElse(new Provincia());

//Buscamos la poblacion
Poblacion poblacion = poblacionRepository.findById(clienteDto.getPoblacionId()).orElse(new Poblacion());
 * */
	

}
