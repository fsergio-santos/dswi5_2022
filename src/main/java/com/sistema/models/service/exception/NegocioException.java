package com.sistema.models.service.exception;

public class NegocioException extends RuntimeException {

	private static final long serialVersionUID = -9161870163583486974L;
	
	public NegocioException(String mensagem) {
		super(mensagem);
	}

	public NegocioException(String mensagem, Throwable cause) {
		super(mensagem, cause);
	}
	
}
