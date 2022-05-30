package com.sistema.models.domain.dto;

public class FotoCadastrada {

	private Long id;
	private String foto;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	@Override
	public String toString() {
		return "FotoCadastrada [id=" + id + ", foto=" + foto + "]";
	}

    	
	
	
}
