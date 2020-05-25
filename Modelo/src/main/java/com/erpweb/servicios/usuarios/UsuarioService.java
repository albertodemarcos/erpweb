package com.erpweb.servicios.usuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpweb.repositorios.empresa.EmpresaRepository;
import com.erpweb.repositorios.usuarios.UsuarioRepository;
import com.erpweb.servicios.usuarios.interfaces.UsuarioServiceInterfaz;



@Service
public class UsuarioService implements UsuarioServiceInterfaz {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	


}
