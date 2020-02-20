package com.erpweb.servicios.usuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpweb.dto.UsuarioDto;
import com.erpweb.entidades.empresa.Empresa;
import com.erpweb.entidades.usuarios.Usuario;
import com.erpweb.repositorios.empresa.EmpresaRepository;
import com.erpweb.repositorios.usuarios.UsuarioRepository;
import com.erpweb.servicios.usuarios.interfaces.UsuarioServiceInterfaz;



@Service
public class UsuarioService implements UsuarioServiceInterfaz {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Override
	public Boolean creaUsuarioDesdeUsuarioDto(UsuarioDto usuarioDto) {
		
		Usuario usuario = new Usuario();

		if(usuarioDto.getEmpresaId() == null) {
			return Boolean.FALSE;
		}
		
		Empresa empresa = empresaRepository.findOne(usuarioDto.getEmpresaId());
		
		usuario.setCodigo(usuarioDto.getCodigo());
		usuario.setEmpresa(empresa);
		usuario.setName(usuarioDto.getName());
		usuario.setPassword(usuarioDto.getPassword());
		usuario.setIdentidad(usuarioDto.getIdentidad());
		usuario.setIdioma(usuarioDto.getIdioma());
		
		try {
			//Guardamos el usuario en base de datos
			usuarioRepository.save(usuario);
			
		}catch(Exception e) {
			
			System.out.println("Error: " + e.getLocalizedMessage() );
			
			return Boolean.FALSE;
		}
		
		return Boolean.TRUE;
	}

	@Override
	public UsuarioDto obtieneUsuarioDto(Long id, Long empresaId) {

		Usuario usuario = usuarioRepository.findByIdAndEmpresaId(id, empresaId);
		
		if(usuario == null) {
			return new UsuarioDto();
		}
		
		UsuarioDto usuarioDto = new UsuarioDto();
		
		usuarioDto.setCodigo(usuario.getCodigo());
		usuarioDto.setEmpresaId(usuario.getEmpresa().getId());
		usuarioDto.setName(usuario.getName());
		usuarioDto.setPassword(usuario.getPassword());
		usuarioDto.setIdentidad(usuario.getIdentidad());
		usuarioDto.setIdioma(usuario.getIdioma());
		
		return usuarioDto;
	}

	@Override
	public Boolean actualizaUsuario(UsuarioDto usuarioDto) {
		
		Usuario usuario = new Usuario();

		if(usuarioDto.getEmpresaId() == null) {
			return Boolean.FALSE;
		}
		
		Empresa empresa = empresaRepository.findOne(usuarioDto.getEmpresaId());
		
		usuario.setId(usuarioDto.getId());
		usuario.setCodigo(usuarioDto.getCodigo());
		usuario.setEmpresa(empresa);
		usuario.setName(usuarioDto.getName());
		usuario.setPassword(usuarioDto.getPassword());
		usuario.setIdentidad(usuarioDto.getIdentidad());
		usuario.setIdioma(usuarioDto.getIdioma());
		
		try {
			//Guardamos el usuario en base de datos
			usuarioRepository.save(usuario);
			
		}catch(Exception e) {
			
			System.out.println("Error: " + e.getLocalizedMessage() );
			
			return Boolean.FALSE;
		}
		
		return Boolean.TRUE;
	}

	@Override
	public Boolean eliminaUsuario(Usuario usuario) {
		
		if(usuario == null || usuario.getId() == null) {
			return Boolean.FALSE;
		}
		
		try {
			//Elimnamos el usuario
			usuarioRepository.deleteById(usuario.getId());
			
		}catch(Exception e) {
			
			System.out.println("Error: " + e.getLocalizedMessage());
			
			return Boolean.FALSE;
		}
		
		return Boolean.TRUE;
	}

	@Override
	public Usuario obtieneUsuario(Long id, Long empresaId) {

		Usuario usuario;
		
		try {
			//Recuperamos el usuario
			usuario = usuarioRepository.findByIdAndEmpresaId(id, empresaId);
			
		}catch(Exception e) {
			
			System.out.println("Error: " + e.getLocalizedMessage());
			
			return new Usuario();
		}
		
		return usuario;
	}


}
