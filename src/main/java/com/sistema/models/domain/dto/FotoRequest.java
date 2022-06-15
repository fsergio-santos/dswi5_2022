package com.sistema.models.domain.dto;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;


public class FotoRequest implements Serializable {

	private static final long serialVersionUID = 657066037177942886L;

	private Long id;
	private MultipartFile foto;
		
	public FotoRequest(Long id, MultipartFile foto) {
		this.id = id;
		this.foto = foto;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public MultipartFile getFoto() {
		return foto;
	}
	
	public void setFoto(MultipartFile foto) {
		this.foto = foto;
	}
	
	
	
	
}
