package com.sistema.models.service.security;

import org.springframework.security.core.userdetails.User;

import com.sistema.models.domain.Usuario;

public class UsuarioSistema extends User{

	private static final long serialVersionUID = -3428462420714158180L;
	private Usuario usuario;
	
	public UsuarioSistema(Usuario usuario) {
		super( usuario.getUsername(),
			   usuario.getPassword(),
			   usuario.isEnabled(),
			   usuario.isAccountNonExpired(),
			   usuario.isAccountNonLocked(),
			   usuario.isCredentialsNonExpired(),
			   usuario.getAuthorities());
		
	    this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
	

}
