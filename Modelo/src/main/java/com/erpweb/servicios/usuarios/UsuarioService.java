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
import com.erpweb.utiles.AccionRespuesta;


@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	public AccionRespuesta crearUsuarioDesdeUsuarioDto(UsuarioDto usuarioDto) {
		
		logger.debug("Entramos en el metodo crearUsuarioDesdeUsuarioDto() con la empresa={}", usuarioDto.getEmpresaId() );
		
		Usuario usuario = new Usuario();

		if(usuarioDto.getEmpresaId() == null) {
			
			return new AccionRespuesta();
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
			
			logger.error("Error en el metodo crearUsuarioDesdeUsuarioDto() con la empresa{} ", usuarioDto.getEmpresaId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public AccionRespuesta actualizarUsuarioDesdeUsuarioDto(UsuarioDto usuarioDto) {
		
		logger.debug("Entramos en el metodo actualizarUsuarioDesdeUsuarioDto() con la empresa={}", usuarioDto.getEmpresaId() );
		
		Usuario usuario = new Usuario();

		if(usuarioDto.getEmpresaId() == null) {
			
			return new AccionRespuesta();
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
			
			logger.error("Error en el metodo actualizarUsuarioDesdeUsuarioDto() con la empresa{} ", usuarioDto.getEmpresaId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public AccionRespuesta eliminarUsuario(Usuario usuario) {
		
		logger.debug("Entramos en el metodo eliminarUsuario() con la empresa={}", usuario.getEmpresa().getId() );
		
		if(usuario == null || usuario.getId() == null) {
			
			return new AccionRespuesta();
		}
		
		try {
			//Elimnamos el usuario
			usuarioRepository.deleteById(usuario.getId());
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo eliminarUsuario() con la empresa{} ", usuario.getEmpresa().getId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public UsuarioDto obtenerUsuarioDtoDesdeUsuario(Long id, Long empresaId) {
		
		logger.debug("Entramos en el metodo obtenerUsuarioDtoDesdeUsuario() con la empresa={}", empresaId );
		
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
		
		logger.error("Error en el metodo obtenerUsuarioDtoDesdeUsuario() con la empresa{} ", empresaId );
		
		return usuarioDto;
	}

}
