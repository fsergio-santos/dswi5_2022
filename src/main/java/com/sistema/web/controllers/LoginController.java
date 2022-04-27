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
import com.sistema.models.domain.dto.UsuarioLogadoDto;
import com.sistema.models.service.faces.UsuarioService;
import com.sistema.models.service.security.jwt.JwtTokenProvider;


@RestController
public class LoginController {

    private String token;
    
    @Autowired
    private JwtTokenProvider tokenProvider;
	
	@Autowired
	private UsuarioService usuarioService;
	
	
	@ResponseBody
	@PostMapping(value="/login")
	public UsuarioLogadoDto login( @RequestBody Login login  ) {
		
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
		
		setToken(tokenProvider.createToken(usuario.get().getEmail(), usuario.get().getRoles()));
		
		UsuarioLogadoDto usuarioLogado = new UsuarioLogadoDto();
		
		usuarioLogado.setUsername(usuario.get().getUsername());
		usuarioLogado.setEmail(usuario.get().getEmail());
		usuarioLogado.setToken(getToken());
		usuarioLogado.setStatus("200");
		
	 	return usuarioLogado;
	}


	public void setToken(String token) {
		this.token = token;
	}


	public String getToken() {
		return token;
	}
	
	
	
	
}
