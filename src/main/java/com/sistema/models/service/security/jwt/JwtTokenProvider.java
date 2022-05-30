package com.sistema.models.service.security.jwt;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.sistema.models.domain.Role;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtTokenProvider {
	
	
	private String chaveSecreta = "segredo";
	
	private long tempoDeValidadeDoToken = 3600000 * 6; 

	@Autowired
	private UserDetailsService userDetailsService;
	
	
	@PostConstruct
	protected void init() {
		chaveSecreta = Base64.getEncoder().encodeToString(chaveSecreta.getBytes());
	}
	

	public String createToken(String username, List<Role> roles) { 
		
		Claims claims = Jwts.claims().setSubject(username);
		claims.put("roles", roles);
		
		Date hoje = new Date();
		
		Date dataValidaFinal = new Date(hoje.getTime() + tempoDeValidadeDoToken);
		
		return Jwts.builder()
				   .setClaims(claims) 
				   .setIssuedAt(hoje)
				   .setExpiration(dataValidaFinal)
				   .signWith(SignatureAlgorithm.HS256, chaveSecreta)
				   .compact();
	}
	
	
	
	public String resolveToken(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if ( bearerToken != null  && bearerToken.startsWith("Bearer ") ) {
			return bearerToken.substring(7, bearerToken.length());
		}
		return null;
	}

	public boolean validateToken(String token) {
		Jws<Claims> claims = Jwts.parser().setSigningKey(chaveSecreta).parseClaimsJws(token);
		if (claims.getBody().getExpiration().before(new Date())) {
			return false;
		}
		return true;
	}

	public Authentication getAuthentication(String token) {
		UserDetails usuario = userDetailsService.loadUserByUsername(getUsername(token));
		return new UsernamePasswordAuthenticationToken(usuario,"", usuario.getAuthorities());
	}


	private String getUsername(String token) {
 		return Jwts.parser().setSigningKey(chaveSecreta)
 				            .parseClaimsJws(token).getBody().getSubject();
	}

}
