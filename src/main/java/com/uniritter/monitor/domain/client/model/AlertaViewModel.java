package com.uniritter.monitor.domain.client.model;

public class AlertaViewModel {
	
	public String regra;
	public String mensagem;
	public int valor;
	
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
	public int getValor() {
		return valor;
	}
	public void setValor(int valor) {
		this.valor = valor;
	}
}
