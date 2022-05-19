package com.sistema.models.service.exception;

public class FileStorageException extends NegocioException {

	private static final long serialVersionUID = 1L;

	public FileStorageException(String mensagem) {
		super(mensagem);
	}
	
	public FileStorageException(String mensagem, Throwable cause) {
		super(mensagem, cause);
	}
	
	

}
