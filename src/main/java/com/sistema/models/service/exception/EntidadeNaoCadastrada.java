package com.sistema.models.service.exception;

public class EntidadeNaoCadastrada extends NegocioException{


	private static final long serialVersionUID = 2387446319723554569L;

	public EntidadeNaoCadastrada(String mensagem) {
		super(mensagem);
	}

}
