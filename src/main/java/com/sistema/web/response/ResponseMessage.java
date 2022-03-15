package com.sistema.web.response;

public class ResponseMessage {

	private String mensagem;
	private Integer httpCode;
	
	public ResponseMessage(String mensagem, Integer httpCode) {
		super();
		this.mensagem = mensagem;
		this.httpCode = httpCode;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Integer getHttpCode() {
		return httpCode;
	}

	public void setHttpCode(Integer httpCode) {
		this.httpCode = httpCode;
	}
	
	
}
