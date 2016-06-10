package com.uniritter.monitor.domain.repository.model;

import com.uniritter.monitor.domain.model.AlertaRegra;

public class AlertaEventData extends GenericEventData {

	private AlertaRegra regra;
	private String mensagem;
	
	public AlertaRegra getRegra() {
		return regra;
	}

	public void setRegra(AlertaRegra regra) {
		this.regra = regra;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}
