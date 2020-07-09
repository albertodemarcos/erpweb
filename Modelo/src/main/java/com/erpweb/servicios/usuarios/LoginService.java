package com.erpweb.servicios.usuarios;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpweb.dto.UsuarioDto;
import com.erpweb.entidades.usuarios.Usuario;
import com.erpweb.repositorios.usuarios.UsuarioRepository;
import com.erpweb.utiles.AccionRespuesta;
import com.erpweb.utiles.Encriptador;

@Service
public class LoginService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private Encriptador encriptador;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	public AccionRespuesta obtieneUsuarioDeUsername(UsuarioDto usuarioHttp) {
		
		logger.debug("Entramos en el metodo obtenerUsuarioDtoDesdeUsuario()" );
		
		AccionRespuesta accionRespuesta = new AccionRespuesta();
		
		try {
			
			Usuario usuario = usuarioRepository.findByUsername(usuarioHttp.getUsername());
			
			if(usuario == null || !BooleanUtils.isTrue(this.compruebaUsuario(usuarioHttp, usuario) ) ) {
				
				return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
			}
			
			UsuarioDto usuarioDto = new UsuarioDto();
			
			usuarioDto.setId(usuario.getId());
			usuarioDto.setCodigo(usuario.getCodigo());
			usuarioDto.setNombreCompleto(usuario.getNombreCompleto());
			usuarioDto.setUsername(usuario.getUsername());
			//usuarioDto.setPassword(usuario.getPassword());
			usuarioDto.setRole(usuario.getRole());
			
			HashMap<String, Object> data = new HashMap<String, Object>();
			
			data.put("usuarioDto", usuarioDto);
			
			accionRespuesta.setId(usuarioDto.getId());
			
			accionRespuesta.setCodigo("OK");
			
			accionRespuesta.setResultado(Boolean.TRUE);
			
			accionRespuesta.setData(data);
			
			return accionRespuesta;
			
		} catch(Exception e) {
			
			logger.error("Error en el metodo obtenerUsuarioDtoDesdeUsuario()" );
			
			e.printStackTrace();
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
		
	}
	
	
	public Boolean compruebaUsuario(UsuarioDto usuarioHttp, Usuario usuario) throws Exception /*UnsupportedEncodingException,
		NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException,IllegalBlockSizeException, BadPaddingException */{
		
		//Este caso no es posible pero evitamos errores
		if( usuarioHttp == null 
				|| StringUtils.isBlank(usuarioHttp.getUsername()) 
					|| StringUtils.isBlank(usuarioHttp.getPassword())) {
			
			return Boolean.FALSE;			
		}
		
		String textoEncriptado = this.encriptador.desencriptarBase64( usuarioHttp.getPassword() ); 
		
		if( usuario.getPassword().equals(textoEncriptado) ) {
			
			return Boolean.TRUE;
		}
		
		return Boolean.FALSE;
	}
	
	
	
	
	
	
}
