package com.erpweb.servicios.usuarios;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpweb.dto.UsuarioDto;
import com.erpweb.entidades.empresa.Empresa;
import com.erpweb.entidades.usuarios.Usuario;
import com.erpweb.repositorios.empresa.EmpresaRepository;
import com.erpweb.repositorios.usuarios.UsuarioRepository;


@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	public void crearUsuarioDesdeUsuarioDto(UsuarioDto usuarioDto) {
		
		Usuario usuario = new Usuario();

		if(usuarioDto.getEmpresaId() == null) {
			//return Boolean.FALSE;
		}
		
		Empresa empresa = empresaRepository.findById(usuarioDto.getEmpresaId()).orElse(new Empresa());
		
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
			
			//return Boolean.FALSE;
		}
		
		//return Boolean.TRUE;
	}
	
	public void actualizarUsuarioDesdeUsuarioDto(UsuarioDto usuarioDto) {
		
		Usuario usuario = new Usuario();

		if(usuarioDto.getEmpresaId() == null) {
			//return Boolean.FALSE;
		}
		
		Empresa empresa = empresaRepository.findById(usuarioDto.getEmpresaId()).orElse(new Empresa());
		
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
			
			//return Boolean.FALSE;
		}
		
		//return Boolean.TRUE;
	}
	
	public void eliminarUsuario(Usuario usuario) {
		
		if(usuario == null || usuario.getId() == null) {
			//return Boolean.FALSE;
		}
		
		try {
			//Elimnamos el usuario
			usuarioRepository.deleteById(usuario.getId());
			
		}catch(Exception e) {
			
			System.out.println("Error: " + e.getLocalizedMessage());
			
			//return Boolean.FALSE;
		}
		
		//return Boolean.TRUE;
	}
	
	public UsuarioDto obtenerUsuarioDtoDesdeUsuario(Long id, Long empresaId) {
		
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

}
