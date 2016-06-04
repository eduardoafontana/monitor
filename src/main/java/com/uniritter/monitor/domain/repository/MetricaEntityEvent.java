package com.uniritter.monitor.domain.repository;

import java.util.Date;

public class MetricaEntityEvent {
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}	
	
	public int id;
	public String tipo;
	public Date created;
}
