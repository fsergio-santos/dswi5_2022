package com.sistema.web.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class Login {

	@ResponseBody
	@GetMapping(value="/login")
	public String inserir() {
	 	return "Login efetuado";
	}
	
	
}
