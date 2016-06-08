package com.uniritter.monitor.domain.repository;

import java.util.Date;

public class MedicaoEventData implements IEntity {

	private int id;
	private int mac;
	private int valor;
	private Date quando;
	private Date created;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
