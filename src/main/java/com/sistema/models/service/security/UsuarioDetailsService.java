package com.sistema.models.service.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sistema.models.domain.Usuario;
import com.sistema.models.service.faces.UsuarioService;

@Service
public class UsuarioDetailsService implements UserDetailsService {
	
	@Autowired
	private UsuarioService usuarioService;
	

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Optional<Usuario> usuario = usuarioService.findUsuarioByEmail(email);
		
		if (!usuario.isPresent()) {
			throw new UsernameNotFoundException(" Usuário não cadastrado !"+email);
		}
		
		return new UsuarioSistema(usuario.get());
	}

}
