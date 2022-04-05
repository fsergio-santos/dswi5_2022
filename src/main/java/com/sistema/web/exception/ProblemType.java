package com.sistema.web.exception;

public enum ProblemType {
	
	ERRO_NEGOGIO("/erro-negocio","Violação de regra de negócio"),
	RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado","O Recurso solicitado não foi localizado"),
	REGISTRO_NAO_ENCONTRADO("dados-nao-encontrado","O Registro solicitado não foi localizado"),
	DADOS_INVALIDOS("/dados-inválidos","Dados inválidos "),
	USUARIO_BLOQUEADO("/usuario-bloqueado","Usuário bloqueado "),
	SERVICO_EMAIL("/erro-servidor-email","Erro no serviço de e-mail"),
	CONFIRMACAO_SENHA("/confirmar_senha"," campos confirmação de senha inválido!"),
	NAO_ESPERADO("/erro_nao_esperado"," erro não esperado, procure o administrador do sistema!"),
	USUARIO_NAO_ENCONTRADO("/usuario_nao_encontrado", "Usuário não encontrado");
	
	private String uri;
	private String title;
	
	private ProblemType(String uri, String title) {
		this.uri = uri;
		this.title = title;
	}

	public String getUri() {
		return uri;
	}

	public String getTitle() {
		return title;
	}


}
