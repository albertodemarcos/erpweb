package com.erpweb.servicios.usuarios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erpweb.dto.UsuarioDto;
import com.erpweb.entidades.usuarios.Usuario;
import com.erpweb.repositorios.usuarios.UsuarioRepository;
import com.erpweb.utiles.AccionRespuesta;


@Service
public class UsuarioService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	public AccionRespuesta crearUsuarioDesdeUsuarioDto(UsuarioDto usuarioDto) {
		
		logger.debug("Entramos en el metodo crearUsuarioDesdeUsuarioDto() con ID={}", usuarioDto.getId() );
		
		Usuario usuario = new Usuario();
		
		usuario.setCodigo(usuarioDto.getCodigo());
		usuario.setNombreCompleto(usuarioDto.getNombreCompleto());
		usuario.setUsuario(usuarioDto.getUsuario());
		usuario.setPass(usuarioDto.getPass());
		usuario.setRole(usuarioDto.getRole());
		
		List<String> roles = new ArrayList<String>();
		roles.add(usuarioDto.getRole());
		
		//Spring
		usuario.setName(usuarioDto.getUsuario());
		usuario.setPassword(usuarioDto.getPass());
		usuario.setRoles( roles );
		
		try {
			//Guardamos el usuario en base de datos
			Usuario usuarioSave = usuarioRepository.save(usuario);
			
			return this.devolverDatosUsuarioDto(usuarioDto, usuarioSave);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo crearUsuarioDesdeUsuarioDto() con ID={}", usuarioDto.getId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
	}
	
