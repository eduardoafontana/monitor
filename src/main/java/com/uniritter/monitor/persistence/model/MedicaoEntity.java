package com.uniritter.monitor.persistence.model;

import java.util.Date;

public class MedicaoEntity extends GenericEntity {

	private int metricaId;
	private int mac;
	private int valor;
	private Date quando;
	
	public int getMetricaId() {
		return metricaId;
	}
	public void setMetricaId(int metricaId) {
		this.metricaId = metricaId;
	}
	public int getMac() {
		return mac;
	}
	public void setMac(int mac) {
		this.mac = mac;
	}
	public int getValor() {
		return valor;
	}
	public void setValor(int valor) {
		this.valor = valor;
	}
	public Date getQuando() {
		return quando;
	}
	public void setQuando(Date quando) {
		this.quando = quando;
	}
}
