package com.sistema.web.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.models.domain.Login;
import com.sistema.models.domain.Usuario;
import com.sistema.models.service.faces.UsuarioService;


@RestController
public class LoginController {

	//@Autowired
	//private UserDetailsService userDetaisService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	
	@ResponseBody
	@PostMapping(value="/login")
	public Usuario login( @RequestBody @Valid Login login  ) {
		
		Optional<Usuario> usuario = usuarioService.findUsuarioByEmail(login.getEmail());
		
		if ( !usuario.isPresent() ) {
			throw new UsernameNotFoundException("Usuário não cadastrado!");
		}
		
		if ( login.getEmail().equals(usuario.get().getEmail()) && usuario.get().isAtivo() == false ) {
			throw new LockedException("Usuario está bloqueado ");
		}
		
		if ( login.getEmail().equals(usuario.get().getEmail()) && 
				BCrypt.checkpw(login.getPassword(),  usuario.get().getPassword()) ) {
			  new UsernamePasswordAuthenticationToken(usuario.get(),usuario.get().getPassword(), usuario.get().getAuthorities());
	 	   	
		} else {
		   throw new BadCredentialsException("A senha informada é inválida ou está incorreta!");  
		}
		
		
	 	return usuario.get();
	}
	
	
}
