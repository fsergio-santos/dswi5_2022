package com.sistema.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.sistema.models.service.security.jwt.JwtConfigurer;
import com.sistema.models.service.security.jwt.JwtTokenProvider;



@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		String password = passwordEnconder().encode("123456");
//        //System.out.println(password);
//		auth.inMemoryAuthentication()
//		    .withUser("admin")
//		    .password(password)
//		    .roles("ADMIN");
//		
//		
//	}
	
	@Autowired
	private JwtTokenProvider tokenProvider;
	
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
			.antMatchers("/autor/**").hasAnyRole("ADMIN","USUARIO")
			.antMatchers("/livro/*").hasRole("USUARIO")
			.antMatchers("/seguranca/**").hasRole("ADMIN")
			.antMatchers("/login").permitAll()
		    .anyRequest().authenticated();
		
		http.csrf().disable();
		
		http.cors();
		
		http.apply(new JwtConfigurer(tokenProvider));
		    
	}
	
	@Bean
	public PasswordEncoder passwordEnconder() {
		return new BCryptPasswordEncoder();
	}
	

}
