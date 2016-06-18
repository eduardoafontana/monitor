package com.uniritter.monitor.persistence.model;

public class NotificacaoEntity extends GenericEntity {

	private String mensagem;

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}
