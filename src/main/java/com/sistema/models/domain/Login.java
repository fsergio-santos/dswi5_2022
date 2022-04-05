package com.sistema.models.domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Login {
	
	private String email;
	private String password;
	
	public Login() {
		
	}

	public Login(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	@Email(message = "E-mail inválido")
	@NotEmpty(message = "E-mail não pode ser nulo")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	@NotEmpty(message = " Senha inválida! ")
	@Size(min=6, message="A senha deve conter {min} caracteres!")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
}
