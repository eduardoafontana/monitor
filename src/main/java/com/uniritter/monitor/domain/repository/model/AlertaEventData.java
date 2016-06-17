package com.uniritter.monitor.domain.repository.model;

import com.uniritter.monitor.domain.model.AlertaRegra;

public class AlertaEventData extends GenericEventData {

	private AlertaRegra regra;
	private String mensagem;
	private int valor;
	
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
	
	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}
}
