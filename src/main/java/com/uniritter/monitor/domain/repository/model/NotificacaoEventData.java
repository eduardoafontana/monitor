package com.uniritter.monitor.domain.repository.model;

public class NotificacaoEventData extends GenericEventData {

	private String mensagem;

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}
