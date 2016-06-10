package com.uniritter.monitor.persistence.model;

public class AlertaEntity extends GenericEntity {
	
	private int metricaId;
	private String regra;
	private String mensagem;
	
	public int getMetricaId() {
		return metricaId;
	}

	public void setMetricaId(int metricaId) {
		this.metricaId = metricaId;
	}

	public String getRegra() {
		return regra;
	}

	public void setRegra(String regra) {
		this.regra = regra;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}