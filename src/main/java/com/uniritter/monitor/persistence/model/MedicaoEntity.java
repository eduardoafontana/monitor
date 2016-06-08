package com.uniritter.monitor.persistence.model;

import java.util.Date;

public class MedicaoEntity {

	public int id;
	public int metricaId;
	public int mac;
	public int valor;
	public Date quando;
	public Date created;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
}
