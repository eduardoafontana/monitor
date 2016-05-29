package com.uniritter.monitor.persistence.model;

import java.util.Date;

public class MetricaEntity {
	public long getId() {
		return id;
	}

	public void setId(long id) {
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
	
	public long id;
	public String tipo;
	public Date created;
	
	public MetricaEntity(){}
	
	public MetricaEntity(long id, String tipo, Date created) {
		this.id = id;
		this.tipo = tipo;
		this.created = created;
	}
}
