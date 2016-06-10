package com.uniritter.monitor.domain.client.model;

import java.util.Date;

public class MedicaoViewModel {
	
	public int mac;
	public int valor;
	public Date quando;
	
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
