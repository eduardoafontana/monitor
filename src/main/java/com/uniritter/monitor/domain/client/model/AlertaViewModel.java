package com.uniritter.monitor.domain.client.model;

public class AlertaViewModel {
	
	public String regra;
	public String mensagem;
	
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public String getRegra() {
		return regra;
	}
	public void setRegra(String regra) {
		this.regra = regra;
	}
}
