package com.uniritter.monitor.persistence.model;

import java.util.Date;

public class MetricaEntity {
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public long id;
	public String nome;
	public Date created;
	
	public MetricaEntity(){}
	
	public MetricaEntity(long id, String nome, Date created) {
		this.id = id;
		this.nome = nome;
		this.created = created;
	}
}
