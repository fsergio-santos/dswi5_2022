package com.sistema.models.domain.dto;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;


public class FotoRequest implements Serializable {

	private static final long serialVersionUID = 657066037177942886L;

	private String id;
	private MultipartFile foto;
		
	public FotoRequest(String id, MultipartFile foto) {
		this.id = id;
		this.foto = foto;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public MultipartFile getFoto() {
		return foto;
	}
	
	public void setFoto(MultipartFile foto) {
		this.foto = foto;
	}
	
	
	
	
}
