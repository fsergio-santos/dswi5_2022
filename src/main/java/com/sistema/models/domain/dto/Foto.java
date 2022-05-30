package com.sistema.models.domain.dto;

import java.io.InputStream;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Foto {
	
	private String nomeArquivo; 
	private String contentType;
	private Long id;
	
	@JsonIgnore
	private InputStream inputStream;

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	

	@Override
	public String toString() {
		return "Foto [nomeArquivo=" + nomeArquivo + ", contentType=" + contentType + ", id=" + id + ", inputStream="
				+ inputStream + "]";
	}
	
	
	
	

}