	public AccionRespuesta actualizarUsuarioDesdeUsuarioDto(UsuarioDto usuarioDto) {
		
		logger.debug("Entramos en el metodo actualizarUsuarioDesdeUsuarioDto() con ID={}", usuarioDto.getId() );
		
		Usuario usuario = new Usuario();
		
		usuario.setId(usuarioDto.getId());
		usuario.setCodigo(usuarioDto.getCodigo());
		usuario.setNombreCompleto(usuarioDto.getNombreCompleto());
		usuario.setUsuario(usuarioDto.getUsuario());
		usuario.setPass(usuarioDto.getPass());
		usuario.setRole(usuarioDto.getRole());
		
		List<String> roles = new ArrayList<String>();
		roles.add(usuarioDto.getRole());
		
		//Spring
		usuario.setName(usuarioDto.getUsuario());
		usuario.setPassword(usuarioDto.getPass());
		usuario.setRoles( roles );
		
		try {
			//Guardamos el usuario en base de datos
			Usuario usuarioSave = usuarioRepository.save(usuario);
			
			return this.devolverDatosActualizadosUsuarioDto(usuarioDto, usuarioSave);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo actualizarUsuarioDesdeUsuarioDto() con ID={}", usuarioDto.getId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
	}
	
	public AccionRespuesta eliminarUsuario(Usuario usuario) {
		
		logger.debug("Entramos en el metodo eliminarUsuario() con ID={}", usuario.getId() );
		
		if(usuario == null || usuario.getId() == null) {
			
			logger.error("ERROR en el metodo eliminarUsuario()");
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
		
		try {
			//Elimnamos el usuario
			usuarioRepository.deleteById(usuario.getId());
			
			return new AccionRespuesta(-2L, "OK", Boolean.TRUE);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo eliminarUsuario() con ID={}", usuario.getId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
	}
	
	public AccionRespuesta eliminarUsuarioPorId(Long usuarioId) {
		
		logger.error("Entramos en el metodo eliminarUsuarioPorId()" );
		
		if( usuarioId == null) {
			
			logger.error("ERROR en el metodo eliminarUsuarioPorId()");
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
		
		try {
			
			//Elimnamos el usuario
			usuarioRepository.deleteById(usuarioId);
			
			return new AccionRespuesta(-2L, "OK", Boolean.TRUE);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo eliminarUsuarioPorId() con ID={}", usuarioId );
			
			e.printStackTrace();
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
	}
	
	public UsuarioDto obtenerUsuarioDtoDesdeUsuario(Long id) {
		
		logger.debug("Entramos en el metodo obtenerUsuarioDtoDesdeUsuario() con ID={}", id );
		
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
		
		Usuario usuario = usuarioOptional.get();
		
		if(usuario == null) {
			return new UsuarioDto();
		}
		
		UsuarioDto usuarioDto = new UsuarioDto();
		
		try {
			
			usuarioDto.setId(usuario.getId());
			usuarioDto.setCodigo(usuario.getCodigo());
			usuarioDto.setNombreCompleto(usuario.getNombreCompleto());
			usuarioDto.setUsuario(usuario.getUsuario());
			usuarioDto.setPass(usuario.getPass());
			usuarioDto.setRole(usuario.getRole());
			
		} catch(Exception e) {
			
			logger.error("Error en el metodo obtenerUsuarioDtoDesdeUsuario() con ID={}", id );
			
			e.printStackTrace();
		}
		
		return usuarioDto;
	}
	
	public List<UsuarioDto> getListadoUsuarios() {
		
		logger.debug("Entramos en el metodo getListadoUsuarios()" );
		
		try {
			
			List<Usuario> usuarios = usuarioRepository.findAll();
			
			return this.obtieneListadoUsuarioDtoDelRepository(usuarios);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo getListadoUsuarios()" );
			
			e.printStackTrace();
		}
			
		return new ArrayList<UsuarioDto>();
	}
	
	public AccionRespuesta getUsuario(Long usuarioId, Usuario user) {
		
		logger.debug("Entramos en el metodo getUsuario()");
		
		if( usuarioId == null) {
			
			return new AccionRespuesta(-1L, "Error, existe el usuario", Boolean.FALSE);
		}
		
		UsuarioDto usuarioDto = this.obtenerUsuarioDtoDesdeUsuario(usuarioId);
		
		AccionRespuesta AccionRespuesta = new AccionRespuesta();
		
		if( usuarioDto != null ) {
			
			AccionRespuesta.setId( usuarioDto.getId() );
			
			AccionRespuesta.setRespuesta("");
			
			AccionRespuesta.setResultado(Boolean.TRUE);
			
			HashMap<String, Object> mapa = new HashMap<String, Object>();
			
			mapa.put("usuarioDto", usuarioDto);
			
			AccionRespuesta.setData(new HashMap<String, Object>(mapa));
			
		}else {
			
			AccionRespuesta.setId( -1L );
			
			AccionRespuesta.setRespuesta("Error, no se ha podido recuperar el usuario");
			
			AccionRespuesta.setResultado(Boolean.FALSE);
		}
		
		return AccionRespuesta;
	}
	
	public AccionRespuesta getCrearEditarUsuario(UsuarioDto usuarioDto, Usuario user) {
		
		logger.debug("Entramos en el metodo getCrearEditarUsuario() con usuario={}", user.getId() );
		
		if( usuarioDto.getId() != null && usuarioDto.getId().longValue() > 0) {
			
			logger.debug("Se va a realizar una actualizacion del Usuario con usuario={}", user.getId() );
			
			return this.actualizarUsuarioDesdeUsuarioDto(usuarioDto);
			
		} else {
			
			logger.debug("Se va a crear un Usuario con usuario={}", user.getId() );
			
			return this.crearUsuarioDesdeUsuarioDto(usuarioDto);
		}
	}
	
	private AccionRespuesta devolverDatosUsuarioDto(UsuarioDto usuarioDto, Usuario usuarioSave) {
		
		AccionRespuesta respuesta = new AccionRespuesta();
		
		//Guardado el cliente se devuelve en su dto
		if(usuarioSave != null && usuarioSave.getId() != null) {
			
			usuarioDto.setId(usuarioSave.getId());
			
			respuesta.setId(usuarioSave.getId());
			
			respuesta.setCodigo("OK");
						
			respuesta.setResultado(Boolean.TRUE);
			
			HashMap<String, Object> data= new HashMap<String, Object> ();
			
			data.put("usuarioDto", usuarioDto);
			
			respuesta.setData(data);
			
		}else {
			
			respuesta.setCodigo("NOK");
						
			respuesta.setResultado(Boolean.FALSE);
			
			HashMap<String, Object> data= new HashMap<String, Object> ();
			
			data.put("usuarioDto", usuarioDto);
			
			respuesta.setData(data);
		}
		
		return respuesta;
	}
	
	private AccionRespuesta devolverDatosActualizadosUsuarioDto(UsuarioDto usuarioDto, Usuario usuarioSave) {
		
		AccionRespuesta respuesta = new AccionRespuesta();
		
		if(usuarioSave != null && usuarioDto != null) {
			
			usuarioDto.setId(usuarioSave.getId());
			
			respuesta.setId(usuarioSave.getId());
			
			respuesta.setCodigo("OK");
						
			respuesta.setResultado(Boolean.TRUE);
			
			HashMap<String, Object> data= new HashMap<String, Object> ();
			
			data.put("usuarioDto", usuarioDto);
			
			respuesta.setData(data);
			
		}else {
			
			respuesta.setId(-1L);
			
			respuesta.setCodigo("NOK");
			
			respuesta.setResultado(Boolean.FALSE);
			
			HashMap<String, Object> data= new HashMap<String, Object> ();
			
			data.put("usuarioDto", usuarioDto);
			
			respuesta.setData(data);			
		}
		
		return respuesta;		
	}
	
	private List<UsuarioDto> obtieneListadoUsuarioDtoDelRepository(List<Usuario> usuarios){
		
		List<UsuarioDto> usuariosDto = new ArrayList<UsuarioDto>();
		
		if(CollectionUtils.isNotEmpty(usuarios) ) {
			
			for(Usuario usuario : usuarios) {
				
				UsuarioDto usuarioDto = new UsuarioDto();
				
				usuarioDto.setId(usuario.getId());
				usuarioDto.setCodigo(usuario.getCodigo());
				usuarioDto.setNombreCompleto(usuario.getNombreCompleto());
				usuarioDto.setUsuario(usuario.getUsuario());
				usuarioDto.setPass(usuario.getPass());
				usuarioDto.setRole(usuario.getRole());
				
				usuariosDto.add(usuarioDto);			
			}
		}
		
		return usuariosDto;
	}

	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		logger.debug("Entramos en el metodo loadUserByUsername() para autenticar usuario"); 
		
		Usuario usuario = usuarioRepository.findByUsuario(username);
		
		if( usuario == null) {
			
			logger.error("Error, el usuario no esta registrado");
			
			throw new UsernameNotFoundException("Error, el usuario no esta registrado");
		}
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		SimpleGrantedAuthority simple = new SimpleGrantedAuthority(usuario.getRole());
		
		authorities.add(simple);
		
		return new User(usuario.getUsuario(), usuario.getPass(), usuario.getActivo(), true, true, true, authorities);
	}
	
	public Usuario devolverInformacionUsuario(String username) {
		
		return usuarioRepository.findByUsuario(username);
	}
	

}
