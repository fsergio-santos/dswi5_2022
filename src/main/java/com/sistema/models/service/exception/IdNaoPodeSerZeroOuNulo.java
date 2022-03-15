package com.sistema.models.service.exception;

public class IdNaoPodeSerZeroOuNulo extends NegocioException {

	private static final long serialVersionUID = -704171824942643269L;

	public IdNaoPodeSerZeroOuNulo(String mensagem) {
		super(mensagem);
	}

}
